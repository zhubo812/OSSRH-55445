package org.bhu.commons.lang.analyzer.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

public class Lexicon {

//	List<NatureInfo> natures;
	HashMap<Integer, HashMap<Integer, Integer>> natureMap;// HashMap<词性ID,HashMap<前字ID,词频>>
	HashMap<Integer, Integer> wordFreqMap;
	List<Integer> numNatureList;
	List<Integer> nextlist;
//	List<Integer> fromList;
	StringBuilder sb;
	String separator = ",";

	private TermNatures termNatures = null;

	public Lexicon() {
		super();
		// TODO Auto-generated constructor stub
		this.nextlist = new ArrayList<Integer>();
//		this.natures = new ArrayList<NatureInfo>();
		this.numNatureList = new ArrayList<Integer>();
//		this.fromList = new ArrayList<Integer>();
		this.natureMap = new HashMap<Integer, HashMap<Integer, Integer>>();
		this.wordFreqMap = new HashMap<Integer, Integer>();
	}

	public void addNature(int natureIdx, int fromCharIdx, int freq) {
		addNatureList(natureIdx);
		if (natureMap.containsKey(natureIdx)) {
			HashMap<Integer, Integer> fromMap = natureMap.get(natureIdx);
			if (!fromMap.containsKey(fromCharIdx)) {
				fromMap.put(fromCharIdx, freq);
			}
		} else {
			HashMap<Integer, Integer> fromMap = new HashMap<Integer, Integer>();
			fromMap.put(fromCharIdx, freq);
			natureMap.put(natureIdx, fromMap);
		}
	}
	

	
	public TermNatures geTermNatures() {
		return termNatures;
	}

	
	
	
	public TermNatures geTermNatures(int fromCharId, HashMap<Integer, String> natureMapFlec) {
		List<TermNature> list = new ArrayList<TermNature>();
		for (int natureID : this.natureMap.keySet()) {
			HashMap<Integer, Integer> temp = this.natureMap.get(natureID);
			if (temp.containsKey(fromCharId)) {
				list.add(new TermNature(natureMapFlec.get(natureID), temp.get(fromCharId)));
			}
		}
		return  new TermNatures(list);
	}

	public void addMidTag() {
		if (!this.numNatureList.contains(0)) {
			this.numNatureList.add(0);
		}
	}

	public void addNatureFreq(int natureIdx, int freq,String nature) {
		addNatureList(natureIdx);
		if (!wordFreqMap.containsKey(natureIdx)) {
			wordFreqMap.put(natureIdx, freq);
			setNatureStrToArray(nature);
		}
		
	}

	public void addNatureList(int natureIdx) {
		if (!this.numNatureList.contains(natureIdx)) {
			this.numNatureList.add(natureIdx);
		}
	}

	public void addNext(int index) {
		if (!this.nextlist.contains(index)) {
			this.nextlist.add(index);
		}
	}

	

public String getInfos() {
	StringBuilder sb = new StringBuilder();
	sb.append(getNatureSet()).append("\t");
	sb.append(getNextSet()).append("\t");
	sb.append(getBiWordInfo()).append("\t");
	sb.append(getMultiWordInfos());
	
	return sb.toString();
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



	/**
	 * 写出词性集合
	 * @return
	 */
	public String getNatureSet() {
		return getNaturesSetLine();
	}
	
	/***
	 * 写出后字集合
	 * @return
	 */
	public String getNextSet() {
		return getNextLine();
	}
	
	
	/****
	 * 写出双字词词性词频
	 * @return
	 */
	public String getBiWordInfo() {
		return getBiwordString();
	}
	
	
	/***
	 * 写出多字词词性及词频
	 * @return
	 */
	public String getMultiWordInfos() {
		return getNatureNextFreq();
	}

	
	private String getBiwordString() {
		JSONObject gson = new JSONObject();
		for (int i : this.wordFreqMap.keySet()) {
			gson.put(String.valueOf(i), this.wordFreqMap.get(i));
		}
		if(gson.isEmpty()) {
			return " ";
		}
		return gson.toString();
	}
	
	private String getNatureNextFreq() {
		JSONObject gs = new JSONObject();
		for(int i : this.numNatureList) {
			if(i ==0) {
				continue;
			}
			JSONObject gson = new JSONObject();
			HashMap<Integer, Integer> map = natureMap.get(i);
			if(map==null) {
//				System.out.println();
				return " ";
			}
			for (int j: map.keySet()) {
				gson.put(String.valueOf(j), map.get(j));
			}
			gs.put(String.valueOf(i), gson);
		}
		if(gs.isEmpty()) {
			return " ";
		}
		return gs.toString();
	}
	private String getNaturesSetLine() {
		sb = new StringBuilder();
		for (int i : this.numNatureList) {
			sb.append(i).append(separator);
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

		return numNatureList;
	}

//	public void setTermNatures(HashMap<Integer, String> natureMapFlec) {
//
//		setNatureStrToArray(natureMapFlec);
//	}
//
	private TermNatures setNatureStrToArray(String nature) {

		if(this.wordFreqMap.size()==0) {
			return null;
		}
		TermNature[] all = new TermNature[this.wordFreqMap.size()];
		int maxFreq = -1;
		int i = 0;
		int maxIndx=0;
		for(int natureID : wordFreqMap.keySet()) {
			if (maxFreq < wordFreqMap.get(natureID)) {
				maxFreq =  wordFreqMap.get(natureID);
				maxIndx= i;
			}
			all[i] = new TermNature(nature, wordFreqMap.get(natureID));
			i++;
		}
		this.termNatures = new TermNatures(all);
		termNatures.nature = all[maxIndx].nature;
		return termNatures;
	}

}
