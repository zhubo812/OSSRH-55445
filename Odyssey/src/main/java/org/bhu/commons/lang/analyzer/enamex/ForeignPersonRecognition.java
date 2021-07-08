package org.bhu.commons.lang.analyzer.enamex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.Nature;
import org.bhu.commons.lang.analyzer.bean.NewWord;
import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.bean.TermNatures;
import org.bhu.commons.lang.analyzer.util.StringUtil;
import org.bhu.commons.lang.analyzer.util.TermUtil;
import org.edu.bhu.corpus.utils.Predefine;

/**
 * 外国人名识别
 * 
 * @author Jackie
 */
public class ForeignPersonRecognition {
	
	private char[] nameArray = Predefine.foreignNameChar.toCharArray();
	private HashMap<Character, Integer> nameMap = null;
	private int[][] matrix = new int[410][8];

	private static final LinkedList<NameChar> PRLIST = new LinkedList<NameChar>();

	private static NameChar INNAME = null;

	private static HashSet<Character> ISNOTFIRST = new HashSet<Character>();


	private List<Term> tempList = new ArrayList<Term>();
	private LinkedList<NameChar> prList = null;
	private Term[] terms = null;

	public ForeignPersonRecognition(Term[] terms) {
		this.terms = terms;
		loadMatrix();
	}
	
	public ForeignPersonRecognition() {
		loadMatrix();
		loadNameMap();
	}
	
	private void loadMatrix() {

		String[] lines = Predefine.foreignNameMatrix;
		for(int i =0; i< lines.length;i++) {
			String[] items = lines[i].split(" ");
			for(int j =0; j< items.length;j++) {
				matrix[i][j]= Integer.parseInt(items[j]);
			}
		}
	}
	
	private void loadNameMap() {
		nameMap = new HashMap<Character, Integer>();
		for(int i =0;i< nameArray.length;i++) {
			nameMap.put(nameArray[i], i);
		}
	}
	
	public List<Entity> recognition(String line) {
		
		List<Entity> entitylist = new ArrayList<Entity>();
		char[] ch = line.toCharArray();
		int start=0;
		int end =0;
		for(int i =0; i< ch.length;i++) {

			if(nameMap.containsKey(ch[i])) {
				start =i;
				do {
					end = i++;
					if (i >= ch.length) {
						break;
					}
				} while (nameMap.containsKey(ch[i]));
				if(end > start) {
					for(int j = start; j<= end;j++) {
						int row = nameMap.get(ch[j]);
						int col = j- start+1;
						if(matrix[row][col] == 0) {
							end = i;
							break;
						}
					}
					String substr = line.substring(start, end+1);
					entitylist.add(new Entity(substr, start, end, TermNatures.NR));
//					System.out.println(substr);
				}
			}
			
			
		}

		return entitylist;
	}

	public void recognition() {
		String name = null;
		Term term = null;
		reset();
		for (int i = 0; i < terms.length; i++) {
			if (terms[i] == null) {
				continue;
			}

			term = terms[i];
			// 如果名字的开始是人名的前缀,或者后缀.那么忽略
			if (tempList.size() == 0) {
				if (term.getNatures().personAttr.end > 10) {
					continue;
				}

				if ((terms[i].getName().length() == 1 && ISNOTFIRST.contains(terms[i].getName().charAt(0)))) {
					continue;
				}
			}

			name = term.getName();

			if (term.getNatures() == TermNatures.NR || term.getNatures() == TermNatures.NW || name.length() == 1) {
				boolean flag = validate(name);
				if (flag) {
					tempList.add(term);
				}
			} else if (tempList.size() == 1) {
				reset();
			} else if (tempList.size() > 1) {
				TermUtil.insertTerm(terms, tempList, TermNatures.NR);
				reset();
			}
		}
	}

	private boolean validate(String name) {
		boolean flag = false;
		NameChar nameChar = null;
		for (int j = 0; j < prList.size(); j++) {
			nameChar = prList.get(j);
			if (nameChar.contains(name)) {
				flag = true;
			} else {
				prList.remove(j);
				// 向后回退一位
				j--;
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	private void reset() {
		// TODO Auto-generated method stub
		tempList.clear();
		prList = (LinkedList<NameChar>) PRLIST.clone();
	}

	public static boolean isFName(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (!INNAME.contains(name.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private static class NameChar {
		private char[] chars = null;

		public NameChar(char[] chars) {
			this.chars = chars;
		}

		public boolean contains(String name) {
			return contains(name.charAt(0));
		}

		public boolean contains(char c) {
			return Arrays.binarySearch(chars, c) > -1;
		}
	}

	public List<NewWord> getNewWords() {
		List<NewWord> all = new ArrayList<NewWord>();
		String name = null;
		Term term = null;
		reset();
		for (int i = 0; i < terms.length; i++) {
			if (terms[i] == null) {
				continue;
			}

			term = terms[i];
			// 如果名字的开始是人名的前缀,或者后缀.那么忽略
			if (tempList.size() == 0) {
				if (term.getNatures().personAttr.end > 10) {
					continue;
				}

				if ((terms[i].getName().length() == 1 && ISNOTFIRST.contains(terms[i].getName().charAt(0)))) {
					continue;
				}
			}

			name = term.getName();
			if (term.getNatures() == TermNatures.NR || term.getNatures() == TermNatures.NW || name.length() == 1) {
				boolean flag = validate(name);
				if (flag) {
					tempList.add(term);
				}
			} else if (tempList.size() == 1) {
				reset();
			} else if (tempList.size() > 1) {
				StringBuilder sb = new StringBuilder();
				for (Term temp : tempList) {
					sb.append(temp.getName());
				}
				all.add(new NewWord(sb.toString(), Nature.NRF));
				reset();
			}
		}
		return all;
	}

	public List<Term> getNewTerms() {
		LinkedList<Term> result = new LinkedList<Term>();
		String name = null;
		Term term = null;
		reset();
		for (int i = 0; i < terms.length; i++) {
			if (terms[i] == null) {
				continue;
			}

			term = terms[i];
			// 如果名字的开始是人名的前缀,或者后缀.那么忽略
			if (tempList.size() == 0) {
				if (term.getNatures().personAttr.end > 10) {
					continue;
				}

				if ((terms[i].getName().length() == 1 && ISNOTFIRST.contains(terms[i].getName().charAt(0)))) {
					continue;
				}
			}

			name = term.getName();

			if (term.getNatures() == TermNatures.NR || term.getNatures() == TermNatures.NW || name.length() == 1) {
				boolean flag = validate(name);
				if (flag) {
					tempList.add(term);
				}
			} else if (tempList.size() == 1) {
				reset();
			} else if (tempList.size() > 1) {
				result.add(makeNewTerm());
				reset();
			}
		}
		return result;
	}

	public Term makeNewTerm() {
		StringBuilder sb = new StringBuilder();
		int offe = tempList.get(0).getOffe();
		for (Term term : tempList) {
			sb.append(term.getName());
		}
		return new Term(sb.toString(), offe, TermNatures.NR);
	}
}
