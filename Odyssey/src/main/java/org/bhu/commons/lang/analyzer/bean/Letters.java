package org.bhu.commons.lang.analyzer.bean;

import java.util.HashMap;

public class Letters {

	HashMap<Character, Integer> hm = null;
	
	public Letters() {
		hm = new HashMap<Character, Integer>();
		//1=> en
		for (int i = 'a'; i <= 'z'; i++) {
			hm.put((char)i, 1);
		}
		
		for (int i = 'ａ'; i <= 'ｚ'; i++) {
			hm.put((char)i, 1);
		}
		
		for (int i = 'Ａ'; i <= 'Ｚ'; i++) {
			hm.put((char)i, 1);
		}
		
		for (int i = 'A'; i <= 'Z'; i++) {
			hm.put((char)i, 1);
		}
		
		//2=> Number,num
		for (int i = '0'; i <= '9'; i++) {
			hm.put((char)i, 2);
		}
		for (int i = '０'; i <= '９'; i++) {
			hm.put((char)i, 2);
		}
		
		//3=> Russian Letter, ru
		for (int i = 'а'; i <= 'я'; i++) {
			hm.put((char)i, 3);
		}
		
		for (int i = 'А'; i <= 'Я'; i++) {
			hm.put((char)i, 3);
		}
		
		//4=> Greek Letter,gk
		for (int i = 945; i <= 969; i++) {
			if (i == 962)continue;
			hm.put((char)i, 4);
		}
		
		for (int i = 913; i <= 937; i++) {
			if (i == 930)continue;
			hm.put((char)i, 4);
		}
		
		//5=> 序号 serial number, sn
		for (int i = 9312; i <= 9470; i++){
			hm.put((char)i, 5);
		}
	}
	
	public int status(char c) {
		if(this.hm.containsKey(c)) {
			return hm.get(c);
		}
		return -1;
	}
	
	
	
}
