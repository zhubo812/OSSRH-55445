package org.bhu.commons.lang.analyzer.dictionary;

import static org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad.LIBRARYLOG;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.bhu.commons.lang.analyzer.enamex.PersonName;
import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.edu.bhu.corpus.utils.Predefine;

public class PersonNameBuilder {


	public HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
	public int [][][] nameArray = new int[][][] {new int[3482][3482],new int[3482][3482],new int[3482][3482],new int[3482][3482],new int[3482][3482],new int[3482][3482]};

	
	
	public void init() {
		char[] ch = Predefine.nameChar.toCharArray();
		for(int i =0;i< ch.length;i++) {
			charMap.put(ch[i], i);
		}
		BufferedReader br = null;


			br = StaticDictionaryLoad.getPersonNameReader();
			String temp = null;
			int i=0;
			int j =0;
			try {
				while ((temp = br.readLine()) != null) {
					
					if(temp.equals("--")) {
						i++;
						j=0;
						continue;
					}
					char[] nch = temp.toCharArray();
					for(int k=0;k<nch.length;k++) {
						this.nameArray[i][j][k]= Integer.parseInt(String.valueOf(nch[k]));
					}
					j++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LIBRARYLOG.info("init person name matrix ok!");
	}
	
	public void builder() {
		String path = "E:\\BaiduNetdiskDownload\\name.txt";
		
		
		int index = 0;
		FileReader reader = new FileReader(path, "utf-8");
		int maxLen = 0;
		for(String line; (line= reader.readLine())!= null;) {
			String[] items = line.split("\t");
			char[] ch = items[0].toCharArray();
			int len = ch.length;
			if(maxLen < len) {
				maxLen=len;
			}
			for(char c : ch) {
//				String s = String.valueOf(c);
				if(charMap.containsKey(c)) {
					continue;
				}else {
					charMap.put(c, index);
					index++;
				}
			}
//			list = ngram.ngrams(2, ch);
		}
		for(char key : charMap.keySet()) {
			System.out.println(key+"\t"+charMap.get(key));
		}
		
		
		
		System.out.println(maxLen);
		reader.close();
		reader = new FileReader(path, "utf-8");
		for(String line; (line= reader.readLine())!= null;) {
			String[] items = line.split("\t");
//			if(items[0].equals("谢尔盖")) {
//				System.out.println(line);
//			}
//			if(items[0].equals("阿尔盖")) {
//				System.out.println(line);
//			}
			char[] ch = items[0].toCharArray();
//			list = ngram.ngrams(2, ch);
			int i =0;
			int f  =-1;
			int s =-1;
			for(;i<ch.length-1;i++) {
				f = charMap.get(ch[i]);
				s = charMap.get(ch[i+1]);
				
				if(i==ch.length-2) {
					if(nameArray[i][f][s]>0) {
						nameArray[i][f][s]= 3;
					}else {
					nameArray[i][f][s]= 2;//fs首次词尾组合标记为2
					}
				}else {
					if(nameArray[i][f][s]>1) {
						nameArray[i][f][s]= 3;
					}else {
						nameArray[i][f][s]= 1;//fs首次词中组合标记为1
					}
				}
			}
			
		}
		
		FileWriter writer = new FileWriter("E:\\BaiduNetdiskDownload\\matrix.txt");
		for(int i =0;i<nameArray.length;i++) {
			for(int j=0; j<nameArray[i].length;j++) {
				for(int k=0; k<nameArray[i][j].length;k++) {
					writer.write(String.valueOf(nameArray[i][j][k]));
				}
				writer.write("\n");
			}
			writer.writeLine("--");
		}
	}
}
