package org.edu.bhu.corpus.utils;

import java.util.HashMap;

import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.json.JSONObject;

public class CoreLex {
	HashMap<String, Integer> coreMap = new HashMap<String, Integer>();
	HashMap<String, JSONObject> nHashMap = new HashMap<String, JSONObject>();
	
	public void coreLexBuilder() {
		String path = "C:/FTP/data/ncore.ini";
		String outpath = "C:/FTP/data/core_id.ini";
		FileReader reader = new FileReader(path,"utf-8");
		FileWriter writer = new FileWriter(outpath,"utf-8");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String line;
		int counter =100;
		int id = -1;
		for(;(line= reader.readLine())!=null;) {
			String[] items= line.split("\t");
			String word = items[0].trim();
			String nature = items[1].trim();
			int num = Integer.parseInt(items[2].trim());
			switch (nature) {
			case "nr":
				id =2;
				break;
			case "ns":
				id =3;
				break;
			case "nt":
				id =4;
				break;
			case "nz":
				id =5;
				break;
			case "m":
				id =6;
				break;
			case "mq":
				id =7;
				break;
			case "t":
				id =8;
				break;
			case "w":
				id =9;
				break;
			default:
				id = ++counter;
			}
			if(word.equals("教育")) {
				System.out.println();
			}
			if(!nHashMap.containsKey(word)) {
				JSONObject jb = new JSONObject();
				jb.put(nature, num);
				nHashMap.put(word, jb);
			}else {
				JSONObject jb = nHashMap.get(word);
				jb.put(nature, num);
				nHashMap.put(word, jb);
			}
			if(!map.containsKey(word)) {
				map.put(word, id);	
			}
			
		}
		
		for(String word : nHashMap.keySet()) {
			writer.writeLine(word+"\t"+map.get(word)+"\t"+ nHashMap.get(word).toString());
		}
		writer.close();
	}
	
	
	public void coreLexBuilder2() {
		loadCoreMap();
		String path = "C:/FTP/data/wsd.dct";
		String outpath = "C:/FTP/data/core_id_bigram.ini";
		FileReader reader = new FileReader(path,"utf-8");
		FileWriter writer = new FileWriter(outpath,"utf-8");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String line;
		int counter =100;
		int id = -1;
		HashMap<String, JSONObject> biHashMap = new HashMap<String, JSONObject>();
		JSONObject jb= null;
		for(;(line= reader.readLine())!=null;) {
			String[] items= line.split("\t");
			String word = items[0].trim();
			if(word.equals("始##始")||word.equals("末##末")) {
				continue;
			}
			jb = new JSONObject();
			String[] nextItems = items[1].trim().split(" ");
			for(int i =0;i < nextItems.length;i++) {
				String [] strs= nextItems[i].split("=");
				String w = strs[0];
				if(w.equals("始##始")||w.equals("末##末")) {
					continue;
				}
				int num = Integer.parseInt(strs[1]);
				
				switch (w) {
				case "未##人":
					id =2;
					break;
				case "未##地":
					id =3;
					break;
//				case "nt":
//					id =4;
//					break;
				case "未##专":
					id =5;
					break;
				case "未##数":
					id =6;
					break;
//				case "mq":
//					id =7;
//					break;
				case "未##时":
					id =8;
					break;
				
				default:
					if(!coreMap.containsKey(w)) {
						System.err.println(w);
						continue;
					}
					id = coreMap.get(w);
					
					break;
				}
				
				jb.put(String.valueOf(id), num);
			}
			biHashMap.put(word, jb);
//			writer.writeLine(line.trim()+"\t"+ jb.toString());
		}
		reader = new FileReader("C:/FTP/data/core_id.ini","utf-8");
		for(;(line= reader.readLine())!=null;) {
			String[] items= line.split("\t");
			String word = items[0].trim();
			int index = Integer.parseInt(items[1]);
			if(biHashMap.containsKey(word)) {
				writer.writeLine(line+"\t"+biHashMap.get(word).toString());
			}else {
				writer.writeLine(line);
			}
		}
		writer.close();
	}
	
	private void loadCoreMap() {
		String path = "C:/FTP/data/core_id.ini";
		FileReader reader = new FileReader(path,"utf-8");
		String line;
		for(;(line= reader.readLine())!=null;) {
			String[] items= line.split("\t");
			String word = items[0].trim();
			int id = Integer.parseInt(items[1]);
			if(!coreMap.containsKey(word)) {
				coreMap.put(word, id);
			}
			
		}
	}
}
