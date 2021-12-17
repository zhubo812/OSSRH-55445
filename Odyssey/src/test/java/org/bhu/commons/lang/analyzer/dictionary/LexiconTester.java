package org.bhu.commons.lang.analyzer.dictionary;

import static org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad.LIBRARYLOG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Lexicon;
import org.junit.Test;

public class LexiconTester {
	public HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
	Lexicon[][][] lex = new Lexicon[][][] {new Lexicon[8][8],new Lexicon[8][8],new Lexicon[8][8],new Lexicon[8][8]};
	
	
	@Test
	public void tester() {
		List<String> words = new ArrayList<String>();
		words.add("爱田麻美");
		words.add("爱田毛毛");
		words.add("爱田露香");
		words.add("爱田友");
		int nr = 4;
		int index = 0;
		for(String word : words) {
			char[]ch = word.toCharArray();
			
			for(char c : ch) {
//				String s = String.valueOf(c);
				if(charMap.containsKey(c)) {
					continue;
				}else {
					charMap.put(c, index);
					index++;
				}
			}
			for(int i =0;i<ch.length-1;i++) {
				int f = charMap.get(ch[i]);
				int s = charMap.get(ch[i+1]);
				lex[i][f][s] = getItem(i,f,s);
				if(i==ch.length-2) {
					lex[i][f][s].addNature(nr);
				}else {
					lex[i][f][s].addNature(0);//初始值-1；0为词的字串；1-80为具体词性；
				}
				if(i >0) {
					lex[i-1][charMap.get(ch[i-1])][charMap.get(ch[i])].addNext(s);
				}
			}
		}
		LIBRARYLOG.info("init person name matrix ok!");
	}
	private Lexicon getItem(int i, int f, int s) {
		Lexicon temp = lex[i][f][s];
		if(temp == null) {
			return new Lexicon();
		}
		return temp;
	}
	
	
}
