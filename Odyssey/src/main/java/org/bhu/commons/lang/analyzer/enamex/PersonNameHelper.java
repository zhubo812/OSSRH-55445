package org.bhu.commons.lang.analyzer.enamex;

import static org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad.LIBRARYLOG;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Entity;
import org.bhu.commons.lang.analyzer.dictionary.StaticDictionaryLoad;
import org.edu.bhu.corpus.utils.Predefine;

public class PersonNameHelper {


	public static HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
//	public static int [][][] nameArray = loader();
	public static int [][][] nameArray = null;

	
	
	public List<Entity> getPersonName(String src) {
		List<Entity> entitylist = new ArrayList<Entity>();
		
		char[] ch = src.toCharArray();
		int i=0;
		int j=0;
		int value = -1;
		int f,s = -1;
		int start=-1;
		int end =-1;
		String substr = "";
		
	
		for(;i<ch.length-1;i++) {
			if(!charMap.containsKey(ch[i])||!charMap.containsKey(ch[i+1])) {
				if(start>-1 && end>start) {
					substr = src.substring(start, end+1);
					entitylist.add(new Entity(substr, start, end, Predefine.NR));
					start =-1;
					end =-1;
				}
				j=0;
				continue;
			}
			f = charMap.get(ch[i]);
			s = charMap.get(ch[i+1]);
			

			value = nameArray[j][f][s];
		
			if(value>0 && start==-1) {
				if(j==0) {
					start =i;
				}	
			}else {
				if(start>-1 && end >start) {
					try {
						substr = src.substring(start, end+1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.err.println(ch[start]);
						System.err.println(end+1);
					}
					entitylist.add(new Entity(substr, start, end, Predefine.NR));
					start =-1;
					end =-1;
					j=0;
					continue;
				}else {
					start =-1;
					end =-1;
					continue;
				}
			}
			if(value==1) {
				start =i;
				j++;
			}
			else if(value ==2) {
				end = i+1;
				substr = src.substring(start, end+1);
				entitylist.add(new Entity(substr, start, end, Predefine.NR));
				start =-1;
				end =-1;
				j=0;
			}
			else if(value==3) {
				end = i+1;
				j++;
			}
			
		}
		
		
		
		return entitylist;
	}
	public static int[][][] loader() {
		nameArray= new int[][][] {new int[3482][3482],new int[3482][3482],new int[3482][3482],new int[3482][3482],new int[3482][3482],new int[3482][3482]};
		char[] ch = Predefine.nameChar.toCharArray();
		for(int i =0;i< ch.length;i++) {
			charMap.put(ch[i], i);
		}
		BufferedReader br = null;


			br = StaticDictionaryLoad.getPersonNameReader();
			String temp = null;
			int i=0;
			int j =0;
			try {
				while ((temp = br.readLine()) != null) {
					
					if(temp.equals("--")) {
						i++;
						j=0;
						continue;
					}
					char[] nch = temp.toCharArray();
					for(int k=0;k<nch.length;k++) {
						nameArray[i][j][k]= Integer.parseInt(String.valueOf(nch[k]));
					}
					j++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LIBRARYLOG.info("init person name matrix ok!");
			return nameArray;
	}
	
	
}
