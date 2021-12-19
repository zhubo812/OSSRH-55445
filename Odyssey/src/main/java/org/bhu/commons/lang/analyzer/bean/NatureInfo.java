package org.bhu.commons.lang.analyzer.bean;

public class NatureInfo {
	int natureidx;
	int freq;
	
	public NatureInfo(int natureidx,int freq) {
		this.natureidx= natureidx;
		this.freq = freq;
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
}
