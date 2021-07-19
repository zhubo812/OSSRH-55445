package org.bhu.commons.lang.analyzer.util;

import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.enamex.TimeHelper;
import org.junit.Test;

public class TimeHelperTester {

	
	@Test
	public void tester() {
		TimeHelper timeHelper = new TimeHelper();
		String str = "15日";
		List<Entity> list = timeHelper.getTimex(str);
		for (Entity e : list) {
			System.out.println(e.getExpression());
		}
		
		
	}
}
