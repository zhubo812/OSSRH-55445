package org.bhu.commons.lang.analyzer.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Uniword {

	HashMap<Integer, UniwordNature> hm = null;
	List<NatureInfo> natures;
	private TermNatures termNatures = null ;
	
	

	public Uniword() {
		hm = new HashMap<Integer, UniwordNature>();
	}

//	public void add(int idx, List<Integer> natures) {
//		hm.put(idx, natures);
//	}

	public void add(int idx, NatureInfo nInfo) {
		if (hm.containsKey(idx)) {
			UniwordNature uniwordNature= hm.get(idx);
			uniwordNature.getNatureList().add(nInfo);
			hm.put(idx, uniwordNature);
		} else {
			List<NatureInfo> list = new ArrayList<NatureInfo>();
			list.add(nInfo);
			UniwordNature uniwordNature =  new UniwordNature();
			uniwordNature.setNatureList(list);
			hm.put(idx, uniwordNature);
		}
	}

	public UniwordNature get(int idx) {
		return hm.get(idx);
	}

	public Set<Integer> keySet() {
		return hm.keySet();
	}

	public boolean containsKey(int idx) {
		return this.hm.containsKey(idx);
	}


	public void loadTermNatures(int idx,HashMap<Integer, String> natureMapFlec) {
		UniwordNature uniwordNature= hm.get(idx);
		List<NatureInfo> list = uniwordNature.getNatureList();
		TermNature[] all = new TermNature[list.size()];
		for (int i = 0; i < list.size(); i++) {
			all[i] = new TermNature(natureMapFlec.get(list.get(i).getNatureidx()), list.get(i).getFreq());
		}
		TermNatures termNatures = new TermNatures(all);
		uniwordNature.setTermNatures(termNatures);
		hm.put(idx, uniwordNature);
	}


	public class UniwordNature {
		List<NatureInfo> natureList;
		TermNatures termNatures;
		
		
		public List<NatureInfo> getNatureList() {
			return natureList;
		}
		public void setNatureList(List<NatureInfo> natureList) {
			this.natureList = natureList;
		}
		public TermNatures getTermNatures() {
			return termNatures;
		}
		public void setTermNatures(TermNatures termNatures) {
			this.termNatures = termNatures;
		}
		
	}
}
