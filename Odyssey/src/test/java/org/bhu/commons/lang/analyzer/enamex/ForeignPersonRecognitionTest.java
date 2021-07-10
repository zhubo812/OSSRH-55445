package org.bhu.commons.lang.analyzer.enamex;

import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.junit.Test;

public class ForeignPersonRecognitionTest {

	
	@Test
	public void regconitionTester() {
		String line = "委员会已多次提议将叙利亚富人和掌权者作为制裁对象，并建议自12月中旬起中止往返叙利亚的航班。维生素abc";
		ForeignPersonRecognition fpn = new ForeignPersonRecognition();
		List<Entity> list = fpn.recognition(line);
		
		for(Entity e : list) {
			System.out.println(e.getExpression());
		}
	}
}
