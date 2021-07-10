package org.edu.bhu.corpus.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.bhu.commons.lang.analyzer.util.StringUtil;

/**
 * 
 * @author Jackie 从原始语料中获取核心词表
 */
public class DictionaryBuilder {
	
	
	/***
	 * 从语料和补充词典core.init中生成分词系统核心词典
	 */
	public void buildDictionary() {
		DictionaryBuilder db = new DictionaryBuilder();
		db.RMRB_Nature_Replacer();
		db.corpus2Dictionary();
		db.createCoreDictionary4Segment();
	}

	/***
	 * 人民日报词性标记替换 输入RenminRibao1998_BigWord.txt
	 * 输出RenminRibao1998_BigWord_NatureTrans.txt
	 */
	public void RMRB_Nature_Replacer() {
		String path = "E:/BaiduNetdiskDownload/2/RenminRibao1998_BigWord.txt";
		String outpath = "E:/BaiduNetdiskDownload/2/RenminRibao1998_BigWord_NatureTrans.txt";

		FileWriter writer = new FileWriter(outpath);
		FileReader reader = new FileReader(path, "utf-8");
		String line, sline;
		String word, nature;
		StringBuilder sb;
		while ((line = reader.readLine()) != null) {
			sline = line.trim();
			sb = new StringBuilder();
			if (sline.length() == 0) {
				continue;
			}
			String[] items = sline.split("\t");

			if (items.length < 2) {
				System.out.println(line);
				continue;
			}
			sb.append(items[0]).append("\t");
			String[] tokens = items[1].split(" ");
			for (String token : tokens) {
				word = StringUtil.getWord(token);
				nature = StringUtil.getNature(token).toLowerCase();
				if (nature.equals("vn")) {
					nature = "n";//这里有问题
				} else if (nature.equals("an")) {
					nature = "n";
				} else if (nature.equals("vd") || nature.equals("ad")) {
					nature = "d";
				}
				sb.append(word).append("/").append(nature).append(" ");
			}
			writer.writeLine(sb.toString());
		}
		writer.close();
	}

	/***
	 * 将RenminRibao1998_BigWord_NatureTrans.txt中的语料转换为核心词表 该词表与补充词表整合后构成分词系统的核心词表
	 */
	public void corpus2Dictionary() {

		String path = "E:/BaiduNetdiskDownload/2/RenminRibao1998_BigWord_NatureTrans.txt";
		String outpth = "E:/BaiduNetdiskDownload/2/coreDic.txt";
		String longword = "E:/BaiduNetdiskDownload/2/longword.txt";
		FileWriter longwriter = new FileWriter(longword);
		String[] stoparray = { "nz", "nt", "m", "nr", "nx" };
		List<String> stoplist = Arrays.asList(stoparray);

		FileReader reader = new FileReader(path, "utf-8");
		String line, sline;
		String word, nature, key;
		Counter<String> counter = new Counter<String>();
		while ((line = reader.readLine()) != null) {
			sline = line.trim();
			if (sline.length() == 0) {
				continue;
			}
			String[] items = sline.split("\t");

			if (items.length < 2) {
				System.out.println(line);
				continue;
			}
			String[] tokens = items[1].trim().split(" ");
			for (String token : tokens) {

				word = StringUtil.getWord(token);
				nature = StringUtil.getNature(token);
				if (stoplist.contains(nature)) {
					continue;
				} else if (nature.equals("t") && (word.indexOf("年")>-1 || word.indexOf("月")>-1 || word.indexOf("日")>-1
						|| word.indexOf("时")>-1 || word.indexOf("点")>-1)) {
					longwriter.writeLine(word + "\t" + nature);
				} else if (word.length() > 6 && (!nature.equals("l") || !nature.equals("i"))) {
					longwriter.writeLine(word + "\t" + nature);
				} else if (word.contains("（")||word.contains("(")) {
					longwriter.writeLine(word + "\t" + nature);
				} else {
					key = word + "\t" + nature;
					counter.add(key);
				}
			}
		}
		longwriter.close();
		reader.close();
		FileWriter writer = new FileWriter(outpth);
		int v;
		for (String k : counter.keySet()) {
			v = counter.get(k).intValue();
			writer.writeLine(k + "\t" + v);
		}
		writer.close();

	}
	
	
	public void corpus2DictionaryErrorFinder(String w, String tag) {

		String path = "E:/BaiduNetdiskDownload/2/RenminRibao1998_BigWord_NatureTrans.txt";

		String[] stoparray = { "nz", "nt", "m", "nr", "nx" };
		List<String> stoplist = Arrays.asList(stoparray);

		FileReader reader = new FileReader(path, "utf-8");
		String line, sline;
		String word, nature;

		while ((line = reader.readLine()) != null) {
			sline = line.trim();
			if (sline.length() == 0) {
				continue;
			}
			String[] items = sline.split("\t");

			if (items.length < 2) {
				System.out.println(line);
				continue;
			}
			String[] tokens = items[1].trim().split(" ");
			for (String token : tokens) {

				word = StringUtil.getWord(token);
				nature = StringUtil.getNature(token);
				if(word.equals(w) && nature.equals(tag)) {
					System.out.println(line);
					break;
				}
				if (stoplist.contains(nature)) {
					continue;
				} 
			}
		}

		reader.close();
	
	}
	
	/***
	 * 把从语料中生成的coreDic.txt与core.init词典对比，生成分词系统用的core.ini
	 */
	public void createCoreDictionary4Segment() {
		String path_init = "E:/BaiduNetdiskDownload/2/core.init";
		String path_corpus_dic = "E:/BaiduNetdiskDownload/2/coreDic.txt";
		String coredic_path = "E:/BaiduNetdiskDownload/2/core.ini";
		HashSet<String> wordset = new HashSet<String>();
		
		List<String> natureset = new ArrayList<String>();
		FileReader reader = new FileReader(path_corpus_dic,"utf-8");
		FileWriter writer = new FileWriter(coredic_path);
		String line;
		String word, nature,key, key_init;
		while((line= reader.readLine())!= null) {
			String[] items = line.trim().split("\t");
			word = items[0];
			nature = items[1];
			if(items.length<3) {
				System.out.println(line);
				continue;
			}
			key = word + '\t' + nature;
			writer.writeLine(line);
			if(!wordset.contains(key)) {
				wordset.add(key);
			}
		}
		reader.close();
		reader = new FileReader(path_init,"utf-8");
		while((line= reader.readLine())!= null) {
			String [] items_init = line.trim().split("\t");
			word = items_init[1];
			nature = items_init[0];
			if(nature.equals("jsnr")) {
				System.out.println(line);
				continue;
			}
			if(!natureset.contains(nature)) {
				natureset.add(nature);
			}
			key_init = word +"\t"+nature;
			if(!wordset.contains(key_init)) {
				writer.writeLine(key_init+"\t"+ String.valueOf(0));
			}
		}
		reader.close();
        writer.close();
        System.out.println(natureset.size());
        System.out.println(natureset);
        System.out.println("ok");
	}

}
