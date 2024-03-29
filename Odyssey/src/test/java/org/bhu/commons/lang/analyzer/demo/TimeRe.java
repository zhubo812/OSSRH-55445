package org.bhu.commons.lang.analyzer.demo;

import org.junit.Test;

import edu.fudan.nlp.cn.ner.TimeNormalizer;
import edu.fudan.nlp.cn.ner.TimeUnit;

public class TimeRe {

	@Test
	private void tester() {
		// TODO Auto-generated method stub
		String target = "08年北京申办奥运会，8月8号开幕式，九月十八号闭幕式。" +
				"1年后的7月21号发生了件大事。" +
				"今天我本想去世博会，但是人太多了，直到晚上9点人还是那么多。" +
				"考虑到明天和后天人还是那么多，决定下周日再去。";
		TimeNormalizer normalizer;
		normalizer = new TimeNormalizer("./resources/TimeExp.m");
		normalizer.parse(target);
		TimeUnit[] unit = normalizer.getTimeUnit();
		for(int i = 0; i < unit.length; i++){
			System.out.println(unit[i]);
		}
	}
}
