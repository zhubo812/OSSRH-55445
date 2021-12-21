package org.bhu.commons.lang.analyzer.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lexicon {

	List<NatureInfo> natures;
	List<Integer> numNatureList;
	List<Integer> nextlist;
	List<Integer> fromList;
	StringBuilder sb;
	String separator = ";";

	private TermNatures termNatures = null;

	public Lexicon() {
		super();
		// TODO Auto-generated constructor stub
		this.nextlist = new ArrayList<Integer>();
		this.natures = new ArrayList<NatureInfo>();
		this.numNatureList = new ArrayList<Integer>();
		this.fromList = new ArrayList<Integer>();
	}

	public void addNext(int index) {
		if (!this.nextlist.contains(index)) {
			this.nextlist.add(index);
		}
	}

	public void addNature(NatureInfo index) {
		if (!this.numNatureList.contains(index.getNatureidx())) {
			this.natures.add(index);
			this.numNatureList.add(index.getNatureidx());
			this.fromList.add(index.getFrom());
		}
	}
	
	public void addNatureIdx(int idx) {
		if (!this.numNatureList.contains(idx)) {

			this.numNatureList.add(idx);
		}
	}

//	public TermNatures geTermNatures(int from) {
//		for(int i =0; i< termNatures.termNatures.length;i++) {
//			termNatures.termNatures[i].fromList.contains(from);
//			termNatures.nature= termNatures.termNatures[i].nature;
//		}
//		return termNatures;
//	}
	
	public TermNatures geTermNatures() {
		return termNatures;
	}

	public List<NatureInfo> getNatures() {
		return natures;
	}

	public void setNatures(List<NatureInfo> natures) {
		this.natures = natures;
	}

	public String getInfos() {
		return getNaturesLine() + "," + getNextLine();
	}

	private String getNaturesLine() {
		sb = new StringBuilder();
		for (NatureInfo i : this.natures) {
			if (i.natureidx > 0) {
				sb.append(i.natureidx).append("/").append(i.freq).append(separator);
			} else {
				sb.append(i.natureidx).append(separator);
			}
		}
		return sb.toString();
	}

	private String getNextLine() {
		sb = new StringBuilder();
		for (int i : this.nextlist) {
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
		int len = numNatureList.size();
		if (len > 1) {
			if (numNatureList.contains(0)) {// 多词性标记，可能包含0
				return 1;// 有词性，也有后接内容
			} else {
				return 2;// 有多个词性，无后接内容
			}
		} else {// 单词性标记，可能包含0
			if (numNatureList.contains(0)) {
				return 0;// 无词性，有后接内容
			}
		}
		return 2;// 有一个词性，无后接内容

	}

	public List<Integer> getNatureList() {
		List<Integer> list = new ArrayList<Integer>();
		for (NatureInfo nature : natures) {
			if (nature.natureidx == 0) {
				continue;
			}
			list.add(nature.natureidx);
		}
		return list;
	}

	public void setTermNatures(HashMap<Integer, String> natureMapFlec) {

		setNatureStrToArray(natureMapFlec);
	}

	private TermNatures setNatureStrToArray(HashMap<Integer, String> natureMapFlec) {

		if(this.natures.size()==0) {
			return null;
		}
		TermNature[] all = new TermNature[this.natures.size()];
		int maxFreq = -1;
		int maxIndx = 0;
		for (int i = 0; i < natures.size(); i++) {
			if (maxFreq < natures.get(i).freq) {
				maxFreq = natures.get(i).freq;
				maxIndx = i;
			}
			all[i] = new TermNature(natureMapFlec.get(natures.get(i).getNatureidx()), natures.get(i).getFreq());
		}
//		System.out.println(this.natures.size());
		termNatures = new TermNatures(all);
		termNatures.nature = all[maxIndx].nature;
		return termNatures;
	}

}
