package org.bhu.commons.lang.analyzer.enamex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.TermNatures;

public class EntityHelper {
//	protected static Pattern patterns = null;
//	protected static Matcher match;
	
	protected List<Entity> getEntity(String src , Pattern patterns, TermNatures termNatures) {
		int start = -1, end = -1;
		List<Entity> entitylist = new ArrayList<Entity>();
		try {
			Matcher match = patterns.matcher(src);
			while (match.find()) {
				start = match.start();
				end = match.end();
				String str = match.group();
				entitylist.add(new Entity(str, start, end, termNatures));
			}

		} catch (Exception e) {
			System.err.println(src);
			System.err.println(e.toString());
		}

		return entitylist;
	}
	
	
}
