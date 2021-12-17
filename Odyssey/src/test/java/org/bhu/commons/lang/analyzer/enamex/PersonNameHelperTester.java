package org.bhu.commons.lang.analyzer.enamex;

import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.junit.Test;

public class PersonNameHelperTester {

	@Test
	public void tester() {
		PersonNameHelper ph = new PersonNameHelper();
		String src = "６月１４日6月14日两个半月的MH370布斯克茨,王国强、高峰、汪洋、张朝阳、韩寒、小四大前天一早核心提示： 5月16号出版的13日《人民日报》";
		List<Entity> list = ph.getPersonName(src);
		
		for(Entity entity : list) {
			System.out.println(entity.getExpression());
		}
	}
}
