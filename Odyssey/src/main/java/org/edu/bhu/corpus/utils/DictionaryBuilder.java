package org.edu.bhu.corpus.utils;

import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.bhu.commons.lang.analyzer.util.StringUtil;

/**
 * 
 * @author Jackie
 * 从原始语料中获取核心词表
 */
public class DictionaryBuilder {

	/***
	 * 人民日报词性标记替换
		输入RenminRibao1998_BigWord.txt
		输出RenminRibao1998_BigWord_NatureTrans.txt
	 */
	public void RMRB_Nature_Replacer() {
		String path = "E:/BaiduNetdiskDownload/2/RenminRibao1998_BigWord.txt";
		String outpath = "E:/BaiduNetdiskDownload/2/RenminRibao1998_BigWord_NatureTrans.txt";
		
		FileWriter writer = new FileWriter(outpath);
		FileReader reader = new FileReader(path,"utf-8");
		String line, sline;
		String word, nature;
		StringBuilder sb ;
		while((line= reader.readLine())!= null) {
			sline = line.trim();
			sb = new StringBuilder();
			if(sline.length() ==0) {
				continue;
			}
			String[] items= sline.split("\t");
			
			
			if(items.length < 2) {
				System.out.println(line);
				continue;
			}
			sb.append(items[0]).append("\t");
			String[] tokens = items[1].split(" ");
			for(String token : tokens) {
				word = StringUtil.getWord(token);
				nature = StringUtil.getNature(token);
				if(nature=="vn") {
					nature = "n";
				}
				else if( nature == "an") {
					nature = "n";
				}
				else if( nature =="vd" || nature =="ad") {
					nature = "d";
				}
				sb.append(word).append("/").append(nature).append(" ");
			}
			writer.writeLine(sb.toString());
		}
		writer.close();
	}
}
