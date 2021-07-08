package org.bhu.test;

import org.bhu.commons.lang.analyzer.enamex.ForeignPersonRecognition;
import org.junit.Test;

public class ForeignPersonRecognitionTest {

	@Test
	public void Tester() {
		ForeignPersonRecognition fpr = new ForeignPersonRecognition();
		String line ="就算来了中国也里巴罗维奇肯定不会去你南阳维多利亚港";
		fpr.recognition(line);
	}
}
