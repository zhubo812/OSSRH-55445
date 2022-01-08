package org.bhu.test;

import org.bhu.nlp.lexicon.CoreLex;
import org.junit.Test;

public class CoreLexTester {

	@Test
	public void coreLexBuilderTester() {
		CoreLex cl = new CoreLex();
		
		cl.coreLexBuilder();
	}
	
	@Test
	public void coreLexBuilderTester2() {
		CoreLex cl = new CoreLex();
		
		cl.coreLexBuilder2();
	}
}
