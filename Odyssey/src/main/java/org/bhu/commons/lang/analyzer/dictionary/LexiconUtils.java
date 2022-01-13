package org.bhu.commons.lang.analyzer.dictionary;

import static org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad.LIBRARYLOG;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Lexicon;
import org.bhu.commons.lang.analyzer.bean.NatureInfo;
import org.bhu.commons.lang.analyzer.bean.Uniword;
import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.edu.bhu.corpus.utils.Counter;
import org.edu.bhu.corpus.utils.Predefine;
import org.json.JSONException;
import org.json.JSONObject;

public class LexiconUtils {

	public static HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
	private static HashMap<String, Integer> natureMap = new HashMap<String, Integer>();
	public static HashMap<Integer, String> natureMapFlec = new HashMap<Integer, String>();
	public static final Uniword uniword = new Uniword();
	private static final Lexicon[][][] lex = new Lexicon[][][] { new Lexicon[7800][7800], new Lexicon[7800][7800],
			new Lexicon[7800][7800], new Lexicon[7800][7800], new Lexicon[7800][7800], new Lexicon[7800][7800] };

	private static final String SP = "\t";
	private static final String INFOS_SP = ",";

	int wordidx = 0;
	static int natureidx = 0;

	public LexiconUtils() {
		long start = System.currentTimeMillis();
		init();
		StaticDictionaryLoad.LIBRARYLOG.info("init core matrix ok use time :" + (System.currentTimeMillis() - start));
	}

	public LexiconUtils(int i) {
//		init();
	}

//	public static void getCharMap() {
//		char[] ch = Predefine.charLine.toCharArray();
//		for (int i = 0; i < ch.length; i++) {
////			if(ch[i]== '法') {
////				System.out.println();
////			}
//			charMap.put(ch[i], i);
//		}
//	}
	
	public static void getCharMap() {
		BufferedReader br = null;
		br = StaticDictionaryLoad.getCharReader();
		String temp = null;
		try {
			while ((temp = br.readLine()) != null) {
//				System.out.println(i);
				String[] items = temp.split("\t");
				char c = items[0].charAt(0);
				int idx = Integer.parseInt(items[1]);
				charMap.put(c, idx);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void init() {

		natureIndexMaker();
		getCharMap();
		getWordMatrix();
		getUniword();
	}

	private static void getWordMatrix() {
		BufferedReader br = null;
		br = StaticDictionaryLoad.getWordMatrixReader();
		String temp = null;
		int i = 0;

		try {
			while ((temp = br.readLine()) != null) {
//				System.out.println(i);
//				if(temp.startsWith("674,865")) {
//					System.out.println();
//				}
				if (temp.equals("--")) {
					i++;
					continue;
				}

				getLexicon(i, temp);
//				lex[i][unit.getFirstword()][unit.getSecondword()] = getLexicon(temp);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		LIBRARYLOG.info("init word matrix ok!");
	}

	private static void getUniword() {
		BufferedReader br = null;
		br = StaticDictionaryLoad.getUniwordReader();
		String temp = null;
		try {
			while ((temp = br.readLine()) != null) {
//				System.out.println(i);
				String[] items = temp.split(":");
				int idx = Integer.parseInt(items[0]);
				String[] natures = items[1].split(";");
				for (int i = 0; i < natures.length; i++) {
					String[] strs = natures[i].split("/");
					uniword.add(idx, new NatureInfo(Integer.parseInt(strs[0]), Integer.parseInt(strs[1])));// 载入单字词词性
				}
				uniword.loadTermNatures(idx, natureMapFlec);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getLexicon(int hi, String line) {
//		Unit unit = new Unit();
		Lexicon lexicon = new Lexicon();

		JSONObject jb = null;
		String[] items = line.split(SP);
		String[] words = items[0].split(" ");
//		System.out.println( line);
		int f = Integer.parseInt(words[0]);
		int s = Integer.parseInt(words[1]);
//		unit.setFirstword();
//		unit.setSecondword(Integer.parseInt(words[1]));

		String[] natureArray = items[1].split(INFOS_SP);//
		String[] nextArray = null;
		if(items[2].trim().length()>0) {
			nextArray = items[2].split(INFOS_SP);
		}
		String wordfreq = items[3];
		String fromNatureFreq = items[4];
		


		// 双字词
		// 1载入词性集合；2载入后字集合；3载入当前词词词性；4载入前字校对词性及前字集合。
		for (int i = 0; i < natureArray.length; i++) {// 1
			lexicon.addNatureIdx(Integer.parseInt(natureArray[i]));
		}
		if(nextArray!= null) {
		for (int i = 0; i < nextArray.length; i++) {// 2
			lexicon.addNext(Integer.parseInt(nextArray[i]));
		}
		}
		if (wordfreq.trim().length() > 0) {// 3
			jb = new JSONObject(wordfreq);
			Iterator it = jb.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				int value = jb.getInt(key);
				int natureIdx = Integer.parseInt(key);
				lexicon.addNatureFreq(natureIdx, value, natureMapFlec);
			}
		}
		if (fromNatureFreq.trim().length() > 0) {// 4
			try {
				jb = new JSONObject(fromNatureFreq);
				Iterator it = jb.keys();
				while (it.hasNext()) {
					String key = (String) it.next();
//					String value = jb.getString(key.toString());
					int natureIdx = Integer.parseInt(key);

					JSONObject js = jb.getJSONObject(key);
					Iterator itr = js.keys();
					while (itr.hasNext()) {
						String k = (String) itr.next();
						int fromCharIdx = Integer.parseInt(k);
						int freq = js.getInt(k);
						lexicon.addNature(natureIdx, fromCharIdx, freq);
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				System.err.println(fromNatureFreq);
			}
		}
		lex[hi][f][s] = lexicon;
	}

	public void builder() {
		String[] paths = { "E:/data/ncore.ini" };
//		String[] paths = {"E:/data/mini.txt"};
//		String[] paths = { "E:/data/test.txt" };
		FileReader reader = null;
		natureIndexMaker();
		String word, nature;
		int freq = 0;
		for (String path : paths) {
			reader = new FileReader(path, "utf-8");
			String line;
			for (; (line = reader.readLine()) != null;) {
				String[] items = line.split("\t");
				if (items.length != 3) {
					System.out.println(path);
					System.out.println(line);
				}
				if (line.equals("巴沙尔	nr	0")) {
					System.out.println();
				}
				word = items[0].trim();
				nature = items[1].trim();
				freq = Integer.parseInt(items[2].trim());

				int len = word.length();
				if (len > 7 || nature.equals("w")) {
					System.out.println(word);
					continue;
				}
				charIndexMaker(word);
				if (word.equals("巴沙尔")) {
					System.out.println(line);
//					System.out.println(charMap.get('法'));
//					System.out.println(charMap.get('律'));
				}

//				System.out.println(line);
				int natureidx = getNatureIdx(nature);
				NatureInfo ni = new NatureInfo(natureidx, freq);
//				loader(word, ni);
			}
		}
		FileWriter cwriter = new FileWriter("E:/data/char.txt", "utf-8");
		for (char key : charMap.keySet()) {
//			System.out.println(key+"\t"+charMap.get(key));
			cwriter.writeLine(key + "\t" + charMap.get(key));
		}
		cwriter.close();

		FileWriter writer = new FileWriter("E:/data/wordmatrix.txt", "utf-8");
		for (int i = 0; i < lex.length; i++) {
			for (int j = 0; j < lex[i].length; j++) {
				for (int k = 0; k < lex[i][j].length; k++) {
					if (lex[i][j][k] == null) {
						continue;
					}
					writer.writeLine(j + "," + k + ":" + lex[i][j][k].getInfos());
				}
			}
			writer.writeLine("--");
		}
		writer.close();

		FileWriter uWriter = new FileWriter("E:/data/uniword.txt", "utf-8");

		for (int idx : uniword.keySet()) {
			List<NatureInfo> natures = uniword.get(idx).getNatureList();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < natures.size(); i++) {
				sb.append(natures.get(i).getNatureidx()).append("/").append(natures.get(i).getFreq()).append(";");
			}
			uWriter.writeLine(idx + ":" + sb.toString());
		}
		uWriter.close();

		LIBRARYLOG.info("init word matrix ok!");
	}

	public void builder(String[] paths) {
//		String[] paths = {"E:/data/ncore.ini"};s
//		String[] paths = {"E:/data/mini.txt"};
//		String[] paths = { "E:/data/test.txt" };
		FileReader reader = null;
		natureIndexMaker();
		String word, nature,wsdstr = null;
		

		int id = -1;
		for (String path : paths) {
			reader = new FileReader(path, "utf-8");
			String line;
			for (; (line = reader.readLine()) != null;) {
				String[] items = line.split("\t");
				if (items.length < 3) {
					System.out.println(path);
					System.out.println(line);
				}
				
				

				word = items[0].trim();
				id = Integer.parseInt(items[1].trim());
				nature = items[2].trim();
//				freq = Integer.parseInt(items[2].trim());
				if(items.length ==4) {
					wsdstr = items[3];
				}

				int len = word.length();
				if (len > 7) {
					System.out.println(word);
					continue;
				}
				charIndexMaker(word);
				if (word.startsWith("包括")) {
					System.out.println(line);

				}

//				System.out.println(line);
//				int natureidx = getNatureIdx(nature);
				JSONObject jb = new JSONObject(nature);
				Iterator itr = jb.keys();
				String[] natures = new String[jb.length()];
				int[] freqs = new int[jb.length()];
				int idx =0;
				while (itr.hasNext()) {
					String k = (String) itr.next();
					int freq = jb.getInt(k);
					natures[idx] = k;
					freqs[idx] = freq;
					idx++;
				}
				int[] natureidxs = getNatureIdxs(natures);
//				NatureInfo ni = new NatureInfo(natureidx,freq);
				parser(word, natureidxs, freqs,wsdstr);
			}
		}
		System.out.println(charMap.size());
		// 写出字符集合
		FileWriter cwriter = new FileWriter("E:/data/char.txt", "utf-8");
		for (char key : charMap.keySet()) {
//			System.out.println(key+"\t"+charMap.get(key));
			cwriter.writeLine(key + "\t" + charMap.get(key));
		}
		cwriter.close();

		// 写出词表矩阵
		FileWriter writer = new FileWriter("E:/data/wordmatrix.txt", "utf-8");
		for (int i = 0; i < lex.length; i++) {
			for (int j = 0; j < lex[i].length; j++) {
				for (int k = 0; k < lex[i][j].length; k++) {
					if (lex[i][j][k] == null) {
						continue;
					}
					String words = j + " " + k;
//					System.out.println(words);
					writer.writeLine(words + "\t" + lex[i][j][k].getInfos());
				}
			}
			writer.writeLine("--");
		}
		writer.close();

		FileWriter uWriter = new FileWriter("E:/data/uniword.txt", "utf-8");

		for (int idx : uniword.keySet()) {
			List<NatureInfo> natures = uniword.get(idx).getNatureList();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < natures.size(); i++) {
				sb.append(natures.get(i).getNatureidx()).append("/").append(natures.get(i).getFreq()).append(";");
			}
			uWriter.writeLine(idx + ":" + sb.toString());
		}
		uWriter.close();

		LIBRARYLOG.info("init word matrix ok!");
	}

	

	public int getLength() {
		String[] paths = { "E:/data/ncore.ini" };
		Counter<Character> counter = new Counter<Character>();
		FileReader reader = null;
		String word;
		int max = 0;
		for (String path : paths) {
			System.out.println(path);
			reader = new FileReader(path, "utf-8");
			String line;
			for (; (line = reader.readLine()) != null;) {
				String[] items = line.split("\t");
				word = items[0].trim();

				int len = word.length();
				if (len > max) {
					max = len;
				}
				if (len > 7) {
					System.out.println(word);
					continue;
				}
				char[] ch = word.toCharArray();
				for (char c : ch) {
					counter.add(c);
				}
			}
		}

		System.out.println(max);
		return counter.size();
	}

	private void parser(String word, int[] natureidxs, int[] freqs, String wsdstr) {
		
		char[] ch = word.toCharArray();
		
		if(word.length() ==1) {
			uniwordBuilder(ch[0], natureidxs,freqs);
		}

		for (int i = 0; i < ch.length - 1; i++) {
			int f = charMap.get(ch[i]);
			int s = charMap.get(ch[i + 1]);

			lex[i][f][s] = getItem(i, f, s);// 获取Lexicon对象
			if (ch.length == 2) {
				lex[i][f][s].addNatureFreq(natureidxs, freqs, natureMapFlec);//词性ID，频次，词性标记字串
//				lex[i][f][s].setId(id);
//				if(wsdstr!=null) {
//					lex[i][f][s].addWSD(wsdstr);
//				}
				return;
			}
			if (i == ch.length - 2) {// 如果i为倒数第二个字符，添加对应的词性标记
				if (i > 0) {
					int fromIdx = charMap.get(ch[i - 1]);
					lex[i][f][s].addNature(natureidxs, fromIdx, freqs);
//					lex[i][f][s].setId(id);
//					if(wsdstr!=null) {
//						lex[i][f][s].addWSD(wsdstr);
//					}
				}
			} else {
				lex[i][f][s].addMidTag();// 词性标记插入0表示当前字符后仍有字符
				lex[i][f][s].addNext(charMap.get(ch[i + 2]));
			}

		}

	}

	private Lexicon getItem(int i, int f, int s) {
		Lexicon temp = lex[i][f][s];
		if (temp == null) {
			return new Lexicon();
		}
		return temp;
	}

	private void charIndexMaker(String word) {
		char[] ch = word.toCharArray();

		for (char c : ch) {
//			String s = String.valueOf(c);
			if (charMap.containsKey(c)) {
				continue;
			} else {
				charMap.put(c, wordidx);
				wordidx++;
			}
		}
	}
	
	private void uniwordBuilder(char c, int[] natureIdxs, int[] freqs) {
		//单字词词表加载
			int idx = charMap.get(c);
//			List<NatureInfo> list = new ArrayList<NatureInfo>();
			for(int i =0;i<natureIdxs.length;i++) {
			NatureInfo ni = new NatureInfo(natureIdxs[i],freqs[i]);
			uniword.add(idx, ni);
			}

	}

	private int getNatureIdx(String nature) {
		return this.natureMap.get(nature);
	}
	
	private int[] getNatureIdxs(String[] natures) {
		int[] natureIdxs = new int[natures.length];
		for(int i =0;i<natures.length;i++) {
			natureIdxs[i] = this.natureMap.get(natures[i]);
		}
		return natureIdxs;
	}

	private static void natureIndexMaker() {
		String[] natureplus = { "qqy", "qqm", "mq", "qq", "aqq", "aaq", "vv", "vvq", "vvo", "vyv", "vlv", "vbv", "vmv",
				"vlyv", "ia", "in", "id", "iv", "la", "ln", "ld", "ldm", "lt", "lv", "ly", "lga", "lgd", "lgv", "lgn",
				"snr", "aa", "jns", "jn", "bg", "zg", "zz", "ad", "ad", "ala", "dd", "jv" };
		String[] natures = Predefine.natures;
		for (String nature : natures) {

			if (natureMap.containsKey(nature)) {
				continue;
			} else {
				natureMapFlec.put(natureidx, nature);
				natureMap.put(nature, natureidx++);

			}
		}
		for (String n : natureplus) {
			natureMapFlec.put(natureidx, n);
			natureMap.put(n, natureidx++);
		}
	}

	public List<String> getNatureStrList(List<Integer> list) {
		List<String> nlist = new ArrayList<String>();
		for (int i : list) {
			nlist.add(getNatureStr(i));
		}
		return nlist;
	}

	private String getNatureStr(int idx) {
		return natureMapFlec.get(idx);
	}

	public int getStatus(char c) {
		if (charMap.containsKey(c)) {
			return charMap.get(c);
		}
		return -1;
	}

	public Lexicon find(int i, int j, int k) {
		return lex[i][j][k];
	}
}
