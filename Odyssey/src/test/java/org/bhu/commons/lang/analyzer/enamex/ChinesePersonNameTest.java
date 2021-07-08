package org.bhu.commons.lang.analyzer.enamex;

import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.junit.Test;

public class ChinesePersonNameTest {

	@Test
	public void getMatrixTester() {
		ChinesePersonName cpn = new ChinesePersonName();
		cpn.getNameMatrix();
	}
	
	@Test
	public void regconitionTester() {
		String line = "邓颖超生前杜绝建设,我们是排坛公主诸韵颖，我的拦网可以让朱婷之流找不到北，我的发球可以个个追周苏红之流的胸，之到她崩溃，我的传球更是化腐朽为神奇，任何不到位的球，在我手上都会到位";
		ChinesePersonName cpn = new ChinesePersonName();
		List<Entity> list = cpn.recognition(line);
		
		for(Entity e : list) {
			System.out.println(e.getExpression());
		}
	}
}
