package org.bhu.commons.lang.analyzer.dictionary;

import static org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad.LIBRARYLOG;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Lexicon;
import org.bhu.commons.lang.analyzer.bean.NatureInfo;
import org.bhu.commons.lang.analyzer.bean.Unit;
import org.bhu.commons.lang.analyzer.bean.Uniword;
import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.edu.bhu.corpus.utils.Counter;
import org.edu.bhu.corpus.utils.Predefine;

public class LexiconUtils {

	private static HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
	private static HashMap<String, Integer> natureMap = new HashMap<String, Integer>();
	private static HashMap<Integer, String> natureMapFlec = new HashMap<Integer, String>();
	public static final Uniword uniword = new Uniword();
	private static final Lexicon[][][] lex = new Lexicon[][][] { new Lexicon[7790][7790], new Lexicon[7790][7790],
			new Lexicon[7790][7790], new Lexicon[7790][7790], new Lexicon[7790][7790], new Lexicon[7790][7790] };

	private static final String SP = ";";
	private static final String INFOS_SP = ":";
	private static final String NATURE_NEXT_SP = ",";
	private static final String WORD_SP = ",";
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

	public static void getCharMap() {
		char[] ch = Predefine.charLine.toCharArray();
		for (int i = 0; i < ch.length; i++) {
//			if(ch[i]== '法') {
//				System.out.println();
//			}
			charMap.put(ch[i], i);
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
				Unit unit = getUnit(temp);
				
				lex[i][unit.getFirstword()][unit.getSecondword()] = unit.getLexicon();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LIBRARYLOG.info("init person name matrix ok!");
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
					uniword.add(idx, new NatureInfo(Integer.parseInt(strs[0]), Integer.parseInt(strs[1])));//载入单字词词性
				}
				uniword.loadTermNatures(idx, natureMapFlec);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	private static Unit getUnit(String line) {
		Unit unit = new Unit();
		Lexicon lexicon = new Lexicon();
		String[] word_info = line.split(INFOS_SP);
		String[] words = word_info[0].split(WORD_SP);
//		if(word_info[0].equals("243,900")) {
//			System.out.println();
//		}
		unit.setFirstword(Integer.parseInt(words[0]));
		unit.setSecondword(Integer.parseInt(words[1]));
		String[] items = word_info[1].split(NATURE_NEXT_SP);//items[0]为词性序列；items[1]为后有字符
		String[] natureArray = items[0].split(SP);

//		items.length==1表示只有词性无后字符；items.length==2表示既有词性也有后字符
		
		if (items.length == 2) {
//			List<NatureInfo> natures = new ArrayList<NatureInfo>();
			for (String nature : natureArray) {
				if(nature.equals("0")) {
//					natures.add(new NatureInfo(0, 0));
//					lexicon.addNature(new NatureInfo(0, -1));
					lexicon.addNatureIdx(0);
				}else {
					String[] strs = nature.split("/");
					lexicon.addNature(new NatureInfo(Integer.parseInt(strs[0]),Integer.parseInt(strs[1])));//添加词性的频次
				}
			}
//			lexicon.setNatures(natures);
			lexicon.setTermNatures(natureMapFlec);

			List<Integer> nextlist = new ArrayList<Integer>();
			if (items[1].length() > 0) {
				String[] nexteArray = items[1].split(SP);

				for (String next : nexteArray) {
					nextlist.add(Integer.parseInt(next));
				}
				lexicon.setNextlist(nextlist);
			}
			unit.setLexicon(lexicon);
		}else {
			List<NatureInfo> natures = new ArrayList<NatureInfo>();
			for (String nature : natureArray) {
				if(nature.equals("0")) {
//					natures.add(new NatureInfo(0, 0));
//					lexicon.addNature(new NatureInfo(0, -1));
					lexicon.addNatureIdx(0);
				}else {
					String[] strs = nature.split("/");
					lexicon.addNature(new NatureInfo(Integer.parseInt(strs[0]),Integer.parseInt(strs[1])));//添加词性的频次
				}
			}
//			lexicon.setNatures(natures);
			lexicon.setTermNatures(natureMapFlec);
			unit.setLexicon(lexicon);
		}
		return unit;
	}

	public void builder() {
		String[] paths = {"E:/data/ncore.ini"};
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
				if(items.length !=3) {
					System.out.println(path);
					System.out.println(line);
				}
//				if(line.equals("小籔良隆	nr	0")) {
//					System.out.println();
//				}
				word = items[0].trim();
				nature = items[1].trim();
				freq = Integer.parseInt(items[2].trim());
				
				int len = word.length();
				if (len > 7 || nature.equals("w")) {
					System.out.println(word);
					continue;
				}
				charIndexMaker(word);
				if(word.equals("罗纳尔多")) {
					System.out.println(word);
					System.out.println(charMap.get('法'));
					System.out.println(charMap.get('律'));
				}
				
//				System.out.println(line);
				int natureidx = getNatureIdx(nature);
				NatureInfo ni = new NatureInfo(natureidx,freq);
				loader(word, ni);
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
		
		for(int idx : uniword.keySet()) {
			List<NatureInfo> natures = uniword.get(idx).getNatureList();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < natures.size(); i++) {
				sb.append(natures.get(i).getNatureidx()).append("/").append(natures.get(i).getFreq()).append(";");
			}
			uWriter.writeLine(idx+":"+ sb.toString());
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

	private void loader(String word, NatureInfo ni ) {
		char[] ch = word.toCharArray();
		
		if(ch.length==1) {//单字词词表加载
			int idx = charMap.get(ch[0]);
			uniword.add(idx, ni);
			return;
		}
//		System.out.println(word);
		for (int i = 0; i < ch.length - 1; i++) {
			int f = charMap.get(ch[i]);
			int s = charMap.get(ch[i + 1]);
			lex[i][f][s] = getItem(i, f, s);// 获取Lexicon对象

			if (ch.length == 2) {
				lex[i][f][s].addNature(ni);
			} else {

				if (i == ch.length - 2) {// 如果i为倒数第二个字符，添加对应的词性标记
					lex[i][f][s].addNature(ni);
				} else {
					lex[i][f][s].addNature(new NatureInfo(0, 0));;// 初始值-1；0为词的字串；1-80为具体词性；
					lex[i][f][s].addNext(charMap.get(ch[i+2]));
				}
				
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

	private int getNatureIdx(String nature) {
		return this.natureMap.get(nature);
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

	public static int getStatus(char c) {
		if (charMap.containsKey(c)) {
			return charMap.get(c);
		}
		return -1;
	}

	public Lexicon find(int i, int j, int k) {
		return lex[i][j][k];
	}
}
