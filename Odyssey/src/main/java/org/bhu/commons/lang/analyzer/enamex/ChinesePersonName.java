package org.bhu.commons.lang.analyzer.enamex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.TermNatures;
import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.MatrixUtil;
import org.edu.bhu.corpus.utils.Predefine;

public class ChinesePersonName {

	HashMap<Character, Integer> indxName = new HashMap<Character, Integer>();
	List<String> snrlist = new ArrayList<String>();
	int[][] matrix = new int[2270][2270];
	int[][] nmatrix = new int[2270][2270];

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
			if(lines.get(i).trim().length()==1) {
				continue;
			}
			String[] els = lines.get(i).split("\t");//分割每一个字作为（姓氏与名首字）和作为（名首字与尾字）的数据

			String[] items = els[0].split(" ");//当前字作为姓氏与名首字数据
			for (int j = 1; j < items.length; j++) {
				int col = Integer.parseInt(items[j]);
				matrix[i][col] = 1;
			}
			indxName.put(items[0].charAt(0), i);
			//====================
			if(els.length ==2) {
				items = els[1].trim().split(" ");//当前字作为名首字数据与名尾字的数据
				for (int j = 0; j < items.length; j++) {
					int col = Integer.parseInt(items[j]);
					nmatrix[i][col] = 1;
				}
			}
		}

	}

	
	
	public List<Entity> recognition(String line) {

		List<Entity> entitylist = new ArrayList<Entity>();
		char[] ch = line.toCharArray();
		
		int start, end , count= 0;
		
		boolean flag,checker = false;
		for (int i = 0; i < ch.length - 1; i++) {
			flag = false;
		
				if(snrlist.contains(String.valueOf(ch[i]))) {//ch[i]为姓氏
					start =i;
					count = 0;
					do {
						end = i++;
						count++;
						if (i >= ch.length|| end -start>=2) {
							break;
						}
						flag = indxName.containsKey(ch[i]);
						if(flag) {
							checker =matrixCheker(count,ch,i);
						}
						
					} while (flag && checker);
					if(end > start) {
						String substr = line.substring(start, end+1);
						entitylist.add(new Entity(substr, start, end, Predefine.NR));
						i--;
					}else {
						if(flag && !checker) {
							i--;
						}
					}
				}
		}

		return entitylist;
	}
	
	private boolean matrixCheker(int count, char[] ch, int i) {
		
		if(count ==1) {
			if(matrix[indxName.get(ch[i-1])][indxName.get(ch[i])]>0) {
				return true;
			}
		}
		if(count>1) {
			if(nmatrix[indxName.get(ch[i-1])][indxName.get(ch[i])]>0) {
				return true;
			}
		}
		return false;
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
