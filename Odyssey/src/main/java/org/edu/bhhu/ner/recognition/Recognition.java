package org.edu.bhhu.ner.recognition;

import java.io.Serializable;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Term;

/**
 * 词语结果识别接口,用来通过规则方式识别词语,对结果的二次加工
 * 
 * @author Ansj
 *
 */
public interface Recognition extends Serializable{
	public void recognition(List<Term> terms) ;
}
