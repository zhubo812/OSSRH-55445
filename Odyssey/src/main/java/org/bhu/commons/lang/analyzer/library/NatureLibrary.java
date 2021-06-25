package org.bhu.commons.lang.analyzer.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.bhu.commons.lang.analyzer.bean.Nature;
import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad;
import org.bhu.commons.lang.analyzer.util.StringUtil;

/***
 * 词性和词性之间的关系.以及词性的索引.这是个好东西. 
 * 里面数组是从ict里面找来的. 不是很新.没有语料无法训练
* @ClassName: NatureLibrary 
* @Description: TODO() 
* @author Jackie zhubo812@gmail.com 
* @date 2015年8月11日 下午4:32:39 
*
 */
public class NatureLibrary {

	private static final int YI = 1;
	private static final int FYI = -1;
	/**
	 * 词性的字符串对照索引位的hashmap
	 */
	private static HashMap<String, Nature> NATUREMAP = new HashMap<String, Nature>();
	private static HashMap<String, Double> NATURERELATION = new HashMap<String, Double>();
	private static double[][] TRANS_P = new double[39][39];
	private static double[] INIT_NATURE_P = new double[39];
	private static int[] INIT_NATURE_F = new int[39];
	private static int[] STATES = new int[39];
	private static HashMap<String, Integer> NATURE_INDEX= new HashMap<String, Integer>();
	private static HashMap<String, Double> NATURE_INDEX_DETAIL= new HashMap<String, Double>();
	/**
	 * 词与词之间的关系.对照natureARRAY,natureMap
	 */
	private static int[][] NATURETABLE = null;

	/**
	 * 初始化对照表
	 */
	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("词性列表加载失败!");
		}
	}

	/**
	 * 初始化词性对照表（包含词性，词性索引，词性频次）
	* @Title: init 
	* @Description: 
	* @param @throws IOException    
	* @return void   
	* @throws
	 */
	private static void init() throws IOException {
		String split = "\t";
		/**
		 * 修改工作：引入Predefine 词性数组，存入带有索引标记的容器中 便于路径转换词性序列
		 */
		// 加载词对照性表
//		BufferedReader reader = StaticDictionaryLoad.getNatureMapReader();
		String temp = null;
//		String[] strs = null;
//		int maxLength = 0;
//		int p0 = 0;
//		int p1 = 0;
//		int p2 = 0;
//		while ((temp = reader.readLine()) != null) {
//			strs = temp.split(split);
//			if (strs.length != 4)
//				continue;
//
//			p0 = Integer.parseInt(strs[0]);
//			p1 = Integer.parseInt(strs[1]);
//			p2 = Integer.parseInt(strs[3]);
//			NATUREMAP.put(strs[2], new Nature(strs[2], p0, p1, p2));
//			maxLength = Math.max(maxLength, p1);
//		}
//		reader.close();

		/**
		 * 加载词性转移矩阵
		 */
//		NATURETABLE = new int[maxLength + 1][maxLength + 1];
		BufferedReader reader = StaticDictionaryLoad.getNatureMatrixReader();
//		int j = 0;
		int row=0;
		while ((temp = reader.readLine()) != null) {
			if (StringUtil.isBlank(temp))
				continue;
//			strs = temp.split(split);
//			for (int i = 0; i < strs.length; i++) {
//				NATURETABLE[j][i] = Integer.parseInt(strs[i]);
//			}
//			j++;
			String[] items = temp.split(split);
			for(int i =0;i<items.length;i++) {
				TRANS_P[row][i]= Double.parseDouble(items[i]);
			}
			row++;
		}
		reader.close();

		
		/***
		 * 加载词性初始概率、词性索引转换表
		 */
		reader = StaticDictionaryLoad.getNatureInitReader();
		int indx =0;
		while ((temp = reader.readLine()) != null) {
			if (StringUtil.isBlank(temp))
				continue;
			String[] items = temp.split(split);
//			if(strs.length!=3)continue;
//			String relation = strs[0]+"\t"+strs[1];
//			double value = Double.parseDouble(strs[2]);
//			if(!NATURERELATION.containsKey(relation)){
//				NATURERELATION.put(relation, value);
//			}
			INIT_NATURE_P[indx]= Double.parseDouble(items[1]);
			INIT_NATURE_F[indx]= Integer.parseInt(items[2]);
			
			if(!NATURE_INDEX.containsKey(items[1])){
				NATURE_INDEX.put(items[0], indx);
				NATURE_INDEX_DETAIL.put(items[0], indx*1.0);
			}
			indx++;
			
		}
		reader.close();
		
		for(int i=0;i<39;i++) {
			STATES[i]=i;
		}
		loadNatureIndexDetail();
		
	}
	
	public static double[] getINIT_NATURE_P() {
		return INIT_NATURE_P;
	}
	
	public static double[][] getTRANS_P(){
		return TRANS_P;
	}
	
	public static HashMap<String, Integer> getNATURE_INDEX(){
		return NATURE_INDEX;
	}
	
	public static HashMap<String, Double> getNATURE_INDEX_DETAIL(){
		return NATURE_INDEX_DETAIL;
	}
	
	public static int[] getINIT_NATURE_F() {
		return INIT_NATURE_F;
	}
	
	public static int[] getSTATE() {
		
		return STATES;
	}

	/**
	 * 获得两个词性之间的频率
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getTwoNatureFreq(Nature from, Nature to) {
		if (from.index < 0 || to.index < 0) {
			return 0;
		}
		return NATURETABLE[from.index][to.index];
	}

	/**
	 * 获得两个term之间的频率
	 * 
	 * @param fromTerm
	 * @param toTerm
	 * @return
	 */
	public static int getTwoTermFreq(Term fromTerm, Term toTerm) {
		Nature from = fromTerm.natrue();
		Nature to = toTerm.natrue();
		if (from.index < 0 || to.index < 0) {
			return 0;
		}
		return NATURETABLE[from.index][to.index];
	}

	/**
	 * 根据字符串得道词性.没有就创建一个
	 * 
	 * @param natureStr
	 * @return
	 */
	public static Nature getNature(String natureStr) {
		Nature nature = NATUREMAP.get(natureStr);
		if (nature == null) {
			nature = new Nature(natureStr, FYI, FYI, YI);
			NATUREMAP.put(natureStr, nature);
			return nature;
		}
		return nature;
	}
	
	
	public static double getNaturesValue(String n1, String n2){
		if(!NATURERELATION.containsKey(n1+"\t"+n2)){
			return .0;
		}
		return NATURERELATION.get(n1+"\t"+n2);
	}
	
	public static void loadNatureIndexDetail() {
		//名词细分词性
		NATURE_INDEX_DETAIL.put("snr", NATURE_INDEX.get("nr")+0.1);
		NATURE_INDEX_DETAIL.put("nq", NATURE_INDEX.get("nr")+0.2);
		//动词细分词性
		NATURE_INDEX_DETAIL.put("vv", NATURE_INDEX.get("v")+0.1);
		NATURE_INDEX_DETAIL.put("vyv", NATURE_INDEX.get("v")+0.2);
		NATURE_INDEX_DETAIL.put("vlv", NATURE_INDEX.get("v")+0.3);
		NATURE_INDEX_DETAIL.put("vlyv", NATURE_INDEX.get("v")+0.4);
		NATURE_INDEX_DETAIL.put("vbv", NATURE_INDEX.get("v")+0.5);
		NATURE_INDEX_DETAIL.put("vmv", NATURE_INDEX.get("v")+0.6);
		NATURE_INDEX_DETAIL.put("vvo", NATURE_INDEX.get("v")+0.7);
		NATURE_INDEX_DETAIL.put("vvq", NATURE_INDEX.get("v")+0.8);
		//形容词细分词性
		NATURE_INDEX_DETAIL.put("aa", NATURE_INDEX.get("a")+0.1);
		NATURE_INDEX_DETAIL.put("aba", NATURE_INDEX.get("a")+0.2);
		NATURE_INDEX_DETAIL.put("ala", NATURE_INDEX.get("a")+0.3);
		NATURE_INDEX_DETAIL.put("aaq", NATURE_INDEX.get("a")+0.4);
		//数词细分词性
		NATURE_INDEX_DETAIL.put("mm", NATURE_INDEX.get("m")+0.1);
		NATURE_INDEX_DETAIL.put("mq", NATURE_INDEX.get("m")+0.2);
		NATURE_INDEX_DETAIL.put("mmq", NATURE_INDEX.get("m")+0.3);
		//量词细分词性
		NATURE_INDEX_DETAIL.put("qq", NATURE_INDEX.get("q")+0.1);
		NATURE_INDEX_DETAIL.put("qqy", NATURE_INDEX.get("q")+0.2);
		NATURE_INDEX_DETAIL.put("qqm", NATURE_INDEX.get("q")+0.3);
		//副词细分词性
		NATURE_INDEX_DETAIL.put("dd", NATURE_INDEX.get("d")+0.1);
		//缩略语细分词性
		NATURE_INDEX_DETAIL.put("jns", NATURE_INDEX.get("j")+0.1);
		NATURE_INDEX_DETAIL.put("jnt", NATURE_INDEX.get("j")+0.2);
		NATURE_INDEX_DETAIL.put("jn", NATURE_INDEX.get("j")+0.3);
		NATURE_INDEX_DETAIL.put("jv", NATURE_INDEX.get("j")+0.4);
		//成语细分词性
		NATURE_INDEX_DETAIL.put("in", NATURE_INDEX.get("i")+0.1);
		NATURE_INDEX_DETAIL.put("iv", NATURE_INDEX.get("i")+0.2);
		NATURE_INDEX_DETAIL.put("ia", NATURE_INDEX.get("i")+0.3);
		NATURE_INDEX_DETAIL.put("id", NATURE_INDEX.get("i")+0.4);
		//习语细分词性
		NATURE_INDEX_DETAIL.put("ldm", NATURE_INDEX.get("l")+0.1);
		NATURE_INDEX_DETAIL.put("lgn", NATURE_INDEX.get("l")+0.2);
		NATURE_INDEX_DETAIL.put("lgv", NATURE_INDEX.get("l")+0.3);
		NATURE_INDEX_DETAIL.put("lgd", NATURE_INDEX.get("l")+0.4);
		NATURE_INDEX_DETAIL.put("ly", NATURE_INDEX.get("l")+0.5);
		NATURE_INDEX_DETAIL.put("ln", NATURE_INDEX.get("l")+0.6);
		NATURE_INDEX_DETAIL.put("lv", NATURE_INDEX.get("l")+0.7);
		NATURE_INDEX_DETAIL.put("la", NATURE_INDEX.get("l")+0.8);
		NATURE_INDEX_DETAIL.put("ld", NATURE_INDEX.get("l")+0.9);
		NATURE_INDEX_DETAIL.put("lt", NATURE_INDEX.get("l")+0.11);
		NATURE_INDEX_DETAIL.put("lga", NATURE_INDEX.get("l")+0.12);
		//语素细分词性
		NATURE_INDEX_DETAIL.put("zg", NATURE_INDEX.get("z")+0.1);
	}

	public static void main(String[] args) throws IOException {
		// System.out.println(NATURETABLE[NATUREMAP.get("nr").natureIndex][NATUREMAP.get("mq").natureIndex]);

	}

}
