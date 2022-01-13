package org.bhu.commons.lang.analyzer.enamex;

import org.bhu.commons.lang.analyzer.bean.Nature;
import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.util.CollectionUtil;
import org.edu.bhu.corpus.utils.Predefine;

public class PersonName {
	
	
	public static void recognition(Term[] terms) {
		
		int length = terms.length - 1;
		Term to = null;
		Term from = null;
		for (int i = 0; i < length; i++) {
			if (terms[i] == null) {
				continue;
			} 
			
			else if (!terms[i].getNatures().containSNR()) {
				continue;
			}

			to = terms[i].to();
			from = terms[i].from();
			
			if(terms[i].getNatures().containSNR() && CollectionUtil.EqualsOfAny(to.getName(), Predefine.PersonBackUnits)) {
				terms[i].setNature(Nature.SNR);
	
			}
			else if(terms[i].getNatures().containSNR() && CollectionUtil.EqualsOfAny(from.getName(), Predefine.PersonFrontUnits)) {
				terms[from.getOffe()].setName(from.getName() + terms[i].getName());
				terms[from.getOffe()].setNature(Nature.NR);
				terms[from.getOffe()].setTo(terms[i].to());
				terms[i]= null;
				
			}
			
//			// 如果不等,说明terms[i]发生了改变
//			if (terms[i].to() != temp) {
//				TermUtil.termLink(terms[i], temp);
//				// 将中间无用元素设置为null
//				for (int j = i + 1; j < temp.getOffe(); j++) {
//					terms[j] = null;
//				}
//				i = temp.getOffe() - 1;
//			}
		}

	}
	
	
}
