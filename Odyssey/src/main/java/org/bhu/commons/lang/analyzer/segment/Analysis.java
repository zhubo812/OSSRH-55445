package org.bhu.commons.lang.analyzer.segment;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.Letters;
import org.bhu.commons.lang.analyzer.bean.Lexicon;
import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.bean.TermNature;
import org.bhu.commons.lang.analyzer.bean.TermNatures;
import org.bhu.commons.lang.analyzer.dictionary.LexiconUtils;
import org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad;
import org.bhu.commons.lang.analyzer.enamex.NQHelper;
import org.bhu.commons.lang.analyzer.enamex.NZHelper;
import org.bhu.commons.lang.analyzer.enamex.NumberHelper;
import org.bhu.commons.lang.analyzer.enamex.TimeHelper;
import org.bhu.commons.lang.analyzer.library.UserDefineLibrary;
import org.bhu.commons.lang.analyzer.segment.impl.GetWordsImpl;
import org.bhu.commons.lang.analyzer.util.AnalyzerReader;
import org.bhu.commons.lang.analyzer.util.Graph;
import org.bhu.commons.lang.analyzer.util.StringUtil;
import org.bhu.commons.lang.analyzer.util.WordAlert;
import org.bhu.commons.lang.trie.domain.Forest;
import org.bhu.commons.lang.trie.domain.GetWord;
import org.edu.bhu.corpus.utils.Predefine;

/**
 * 基本分词+人名识别
 * 
 * @author Jackie
 * 
 */
public abstract class Analysis {

	/**
	 * 用来记录偏移量
	 */
	public int offe;

	/**
	 * 分词的类
	 */
	private GetWordsImpl gwi = new GetWordsImpl();

	protected Forest[] forests = null;

	private Forest ambiguityForest = UserDefineLibrary.ambiguityForest;
	private static final TimeHelper timeHelper = new TimeHelper();
	private static final NZHelper nzHelper = new NZHelper();
	private static final NQHelper nqHelper = new NQHelper();
	private static final NumberHelper numHelper = new NumberHelper();
//	ForeignPersonRecognition fpr = new ForeignPersonRecognition();
//	ChinesePersonName cpn = new ChinesePersonName();
//	PersonNameHelper pnHelper = new PersonNameHelper();
	private static final LexiconUtils lu = new LexiconUtils();
	private static final Letters letters = new Letters();
	/**
	 * 文档读取流
	 */
	private AnalyzerReader br;

	protected Analysis() {
	};

	private LinkedList<Term> terms = new LinkedList<Term>();

	/**
	 * while 循环调用.直到返回为null则分词结束
	 * 
	 * @return
	 * @throws IOException
	 */

	public Term next() throws IOException {
		Term term = null;
		if (!terms.isEmpty()) {
			term = terms.poll();
			term.updateOffe(offe);
			return term;
		}

		String temp = br.readLine();
		offe = br.getStart();
		while (StringUtil.isBlank(temp)) {
			if (temp == null) {
				return null;
			} else {
				temp = br.readLine();
			}

		}

		// 歧异处理字符串

		analysisStr(temp);

		if (!terms.isEmpty()) {
			term = terms.poll();
			term.updateOffe(offe);
			return term;
		}

		return null;
	}

	/**
	 * 一整句话分词,用户设置的歧异优先
	 * 
	 * @param temp
	 */
	private void analysisStr(String temp) {
		temp = WordAlert.ToDBC(temp);
		Graph gp = new Graph(temp.trim());
		int startOffe = 0;

		List<Entity> nerlist = getNER(temp);

		// 用户词典优先切分
		if (this.ambiguityForest != null) {
			GetWord gw = new GetWord(this.ambiguityForest, gp.chars);
			String[] params = null;
			while ((gw.getFrontWords()) != null) {
				if (gw.offe > startOffe) {
					analysis2(gp, startOffe, gw.offe, nerlist);
				}
				params = gw.getParams();
				startOffe = gw.offe;
				for (int i = 0; i < params.length; i += 2) {
					gp.addTerm(new Term(params[i], startOffe, new TermNatures(new TermNature(params[i + 1], 1))));
					startOffe += params[i].length();
				}
			}
		} // 用户词典切分完毕
		if (startOffe < gp.chars.length - 1) {
			analysis2(gp, startOffe, gp.chars.length, nerlist);
		}
		List<Term> result = this.getResult(gp);

//		terms.addAll(result);
		this.addAll(result);
	}

	private void analysis2(Graph gp, int startOffe, int endOffe, List<Entity> nerList) {
		int start = 0;

		char[] chars = gp.chars;

		String str = null;
		if (nerList != null && nerList.size() > 0) {

			for (Entity tx : nerList) {
				
				if(tx.isNum()) {
					gp.addTerm(new Term(tx.getExpression(), tx.getStartIndx(), tx.getTermNatures(), true));
					gp.hasNum = tx.isNum();
				}else {
					gp.addTerm(new Term(tx.getExpression(), tx.getStartIndx(), tx.getTermNatures()));
				}
//				indexList.removeAll( CollectionUtil.getIndexList(tx.getStartIndx(), tx.getEndIndex()));
			}
		}

		StringBuilder sb = new StringBuilder();
		List<Integer> nextlist = new ArrayList<Integer>();
		for (int i = 0; i < chars.length - 1; i++) {
			// 添加单字词及词性
			sb = new StringBuilder();
			int letteridx = letters.status(chars[i]);
			if (letteridx > -1) {
				int s = i;
				sb.append(chars[i]);
				switch (letteridx) {
				case 1:
					while (++s < endOffe && letters.status(chars[s]) == 1) {
						sb.append(chars[s]);
					}
					gp.addTerm(new Term(sb.toString(), i, Predefine.EN));
					sb = new StringBuilder();
					break;
				case 2:
					while (++s < endOffe && letters.status(chars[s]) == 2) {
						sb.append(chars[s]);
					}
					gp.addTerm(new Term(sb.toString(), i, Predefine.M, true));
					gp.hasNum = true;
					sb = new StringBuilder();

					break;
				case 3:
					while (++s < endOffe && letters.status(chars[s]) == 3) {
						sb.append(chars[s]);
					}
					gp.addTerm(new Term(sb.toString(), i, Predefine.RU));
					sb = new StringBuilder();
					break;
				case 4:
					while (++s < endOffe && letters.status(chars[s]) == 4) {
						sb.append(chars[s]);
					}
					gp.addTerm(new Term(sb.toString(), i, Predefine.GK));
					sb = new StringBuilder();
					break;
				case 5:
					while (++s < endOffe && letters.status(chars[s]) == 5) {
						sb.append(chars[s]);
					}
					gp.addTerm(new Term(sb.toString(), i, Predefine.SN));
					sb = new StringBuilder();
					break;
				}

			}

			sb.append(chars[i]);
			// 查询单字词

			if (LexiconUtils.uniword.containsKey(lu.getStatus(chars[i]))) {
				gp.addTerm(new Term(String.valueOf(chars[i]), i, LexiconUtils.uniword.get(lu.getStatus(chars[i])).getTermNatures()));
				if(gp.terms[i].getNatures().containSNR()) {
					gp.hasSNR= true;
				}
				else if(gp.terms[i].getNatures().containM()) {
					gp.terms[i].setNum(true);
					gp.hasNum = true;
				}
			} else {
				gp.addTerm(new Term(String.valueOf(chars[i]), i, Predefine.NULL));
			}
			boolean keeper = true;
			int k = 0;
			int from = -1;
			start = i;
			
			for (int j = i; j < chars.length - 1; j++) {
				int m = j, n = m + 1;// m表示第一个字的位置，n表示第二个字符的位置
				if (k > 0) {
					from = charStatus(chars[m - 1]);
				}
				Lexicon lex = infos(k, chars[m], chars[n]);
				if (lex == null) {
					sb = new StringBuilder();
					break;
				}
				nextlist = lex.getNextlist();

				if (lex != null && keeper) {

					switch (lex.getTermStatus()) {
					case 0:// 无词性，有后接内容
						sb.append(chars[n]);
						k++;
						if (n < chars.length - 1 && !nextlist.contains(charStatus(chars[n + 1]))) {
							keeper = false;
						}
						break;
					case 1:
						// 有词性，也有后接内容
						sb.append(chars[n]);
						str = sb.toString();
						if (sb.length() == 2) {
							gp.addTerm(new Term(str, start, lex.geTermNatures()));// 词性转换
							if(gp.terms[start].getNatures().containSNR()) {
								gp.hasSNR= true;
							}
						} else {
							gp.addTerm(new Term(str, start, lex.geTermNatures(from, LexiconUtils.natureMapFlec)));// 词性转换
						}
						k++;
						if (n < chars.length - 1 && !nextlist.contains(charStatus(chars[n + 1]))) {
							keeper = false;
						}
//						System.out.println();
						break;
					case 2:// 有一(多)个词性，无后接内容

						sb.append(chars[n]);
						str = sb.toString();
//						System.out.println(str);
						if (sb.length() == 2) {
							gp.addTerm(new Term(str, start, lex.geTermNatures()));// 词性转换
						} else {
							gp.addTerm(new Term(str, start, lex.geTermNatures(from, LexiconUtils.natureMapFlec)));// 词性转换
						}
						keeper = false;
						str = "";
						break;
					}

				} else {
					sb = new StringBuilder();// 存储一单字词
					break;
				}
			}

		}

	}

	private Lexicon infos(int k, char x, char y) {
		int f = lu.getStatus(x);
		int s = lu.getStatus(y);
//		System.out.println(k);
//		System.out.println(String.valueOf(x)+ f);
//		System.out.println(String.valueOf(y)+s);
		if (f > -1 && s > -1) {
			return lu.find(k, f, s);
		}

		return null;
	}

	private int charStatus(char c) {
		return lu.getStatus(c);
	}
/*
	private void analysis(Graph gp, int startOffe, int endOffe, List<Entity> nerList) {
		int start = 0;
		int end = 0;
		char[] chars = gp.chars;

		String str = null;
		if (nerList != null && nerList.size() > 0) {

			for (Entity tx : nerList) {
				gp.addTerm(new Term(tx.getExpression(), tx.getStartIndx(), tx.getTermNatures()));
//				indexList.removeAll( CollectionUtil.getIndexList(tx.getStartIndx(), tx.getEndIndex()));
			}
		}

		for (int i = startOffe; i < endOffe; i++) {
			if (gp.terms[i] != null) {
				i = i + gp.terms[i].getName().length() - 1;
				continue;
			}
//			int st = status(chars[i]);
			switch (status(chars[i])) {
			case 0:
				gp.addTerm(new Term(String.valueOf(chars[i]), i, TermNatures.NULL));
				break;
			case 3:// 表示是词语，并且到此结束
				gp.addTerm(new Term(String.valueOf(chars[i]), i, TermNatures.W));
				break;
			case 4:
				start = i;
				end = 1;
				while (++i < endOffe && status(chars[i]) == 4) {
					end++;
				}
				str = WordAlert.alertEnglish(chars, start, end);
				gp.addTerm(new Term(str, start, TermNatures.EN));
				i--;
				break;
			case 5:// 数字串
				start = i;
				end = 1;
				if (start == i && chars[i] == '.') {
					gp.addTerm(new Term(String.valueOf(chars[i]), i, TermNatures.W));
					break;
				}
				while (++i < endOffe && status(chars[i]) == 5) {
					end++;
				}
				str = WordAlert.alertNumber(chars, start, end);
				if (gp.terms[start] == null) {
					gp.addTerm(new Term(str, start, TermNatures.M));
					gp.hasNum = true;
				}
				i--;
				break;
			case 6:
				start = i;
				end = 1;
				while (++i < endOffe && status(chars[i]) == 6) {
					end++;
				}
				str = new String(chars, start, end);
				gp.addTerm(new Term(str, start, TermNatures.RU));
				i--;
				break;
			case 7:
				start = i;
				end = 1;
				while (++i < endOffe && status(chars[i]) == 7) {
					end++;
				}
				str = new String(chars, start, end);
				gp.addTerm(new Term(str, start, TermNatures.GK));
				i--;
				break;

			default:

				start = i;
				end = i;

				int status = 0;
				do {
					end = ++i;
					if (i >= endOffe) {
						break;
					}
					status = status(chars[i]);
				} while (status < 4);

				if (status > 3) {
					i--;
				}

				gwi.setChars(chars, start, end);
				int max = start;
				while ((str = gwi.allWords()) != null) {
					Term term = new Term(str, gwi.offe, gwi.getItem());
					int len = term.getOffe() - max;
					if (len > 0) {
						for (; max < term.getOffe();) {
							gp.addTerm(new Term(String.valueOf(chars[max]), max, TermNatures.NULL));
							max++;
						}
					}
					gp.addTerm(term);
					max = term.toValue();
				}

				int len = end - max;
				if (len > 0) {
					for (; max < end;) {
						String temp = String.valueOf(chars[max]);
						AnalyzerItem item = DATDictionary.getItem(temp);
						gp.addTerm(new Term(temp, max, item.termNatures));
						max++;
					}
				}

				break;
			}
		}

	}
*/
	private List<Entity> getNER(String temp) {
		List<Entity> list = new ArrayList<Entity>();
		list.addAll(timeHelper.getTimex(temp));
		list.addAll(nqHelper.getNumQ(temp));
		list.addAll(nzHelper.getNZStr(temp));
//		list.addAll(cpn.recognition(temp));
//		list.addAll(fpr.recognition(temp));
//		list.addAll(pnHelper.getPersonName(tsemp));
		list.addAll(numHelper.getNumber(temp));

		return list;
	}

	/**
	 * 将为标准化的词语设置到分词中
	 * 
	 * @param gp
	 * @param result
	 */
	protected void setRealName(Graph graph, List<Term> result) {

		if (!StaticDictionaryLoad.isRealName) {
			return;
		}

		String str = graph.realStr;

		for (Term term : result) {
			term.setRealName(str.substring(term.getOffe(), term.getOffe() + term.getName().length()));
		}
	}

	protected List<Term> parseStr(String temp) {
		analysisStr(temp);
		return terms;
	}

	protected abstract List<Term> getResult(Graph graph);

	public abstract class Merger {
		public abstract List<Term> merger();
	}

	private void addAll(List<Term> result) {
		for (Term term : result) {
			if (term.getName().trim().length() == 0)
				continue;
			terms.add(term);
		}
	}

	/**
	 * 重置分词器
	 * 
	 * @param br
	 */
	public void resetContent(AnalyzerReader br) {
		this.offe = 0;
		this.br = br;
	}

	public void resetContent(Reader reader) {
		this.offe = 0;
		this.br = new AnalyzerReader(reader);
	}

	public void resetContent(Reader reader, int buffer) {
		this.offe = 0;
		this.br = new AnalyzerReader(reader, buffer);
	}

	public Forest getAmbiguityForest() {
		return ambiguityForest;
	}

	public void setAmbiguityForest(Forest ambiguityForest) {
		this.ambiguityForest = ambiguityForest;
	}
}
