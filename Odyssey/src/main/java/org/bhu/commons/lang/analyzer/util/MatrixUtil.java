package org.bhu.commons.lang.analyzer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.edu.bhu.corpus.utils.Counter;

public class MatrixUtil {

	
	/***
	 * 输入文件为词条，输入每个词条中各个字先后出现的关系矩阵
	 * @param inPath
	 * @param outPath
	 */
	public void file2Matirx(String inPath, String outPath) {
		FileReader reader = new FileReader(inPath,"utf-8");
		String line;
		List<Character> snrlist = new ArrayList<Character>();
		Counter<String> counter = new Counter<String>();
		HashMap<Character, Integer> idxMap = new HashMap<Character, Integer>();
		HashMap<Integer, Character> ridxMap = new HashMap<Integer, Character>();
		int indx = 0;
		while((line= reader.readLine()) !=null) {
			char[] ch = line.trim().toCharArray();
			if(!snrlist.contains(ch[0])) {
				snrlist.add(ch[0]);
			}
			for(char c : ch) {
				if(!idxMap.containsKey(c)) {
					ridxMap.put(indx,c);
					idxMap.put(c, indx++);
					
				}
			}
			String sline;
			for(int i =0; i< ch.length-1;i++) {
				if(ch.length > 1) {
					sline = String.valueOf(ch[i])+String.valueOf(ch[i+1]);
					counter.add(sline);
				}
			}
		}
		System.out.println(idxMap.size());
		int[][] matrix = new int[idxMap.size()][idxMap.size()];
		for(String item : counter.keySet()) {
			int row = idxMap.get(item.charAt(0));
			int col = idxMap.get(item.charAt(1));
			matrix[row][col] = counter.get(item).intValue();
		}
		
		FileWriter writer = new FileWriter(outPath);
		StringBuilder sb ;
		for(int i =0; i< matrix.length;i++) {
			sb = new StringBuilder();
			sb.append(ridxMap.get(i)).append(" ");
			for(int j =0; j< matrix[i].length;j++) {
				if(matrix[i][j]>0) {
					sb.append(j).append(" ");
				}
			}
			writer.writeLine(sb.toString());
		}
		writer.close();
		writer = new FileWriter("E:\\BaiduNetdiskDownload\\2\\snrlist.txt");
		for(char c : snrlist) {
			writer.write(String.valueOf(c));
		}
		writer.close();
		writer = new FileWriter("E:\\BaiduNetdiskDownload\\2\\nameidx.txt");
		for(char c : idxMap.keySet()) {
			writer.writeLine(String.valueOf(c)+"\t"+idxMap.get(c));
		}
		writer.close();
	}
	
	/**
	 * 向量求和
	 * 
	 * @param dbs
	 * @return
	 */
	public static double sum(double[] dbs) {
		double value = 0;
		for (double d : dbs) {
			value += d;
		}
		return value;
	}

	public static int sum(int[] dbs) {
		int value = 0;
		for (int d : dbs) {
			value += d;
		}
		return value;
	}

	public static double sum(double[][] w) {
		// TODO Auto-generated method stub
		double value = 0;
		for (double[] dbs : w) {
			value += sum(dbs);
		}
		return value;
	}

	public static void dot(double[] feature, double[] feature1) {
		if (feature1 == null) {
			return;
		}
		for (int i = 0; i < feature1.length; i++) {
			feature[i] += feature1[i];
		}
	}
}
