package org.bhu.commons.lang.analyzer.util;

import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.enamex.NZHelper;
import org.junit.Test;

public class NZHelperTester {

	@Test
	public void Tester() {
		NZHelper nzHelper = new NZHelper();
		String src = "《aba》和《阿道夫啊》";
		List<Entity> entitylist = nzHelper.getNZStr(src);
		for(Entity et : entitylist) {
			System.out.println(et.getExpression());
		}
	}
}
