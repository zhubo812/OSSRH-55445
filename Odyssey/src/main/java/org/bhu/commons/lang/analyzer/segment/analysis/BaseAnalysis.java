package org.bhu.commons.lang.analyzer.segment.analysis;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.segment.Analysis;
import org.bhu.commons.lang.analyzer.util.AnalyzerReader;
import org.bhu.commons.lang.analyzer.util.Graph;

/**
 * 基本的分词.只做了.ngram模型.和数字发现.其他一律不管
 * 
 * @author Jackie
 * 
 */
public class BaseAnalysis extends Analysis {

	@Override
	protected List<Term> getResult(final Graph graph) {
		// TODO Auto-generated method stub
		Merger merger = new Merger() {
			@Override
			public List<Term> merger() {
				// TODO Auto-generated method stub
				graph.walkPath();
				return getResult();
			}

			private List<Term> getResult() {
				// TODO Auto-generated method stub
				List<Term> result = new ArrayList<Term>();
				int length = graph.terms.length - 1;
				for (int i = 0; i < length; i++) {
					if (graph.terms[i] != null) {
						result.add(graph.terms[i]);
					}
				}

				setRealName(graph, result);
				return result;
			}
		};
		return merger.merger();
	}

	private BaseAnalysis() {
	};

	public BaseAnalysis(Reader reader) {
		super.resetContent(new AnalyzerReader(reader));
	}

	public static List<Term> parse(String str) {
		return new BaseAnalysis().parseStr(str);
	}
}
