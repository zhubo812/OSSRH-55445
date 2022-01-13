package org.edu.bhu.corpus.utils;

import java.util.HashSet;
import java.util.List;

import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileUtil;
import org.bhu.commons.lang.analyzer.util.FileWriter;

public class SogouLexicon {

	public void getLexFile(String name, String dir, HashSet<String> set) {
		
		List<String> paths = FileUtil.getAllFiles(dir);
		FileWriter writer = new FileWriter("E:\\data\\lex\\"+name+".txt","utf-8");
		for (String path : paths) {
//			if(!path.endsWith(".txt")) {
//				continue;
//			}
			FileReader reader = new FileReader(path);
			System.out.println(path);
			String line;
			String[] strs= path.split("\\\\");
			String subclass = strs[5];
			for (; (line = reader.readLine()) != null;) {
				String[] items = line.split("\t");
				if(items.length==1||line.trim().length()==0) {
					continue;
				}
				String word = items[0];
				String nature = items[1];
				if(set.contains(word.trim())) {
					continue;
				}
				int len = word.length();
				writer.writeLine(word+"\t"+nature+"\t"+len+"\t"+subclass+"\t"+name);
			}
		}
		
		writer.close();
	}
	
	
	public void wordFilter(HashSet<String> set) {
		String path = "E:\\data\\lex\\annotation.txt";
		FileReader reader = new FileReader(path,"utf-8");
		FileWriter writer = new FileWriter("E:\\data\\lex\\wordset.txt","utf-8");
		String line;

		for (; (line = reader.readLine()) != null;) {
			if(line.startsWith("财会")) {
				System.out.println(line);
			}
			line= line.replace("\"", "");
			String[] items = line.split("\t");
			String word = items[2];
			if(set.contains(word)) {
				
				System.out.println(line);
				continue;
			}
			writer.writeLine(line.trim());
		}
		writer.close();
	}

	public HashSet<String> loadDic() {
		HashSet<String> set = new HashSet<String>();
		String path = "E:\\data\\core.ini";
		FileReader reader = new FileReader(path,"utf-8");
		String line;

		for (; (line = reader.readLine()) != null;) {
//			if(line.startsWith("财会")) {
//				System.out.println(line);
//			}
			String[] items = line.split("\t");
			String word = items[0];
			set.add(word);
		}
		return set;
	}
}
