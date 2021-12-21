package org.bhu.commons.lang.analyzer.bean;

public class NatureInfo {
	int natureidx;
	int freq;
	int from;
	
	public NatureInfo(int natureidx,int freq) {
		this.natureidx= natureidx;
		this.freq = freq;
	}
	
	public NatureInfo(int natureidx,int freq, int from) {
		this.natureidx= natureidx;
		this.freq = freq;
		this.from = from;
	}
	
	public void setFromIdx(int fromIdx) {
		this.from = fromIdx;
	}
	
	public int getNatureidx() {
		return natureidx;
	}
	public void setNatureidx(int natureidx) {
		this.natureidx = natureidx;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}
	
	
}
