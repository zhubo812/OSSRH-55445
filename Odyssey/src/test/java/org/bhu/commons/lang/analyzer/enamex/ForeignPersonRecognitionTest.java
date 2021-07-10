package org.bhu.commons.lang.analyzer.enamex;

import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.junit.Test;

public class ForeignPersonRecognitionTest {

	
	@Test
	public void regconitionTester() {
		String line = "没有张明何来平等李斯朱丽亚本报记者 桂杰 朱丽亚";
		ForeignPersonRecognition fpn = new ForeignPersonRecognition();
		List<Entity> list = fpn.recognition(line);
		
		for(Entity e : list) {
			System.out.println(e.getExpression());
		}
	}
}
