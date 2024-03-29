package org.bhu.commons.lang.analyzer.library;

import java.util.HashMap;

import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad;

/**
 * 两个词之间的关联
 * 
 * @author Jackie
 * 
 */
public class NgramLibrary {
	static HashMap<String, HashMap<String, Integer>> wsdMap = new HashMap<String, HashMap<String,Integer>>();
	static {
		try {
			long start = System.currentTimeMillis();
			wsdMap = StaticDictionaryLoad.initBigram2();
//			StaticDictionaryLoad.initBigramByFastReader();
			StaticDictionaryLoad.LIBRARYLOG.info("init ngram ok use time :" + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查找两个词与词之间的频率
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getTwoWordFreq(Term from, Term to) {
		if (from.item().bigramEntryMap == null) {
			return 0;
		}
		Integer freq = from.item().bigramEntryMap.get(to.item().index);

		if (freq == null) {
			return 0;
		} else {
			return freq;
		}
		
	}
	
	public static int getTwoWordFreq2(Term from, Term to) {
		
		int freq = 0;
		if (!wsdMap.containsKey(from.getName())) {
			return 0;
		}else {
			if(wsdMap.get(from.getName()).containsKey(to.getName())) {
				freq = wsdMap.get(from.getName()).get(to.getName());
			}
		}
		return freq;
	}

}
