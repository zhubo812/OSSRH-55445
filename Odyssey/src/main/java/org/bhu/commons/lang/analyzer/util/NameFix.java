package org.bhu.commons.lang.analyzer.util;

import org.bhu.commons.lang.analyzer.bean.Term;

public class NameFix {
	/**
	 * 人名消歧,比如.邓颖超生前->邓颖 超生 前 fix to 丁颖超 生 前! 规则的方式增加如果两个人名之间连接是- ， ·，•则连接
	 */
	public static void nameAmbiguity(Term[] terms) {
//		Term from = null;
//		Term term = null;
//		Term next = null;
//		for (int i = 0; i < terms.length - 1; i++) {
//			term = terms[i];
//			if (term != null && term.getNatures() == TermNatures.NR && term.getName().length() == 2) {
//				next = terms[i + 2];
//				if (next.getNatures().personAttr > 0) {
//					term.setName(term.getName() + next.getName().charAt(0));
//					terms[i + 2] = null;
//					terms[i + 3] = new Term(next.getName().substring(1), next.getOffe(), TermNatures.NW);
//					TermUtil.termLink(term, terms[i + 3]);
//					TermUtil.termLink(terms[i + 3], next.to());
//				}
//			}
//		}
//
//		// 外国人名修正
//		for (int i = 0; i < terms.length; i++) {
//			term = terms[i];
//			if (term != null && term.getName().length() == 1 && i > 0 && WordAlert.CharCover(term.getName().charAt(0)) == '·') {
//				from = term.from();
//				next = term.to();
//
//				if (from.natrue().natureStr.startsWith("nr") && next.natrue().natureStr.startsWith("nr")) {
//					from.setName(from.getName() + term.getName() + next.getName());
//					TermUtil.termLink(from, next.to());
//					terms[i] = null;
//					terms[i + 1] = null;
//				}
//			}
//		}

	}
}
