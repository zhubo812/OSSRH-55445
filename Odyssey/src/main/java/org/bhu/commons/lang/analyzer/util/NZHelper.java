package org.bhu.commons.lang.analyzer.util;

import java.util.List;
import java.util.regex.Pattern;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.TermNatures;
import org.bhu.commons.lang.analyzer.enamex.EntityHelper;

public class NZHelper extends EntityHelper {
	private Pattern patterns = null;
	
	private static final String regStr = "(《.+?》)" ;//书名、文件名


	public NZHelper() {
		patterns = Pattern.compile(regStr);
	}
	
	public List<Entity> getNZStr(String src){
		return getEntity(src, patterns, TermNatures.NZ);
	}
}
