package org.bhu.commons.lang.analyzer.enamex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.TermNatures;
import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.edu.bhu.corpus.utils.Counter;
import org.edu.bhu.corpus.utils.Predefine;

public class ChinesePersonName {

	HashMap<Character, Integer> indxName = new HashMap<Character, Integer>();
	List<String> snrlist = new ArrayList<String>();
	int[][] matrix = new int[2270][2270];

	public ChinesePersonName() {
		init();
	}

	public void init() {
		char[] snrchar = Predefine.chineseNameChar.toCharArray();
		for (char c : snrchar) {
			snrlist.add(String.valueOf(c));
		}
		FileReader reader = new FileReader("resources/nameMatrix.txt", "utf-8");
		List<String> lines = reader.read2List();
		for (int i = 0; i < lines.size(); i++) {
			String[] items = lines.get(i).split(" ");
			for (int j = 1; j < items.length; j++) {
				int col = Integer.parseInt(items[j]);
				matrix[i][col] = 1;
			}
			indxName.put(items[0].charAt(0), i);
		}

	}

	
	
	public List<Entity> recognition(String line) {

		List<Entity> entitylist = new ArrayList<Entity>();
		char[] ch = line.toCharArray();
		
		int start, end = 0;
		
		boolean flag = false;
		for (int i = 0; i < ch.length - 1; i++) {
			flag = false;
		
				if(snrlist.contains(String.valueOf(ch[i]))) {
					start =i;
					do {
						end = i++;
						if (i >= ch.length|| end -start>=2) {
							break;
						}
						flag = indxName.containsKey(ch[i]);
					} while (flag && matrix[indxName.get(ch[i-1])][indxName.get(ch[i])]>0);
					if(end > start) {

						String substr = line.substring(start, end+1);
						entitylist.add(new Entity(substr, start, end, TermNatures.NR));
						i--;
					}
				}
		}

		return entitylist;
	}

	// public int B = -1;//0 姓氏
	// public int C = -1;//1 双名的首字
	// public int D = -1;//2 双名的末字
	// public int E = -1;//3 单名

	public void getNameMatrix() {
		String path = "E:\\BaiduNetdiskDownload\\2\\ChineseNames_120W.txt";
		FileReader reader = new FileReader(path, "utf-8");
		Counter<String> Bmap = new Counter<String>();
		Counter<String> Cmap = new Counter<String>();
		Counter<String> Dmap = new Counter<String>();
		Counter<String> Emap = new Counter<String>();
		String line;
		List<Character> list = new ArrayList<Character>();
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("阿")) {
				continue;
			}
			char[] ch = line.toCharArray();
			for (char c : ch) {
				if (!list.contains(c)) {
					list.add(c);
				}
			}
			int len = line.length();
			if (len == 2) {
				Bmap.add(line.substring(0, 1));
				Emap.add(line.substring(1));
			} else if (len == 3) {
				Bmap.add(line.substring(0, 1));
				Cmap.add(line.substring(1, 2));
				Dmap.add(line.substring(2));
			}
		}
		FileWriter writer = new FileWriter("E:\\BaiduNetdiskDownload\\2\\chinesenameMatrix.txt");
		StringBuilder sb = null;
		for (char c : list) {
			sb = new StringBuilder();
			sb.append(c).append("\t");
			if (Bmap.contains(String.valueOf(c))) {
				sb.append(Bmap.get(String.valueOf(c))).append("\t");
			} else {
				sb.append(0).append("\t");
			}
			if (Cmap.contains(String.valueOf(c))) {
				sb.append(Cmap.get(String.valueOf(c))).append("\t");
			} else {
				sb.append(0).append("\t");
			}
			if (Dmap.contains(String.valueOf(c))) {
				sb.append(Dmap.get(String.valueOf(c))).append("\t");
			} else {
				sb.append(0).append("\t");
			}
			if (Emap.contains(String.valueOf(c))) {
				sb.append(Emap.get(String.valueOf(c))).append("\t");
			} else {
				sb.append(0).append("\t");
			}
			writer.writeLine(sb.toString().trim());
		}
		writer.close();
	}

}
