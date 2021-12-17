package org.bhu.commons.lang.analyzer.bean;

import java.util.ArrayList;
import java.util.List;

public class Lexicon {


	List<Integer> natures;
	List<Integer> nextlist;
	StringBuilder sb;
	String separator = ";";
	public Lexicon() {
		super();
		// TODO Auto-generated constructor stub
		this.nextlist = new ArrayList<Integer>();
		this.natures = new ArrayList<Integer>();
	}
	
	public void addNext(int index) {
		if(!this.nextlist.contains(index)) {
			this.nextlist.add(index);
		}
	}
	
	public void addNature(int index) {
		if(!this.natures.contains(index)) {
			this.natures.add(index);
		}
	}
	
	

	public List<Integer> getNatures() {
		return natures;
	}

	public void setNatures(List<Integer> natures) {
		this.natures = natures;
	}
	
	public String getInfos() {
		return getNaturesLine()+","+getNextLine();
	}

	private String getNaturesLine() {
		sb = new StringBuilder();
		for(int i : this.natures) {
			sb.append(i).append(separator);
		}
		return sb.toString();
	}
	
	private String getNextLine() {
		sb = new StringBuilder();
		for(int i : this.nextlist) {
			sb.append(i).append(separator);
		}
		return sb.toString();
	}
	
	public List<Integer> getNextlist() {
		return nextlist;
	}
	public void setNextlist(List<Integer> nextlist) {
		this.nextlist = nextlist;
	}
	
	
	public int getTermStatus() {
		int len = natures.size();
		if(len >1) {
			if(natures.contains(0)) {//多词性标记，可能包含0
				return 1;//有词性，也有后接内容
			}else {
				return 2;//有多个词性，无后接内容
			}
		}else {//单词性标记，可能包含0
			if(natures.contains(0)) {
				return 0;//无词性，有后接内容
			}
		}
		return 2;//有一个词性，无后接内容
		
	}

	public List<Integer> getNatureList() {
		List<Integer> list = new ArrayList<Integer>();
		for(int nature : natures) {
			if(nature==0) {
				continue;
			}
			list.add(nature);
		}
		return list;
	}
	
}
