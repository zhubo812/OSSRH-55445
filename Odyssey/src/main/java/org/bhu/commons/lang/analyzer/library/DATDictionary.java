package org.bhu.commons.lang.analyzer.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bhu.commons.lang.analyzer.bean.AnalyzerItem;
import org.bhu.commons.lang.analyzer.bean.PersonNatureAttr;
import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad;
import org.bhu.commons.lang.dat.DoubleArrayTrie;
import org.bhu.commons.lang.dat.Item;

public class DATDictionary {

	/**
	 * 所有在词典中出现的词,并且承担简繁体转换的任务.
	 */
	public static final char[] IN_SYSTEM = new char[65536];
	
	/**
	 * 人名补充
	 */
	private static final Map<String, PersonNatureAttr> PERSONMAP = new HashMap<>();

	/**
	 * 外国人名补充
	 */
	private static final Set<String> FOREIGNSET = new HashSet<>();

	/**
	 * 核心词典
	 */
//	private static final DoubleArrayTrie DAT = loadDAT();
	private static final DoubleArrayTrie DAT = null;

	/**
	 * 数组长度
	 */
	public static int arrayLength = DAT.arrayLength;

	private static final String RESOURCE = "resources/";
	/**
	 * 加载词典
	 * 
	 * @return
	 */
	private static DoubleArrayTrie loadDAT() {

		long start = System.currentTimeMillis();

		try {
			DoubleArrayTrie dat = DoubleArrayTrie.loadText(getStream("core.dct"), AnalyzerItem.class);

			/**
			 * 人名识别必备的
			 */
//			personNameFull(dat);//人名识别资源，asian_name.data可删除
//			EasternAsianName.loadNameMap();
			/**
			 * 记录词典中的词语，并且清除部分数据
			 */

			for (Item item : dat.getDAT()) {
				if (item == null || item.name == null) {
					continue;
				}

				if (item.status < 4) {
					for (int i = 0; i < item.name.length(); i++) {
						IN_SYSTEM[item.name.charAt(i)] = item.name.charAt(i);
					}
				}
				if (item.status < 2) {
					item.name = null;
					continue;
				}
			}
			// 特殊字符标准化
			IN_SYSTEM['％'] = '%';

			StaticDictionaryLoad.LIBRARYLOG.info("init core library ok use time :" + (System.currentTimeMillis() - start));

			return dat;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return null;
	}
	
//	private static InputStream getStream(String name){
//		return StaticDictionaryLoad.class.getResourceAsStream(String.format("/org/bhu/commons/lang/analyzer/lexicon/%s",name));
//	}
	private static InputStream getStream(String name){
//		return StaticDictionaryLoad.class.getResourceAsStream(String.format("%s%s", RESOURCE,name));
		InputStream input = null;
//		System.out.println(name);
		try {
			input =new FileInputStream(new File(String.format("%s%s", RESOURCE,name)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}

	/**
	 * 取得人名补充
	 *
	 * @param term
	 * @return
	 */
	public static boolean foreign(Term term) {
		String name = term.getName();

		boolean contains = FOREIGNSET.contains(name);
		if (contains) {
			return contains;
		}

		if (!term.getNatureStr().startsWith("nr")) {
			return false;
		}

		for (int i = 0; i < name.length(); i++) {
			if (!FOREIGNSET.contains(String.valueOf(name.charAt(i)))) {
				return false;
			}
		}
		return true ;
	}
	
	/**
	 * 取得人名补充
	 *
	 * @param name
	 * @return
	 */
	public static PersonNatureAttr person(String name) {
		return PERSONMAP.get(name);
	}

	public static int status(char c) {
		Item item = DAT.getDAT()[c];
		if (item == null) {
			return 0;
		}
		return item.status;
	}

	/**
	 * 判断一个词语是否在词典中
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isInSystemDic(String word) {
		Item item = DAT.getItem(word);
		return item != null && item.status > 1;
	}

	public static AnalyzerItem getItem(int index) {
		AnalyzerItem item = DAT.getItem(index);
		//词典中没有的繁体字会被检索出来
		if (item == null) {
			return AnalyzerItem.NULL;
		}

		return item;
	}

	public static AnalyzerItem getItem(String str) {
		AnalyzerItem item = DAT.getItem(str);
		if (item == null) {
			return AnalyzerItem.NULL;
		}

		return item;
	}

	public static int getId(String str) {
		return DAT.getId(str);
	}

}
