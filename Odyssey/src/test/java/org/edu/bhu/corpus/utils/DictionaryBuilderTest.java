package org.edu.bhu.corpus.utils;

import java.util.Arrays;
import java.util.List;

import org.bhu.commons.lang.analyzer.util.StringUtil;
import org.junit.Test;

public class DictionaryBuilderTest {

	@Test
	public void corpus2DictionaryErrorFinderTester() {
		DictionaryBuilder db = new DictionaryBuilder();
		String word = "的";
		String nature = "ng";
		db.corpus2DictionaryErrorFinder(word, nature);
	}
	
	@Test
	public void buildDictionaryTester() {
		DictionaryBuilder db = new DictionaryBuilder();
		db.buildDictionary();
	}
	
	
	@Test
	public void RMRB_Nature_Replacer_Tester() {
		DictionaryBuilder db = new DictionaryBuilder();
		db.RMRB_Nature_Replacer();
	}

	@Test
	public void corpus2DictionaryTester() {
		DictionaryBuilder db = new DictionaryBuilder();
		db.corpus2Dictionary();
	}
	
	@Test
	public void createCoreDictionary4SegmentTester() {
		DictionaryBuilder db = new DictionaryBuilder();
		db.createCoreDictionary4Segment();
	}

	@Test
	public void corpus2Dictionary_SingleTester() {

		String[] stoparray = { "nz", "nt", "m", "nr", "nx" };
		List<String> stoplist = Arrays.asList(stoparray);

		String line, sline;
		String word, nature, key;
		Counter<String> counter = new Counter<String>();
		line = "今年3月2日/t";
		sline = line.trim();

		String[] tokens = sline.split(" ");
		for (String token : tokens) {

			word = StringUtil.getWord(token);
			nature = StringUtil.getNature(token);
			if (stoplist.contains(nature)) {
				continue;
			} else if (nature.equals("t") && (word.indexOf("年") > -1 || word.indexOf("月") > -1 || word.indexOf("日") > -1
					|| word.indexOf("时") > -1 || word.indexOf("点") > -1)) {
				System.out.println(word + "\t" + nature);
			} else if (word.length() > 6 && (!nature.equals("l") || !nature.equals("i"))) {
				System.out.println(word + "\t" + nature);
			} else if (word.contains("（")||word.contains("(")) {
				System.out.println(word + "\t" + nature);
			} else {
				key = word + "\t" + nature;
				counter.add(key);
			}
		}
	}

}
