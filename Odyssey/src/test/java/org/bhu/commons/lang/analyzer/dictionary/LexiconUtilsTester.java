package org.bhu.commons.lang.analyzer.dictionary;

import org.junit.Test;

public class LexiconUtilsTester {

	@Test
	public void getLengthTester() {
		LexiconUtils lu = new LexiconUtils();
		int len = lu.getLength();
		System.out.println(len);
	}
	
	@Test
	public void builderTester() {
		LexiconUtils lu = new LexiconUtils(1);
		lu.builder();

	}
}
