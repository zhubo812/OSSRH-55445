package org.bhu.commons.lang.analyzer.enamex;

import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.junit.Test;

public class NumberHelperTest {

	@Test
	public void recognitionTester() {
		NumberHelper nh = new NumberHelper();
		String line = "丰田这招实在高考验中国人民抵制日货的机会来了395";
		List<Entity> list =  nh.getNumber(line);
		for(Entity e : list) {
			System.out.println(e.getExpression());
		}
	}
}
