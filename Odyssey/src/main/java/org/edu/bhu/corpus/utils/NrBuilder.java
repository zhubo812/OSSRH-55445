package org.edu.bhu.corpus.utils;

import java.util.HashMap;

import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;

public class NrBuilder {

	public String[][] buider(String path) {
		FileReader reader = new FileReader(path,"utf-8");
		HashMap<Character, Integer> idxMap = new HashMap<Character, Integer>();
		Counter<String> counter = new Counter<String>();
		Counter<Character> char_counter = new Counter<Character>();
		String line;
		int maxLen = 0;
		int index = 0;
		while ((line = reader.readLine()) != null) {
			String sline = line.strip();
			int len = sline.length();
			if (len > maxLen) {
				maxLen = len;
			}
			char[] ch = sline.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				String item = String.valueOf(ch[i]) +"\t"+ i;
				counter.add(item);
				char_counter.add(ch[i]);
				if(!idxMap.containsKey(ch[i])){
					idxMap.put(ch[i], index++);
				}
			}
//			break;
		}
		
		return getNameMatrix(maxLen, counter, char_counter, idxMap);
	}

	public String[][] getNameMatrix(int maxLen, Counter<String> counter, Counter<Character> char_counter, HashMap<Character, Integer> idxMap) {
		String[][] matrix = new String[char_counter.size()][maxLen+1];
		for(int i =0 ;i < matrix.length;i++) {
			for(int j =0; j< matrix[i].length;j++) {
				matrix[i][j]="0";
			}
		}
		char[] charlines = new char[idxMap.size()];
		int num = 0;
		for(String key : counter.keySet()) {
			
			if(counter.get(key)== null) {
				continue;
			}
			
			int indexValue = counter.get(key).intValue();
			String[] items = key.split("\t");
			char c = items[0].charAt(0);
			int idxNum = Integer.parseInt(items[1])+1;

			num = idxMap.get(c);
			charlines[num] = c;

			matrix[num][idxNum] = String.valueOf(indexValue);
		}
		
		for(char c : char_counter.keySet()) {
			int charValue = char_counter.get(c).intValue();
			num = idxMap.get(c);
			matrix[num][0] = String.valueOf(charValue);
			
		}
		
		FileWriter writer = new FileWriter("E:/BaiduNetdiskDownload/2/transchar.txt");
		for(int i =0; i< charlines.length;i++) {
			writer.write(String.valueOf(charlines[i]));
		}
		writer.close();
		
		writer = new FileWriter("E:/BaiduNetdiskDownload/2/transmatrix.txt");
		for(int i =0 ;i < matrix.length;i++) {
			StringBuilder sb = new StringBuilder();
			for(int j =0; j< matrix[i].length;j++) {
				sb.append(matrix[i][j]).append(" ");
			}
			writer.writeLine(sb.toString().strip());
		}
		writer.close();
		

		
		return matrix;
	}
	
	

}
