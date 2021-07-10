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
		String line = "现代吉普李斯张飞跑啊跑奥巴马养的一条狗叫";
		ChinesePersonName cpn = new ChinesePersonName();
		List<Entity> list = cpn.recognition(line);
		
		for(Entity e : list) {
			System.out.println(e.getExpression());
		}
	}
}
