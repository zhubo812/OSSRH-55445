package org.bhu.commons.lang.analyzer.bean;

import java.util.Map;

import org.bhu.commons.lang.dat.Item;
import org.edu.bhu.corpus.utils.Predefine;



public class AnalyzerItem extends Item {

	private static final long serialVersionUID = 1L;

	public static final AnalyzerItem NULL = new AnalyzerItem();

	public static final AnalyzerItem BEGIN = new AnalyzerItem();

	public static final AnalyzerItem END = new AnalyzerItem();

	static {
		NULL.base = 0;

		BEGIN.index = 0;
		BEGIN.termNatures = Predefine.BEGIN;

		END.index = -1;
		END.termNatures = Predefine.END;
	}

	public String param;

	/**
	 * frequency : 词性词典,以及词性的相关权重
	 */
	public TermNatures termNatures = null ;

	public Map<Integer,Integer> bigramEntryMap =  null ;

	@Override
	public void init(String[] split) {
		this.name = split[0];
		this.param = split[1];
	}

	@Override
	public void initValue(String[] split) {
		index = Integer.parseInt(split[0]);
		base = Integer.parseInt(split[2]);
		check = Integer.parseInt(split[3]);
		status = Byte.parseByte(split[4]);

		if (status > 1) {
			name = split[1];
			termNatures = new TermNatures(TermNature.setNatureStrToArray(split[5]), index);//split[5]标记词性内容的位置
		}else{
			termNatures = new TermNatures(TermNature.NULL); 
		}
	}

	@Override
	public String toText() {
		return index + "\t" + name + "\t" + base + "\t" + check + "\t" + status + "\t" + param;
	}

}
