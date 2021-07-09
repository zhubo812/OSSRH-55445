package org.bhu.commons.lang.analyzer.enamex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.TermNatures;
import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.bhu.commons.lang.analyzer.util.MatrixUtil;
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

	/***
	 * 创建中文人名矩阵
	 */
	public void getNameMatrix() {
		String inPath = "E:/BaiduNetdiskDownload/2/ChineseNames_120W.txt";
		String outPath = "E:/BaiduNetdiskDownload/2/nameMatrix.txt";
		MatrixUtil mu = new MatrixUtil();
		mu.file2Matirx(inPath, outPath);
	}

}
