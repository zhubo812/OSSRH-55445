package org.bhu.commons.lang.analyzer.bean;

import java.util.HashMap;
import java.util.List;

import org.edu.bhu.corpus.utils.Predefine;

/***
 * 
 * @ClassName: TermNatures
 * @Description: TODO() 每一个term都对应一个词性集合
 * @author Jackie zhubo812@gmail.com
 * @date 2015年8月11日 下午4:31:15
 * 
 */
public class TermNatures {

	

	/***
	 * 关于这个term的所有词性
	 */
	public TermNature[] termNatures = null;

	/***
	 * 数字属性
	 */
//	public NumNatureAttr numAttr = NumNatureAttr.NULL;

	/**
	 * 人名词性
	 */
//	public PersonNatureAttr personAttr = PersonNatureAttr.NULL;
	private boolean isSNR = false;

	private boolean isNum = false;
	private boolean isQ = false;
	/**
	 * 时间词性
	 */
//	public TimeNatureAttr timeAttr = TimeNatureAttr.NULL;

	/**
	 * 默认词性
	 */
	public Nature nature = null;

	/**
	 * 所有的词频
	 */
	public int allFreq = 0;

	/**
	 * 词的id
	 */
	public int id = -2;

	/**
	 * 构造方法.一个词对应这种玩意
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param termNatures
	 * @param id
	 */
	public TermNatures(TermNature[] termNatures, int id) {
		this.id = id;
		this.termNatures = termNatures;
		// find maxNature
		int maxFreq = -1;
		TermNature termNature = null;
		for (int i = 0; i < termNatures.length; i++) {
			if (maxFreq < termNatures[i].frequency) {
				maxFreq = termNatures[i].frequency;
				termNature = termNatures[i];
			}
		}

		if (termNature != null) {
			this.nature = termNature.nature;
		}

		serAttribute();
	}

	public TermNatures(TermNature[] termNatures) {
//		this.id = id;
		this.termNatures = termNatures;
//		TermNature termNature = null;

		if (termNatures != null && termNatures.length > 0) {
			this.nature = termNatures[0].nature;
		}

//		serAttribute();
	}

	public TermNatures(List<TermNature> termNatures) {
		this.termNatures = termNatures.toArray(new TermNature[termNatures.size()]);
		int maxFreq = -1;
		TermNature termNature = null;
		for (int i = 0; i < this.termNatures.length; i++) {
			if (maxFreq < this.termNatures[i].frequency) {
				maxFreq = this.termNatures[i].frequency;
				termNature = this.termNatures[i];
			}
		}

		if (termNatures != null && this.termNatures.length > 0) {
			this.nature = termNature.nature;
		}
	}

	public TermNatures(TermNature termNature) {
		termNatures = new TermNature[1];
		this.termNatures[0] = termNature;
		this.nature = termNature.nature;
		serAttribute();
	}

	public TermNatures(TermNature termNature, int allFreq, int id) {
		this.id = id;
		termNatures = new TermNature[1];
		termNature.frequency = allFreq;
		this.termNatures[0] = termNature;
		this.allFreq = allFreq;
	}

	public boolean containSNR() {
		return isSNR;
	}

	public void setSNR(boolean isSNR) {
		this.isSNR = isSNR;
	}
	
	public boolean containQ() {
		return isQ;
	}

	public void setQ(boolean isQ) {
		this.isQ = isQ;
	}
	
	public boolean containM() {
		return isNum;
	}

	public void setM(boolean isNum) {
		this.isNum = isNum;
	}

	private void serAttribute() {
		TermNature termNature = null;
		int max = 0;
		NumNatureAttr numNatureAttr = null;
		TimeNatureAttr timeNatureAttr = null;
		PersonNatureAttr asianNameNatureAttr = null;
		for (int i = 0; i < termNatures.length; i++) {
			termNature = termNatures[i];
			allFreq += termNature.frequency;
			max = Math.max(max, termNature.frequency);
			switch (termNature.nature.index) {
			case 18:// 数词
				if (numNatureAttr == null) {
					numNatureAttr = new NumNatureAttr();
				}
				numNatureAttr.numFreq = termNature.frequency;
				break;
			case 29:// 量词
				if (numNatureAttr == null) {
					numNatureAttr = new NumNatureAttr();
				}
				numNatureAttr.numEndFreq = termNature.frequency;
				break;
			case 33:// 时间词
				if (timeNatureAttr == null) {
					timeNatureAttr = new TimeNatureAttr();
				}
				timeNatureAttr.timeEndFreq = termNature.frequency;
				break;
			case 48:// 日本姓氏
				if (asianNameNatureAttr == null) {
					asianNameNatureAttr = new PersonNatureAttr();
				}
				break;
			case 51:
				if (asianNameNatureAttr == null) {
					asianNameNatureAttr = new PersonNatureAttr();
				}
//				 asianNameNatureAttr.allFreq = termNature.frequency;
				break;
			}
		}
//		if (numNatureAttr != null) {
//			if (max == numNatureAttr.numFreq) {
//				numNatureAttr.flag = true;
//			}
//			this.numAttr = numNatureAttr;
//		}
//		if (timeNatureAttr != null) {
//			if (max == timeNatureAttr.timeFreq) {
//				timeNatureAttr.flag = true;
//			}
//			timeNatureAttr.flag = true;
//			this.timeAttr = timeNatureAttr;
//		}
//		if (asianNameNatureAttr != null) {
//			asianNameNatureAttr.isSurname = true;
//			this.personAttr = asianNameNatureAttr;
//		}
	}

//	public void setPersonNatureAttr(PersonNatureAttr personAttr) {
//		this.personAttr = personAttr;
//	}
	public int size() {
		return this.termNatures.length;
	}

}
