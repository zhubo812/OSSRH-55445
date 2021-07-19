package org.bhu.commons.lang.analyzer.enamex;

import java.util.ArrayList;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.bean.TermNatures;
import org.edu.bhu.corpus.utils.Predefine;

public class NumberHelper {

	
	char[] numFirstChar = Predefine.numberFirstChar.toCharArray();
	char[] numChar = Predefine.numberChar.toCharArray();
	List<Character> firstCharList;
	List<Character> numCharList;
	
	public NumberHelper() {
		init();
	}
	
	
	public void init(){
		firstCharList = new ArrayList<Character>();
		numCharList = new ArrayList<Character>();
		for(char c : numFirstChar) {
			firstCharList.add(c);
			numCharList.add(c);
		}
		
		for(char c : numChar) {
			numCharList.add(c);
		}
	}
	
	public List<Entity> getNumber(String src) {
		List<Entity> entitylist = new ArrayList<Entity>();
		char[] ch = src.toCharArray();
		
		int start, end = 0;
		
		for (int i = 0; i < ch.length - 1; i++) {
				if(firstCharList.contains(ch[i])) {
					start =i;
					do {
						end = i++;
						if (i >= ch.length) {
							break;
						}
						
					} while (numCharList.contains(ch[i]));
					if(end > start) {
						String substr = src.substring(start, end+1);
						entitylist.add(new Entity(substr, start, end, TermNatures.M));
						i--;
					}
				}
		}

		return entitylist;
	}
}
