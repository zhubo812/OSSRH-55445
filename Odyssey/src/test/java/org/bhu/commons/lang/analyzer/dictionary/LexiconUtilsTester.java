package org.bhu.commons.lang.analyzer.dictionary;


import org.bhu.commons.lang.analyzer.util.FileReader;
import org.junit.Test;

public class LexiconUtilsTester {

	@Test
	public void getLengthTester() {
		LexiconUtils lu = new LexiconUtils(1);
		int len = lu.getLength();
		System.out.println(len);
	}
	
	@Test
	public void builderTester() {
		LexiconUtils lu = new LexiconUtils(1);
//		String[] paths = { "E:/data/minicore.ini" };
		String[] paths = { "E:/data/core.ini" };
		lu.builder(paths);

	}
	

	@Test
	public void getUnitTester() {
		LexiconUtils lu = new LexiconUtils(1);
		String path = "E:/data/miniwordmatrix.txt";
		FileReader reader = new FileReader(path,"utf-8");
		for(String line;(line=reader.readLine())!=null;) {
//			lu.getUnit(line);
		}
	}
}
