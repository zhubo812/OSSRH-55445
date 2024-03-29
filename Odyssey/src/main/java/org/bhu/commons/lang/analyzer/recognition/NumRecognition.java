package org.bhu.commons.lang.analyzer.recognition;

import org.bhu.commons.lang.analyzer.bean.Nature;
import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.util.CollectionUtil;
import org.bhu.commons.lang.analyzer.util.TermUtil;

public class NumRecognition {

	/**
	 * 数字+数字合并,Jackie
	 * 
	 * @param terms
	 */

	
	
	private static final String[] MilitaryUnits = {"班","排","连","营","团","旅","师","军","步兵师","炮兵师"};//转nt
	public static void recognition(Term[] terms) {
		
		int length = terms.length - 1;
		Term from = null;
		Term to = null;
		Term temp = null;
		for (int i = 0; i < length; i++) {
			if (terms[i] == null) {
				continue;
			} 
			else if (".".equals(terms[i].getName()) || "．".equals(terms[i].getName())) {
				// 如果是.前后都为数字进行特殊处理
				to = terms[i].to();
				from = terms[i].from();
				if (from.isNum() && to.isNum()) {
					from.setName(from.getName() + "." + to.getName());
					TermUtil.termLink(from, to.to());
					terms[to.getOffe()] = null;
					terms[i] = null;
					i = from.getOffe() - 1;
				}
				continue;
			} else if (!terms[i].isNum()) {
				continue;
			}

			//terms[i]为数字
			// 将所有的数字合并
			to = terms[i].to();
			temp = to;
//			from = terms[i].from();
//			while ((temp = temp.to()).getNatures().numAttr.flag && !temp.getName().startsWith(DI)) {
//				terms[i].setName(terms[i].getName() + temp.getName());
//			}
			
			if(to.isNum()) {
				terms[i].setName(terms[i].getName() + to.getName());
				terms[i].setNature(Nature.M);
				terms[to.getOffe()]= null;
				terms[i].setTo(temp.to());
			}
			
			else if(to.getNatures().containQ()) {
				terms[to.getOffe()].setNature(Nature.Q);
			}
			//判断结尾是军队
			else if(CollectionUtil.EqualsOfAny(to.getName(), MilitaryUnits)){
				terms[i].setName(terms[i].getName() + to.getName());
				terms[i].setNature(Nature.NT);
				terms[to.getOffe()]= null;
				terms[i].setTo(temp.to());
			}
			
			// 如果是数字结尾
			/*
			if (StaticDictionaryLoad.isQuantifierRecognition && temp.getNatures().numAttr.numEndFreq > 0) {
				tempName = temp.getName();
				terms[i].setName(terms[i].getName() + tempName);
				int len = terms[i].getName().length();
				if(tempName.equals(YEAR)&& !(len==3||len==5)){
					terms[i].setNature(Nature.MQ);
				}
				else{
					terms[i].setNature(Nature.MQ);
				}
				terms[i].getNatures().personAttr.setIsActive(false);
				temp = temp.to();
			}
			
			if(StaticDictionaryLoad.isTimeRecognition && temp.getNatures().timeAttr.timeEndFreq >0&&temp.getName().length()==1){
				terms[i].setName(terms[i].getName() + temp.getName());
				temp = temp.to();
				terms[i].setNature(Nature.T);
			}
*/
			// 如果不等,说明terms[i]发生了改变
			if (terms[i].to() != temp) {
				TermUtil.termLink(terms[i], temp);
				// 将中间无用元素设置为null
				for (int j = i + 1; j < temp.getOffe(); j++) {
					terms[j] = null;
				}
				i = temp.getOffe() - 1;
			}
		}

	}

}
