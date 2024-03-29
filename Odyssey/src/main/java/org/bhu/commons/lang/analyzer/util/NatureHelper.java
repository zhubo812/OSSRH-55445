package org.bhu.commons.lang.analyzer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bhu.commons.lang.analyzer.bean.Term;
import org.bhu.commons.lang.analyzer.bean.TermNature;
import org.bhu.commons.lang.analyzer.library.NatureLibrary;
import org.edu.bhu.corpus.utils.Predefine;

public class NatureHelper {

	private static HashMap<String, Integer> NATURE_INDEX = NatureLibrary.getNATURE_INDEX();
	private static HashMap<String, Double> NATURE_INDEX_DETAIL = NatureLibrary.getNATURE_INDEX_DETAIL();
	private static int[] INIT_NATURE_F = NatureLibrary.getINIT_NATURE_F();
//	private static Term[] terms = null;
	private static double[][] trans_p = NatureLibrary.getTRANS_P();

	public static List<Term> fixer(List<Term> list){
		int[] obs = new int[list.size()+2];
		for(int i=0;i<list.size()+2;i++){
			obs[i]=i;
		}
//		double[] start_p = NatureLibrary.getINIT_NATURE_P();
//		double[][] trans_p = NatureLibrary.getTRANS_P();
//		double[][] emit_p = getEmitPro(list);
//		int[] states = NatureLibrary.getSTATE();
		
//		int [] path = Viterbi.compute(obs, states, start_p, trans_p, emit_p);
		List<Integer>nature_path = getPath(list);
		for(int i =0;i< list.size();i++) {
			if(list.get(i).getNatures().size()==1) {
				continue;
			}
			list.get(i).checkNature(Predefine.natures[nature_path.get(i)]);
		}
		
		
//		return Arrays.asList(terms);
		return list;
	}
	

	
	private static double[][] getEmitPro(List<Term> list) {
		double [][] emit_p = new double [39][list.size()];
		for(int i =0; i< list.size();i++) {
			Term term = list.get(i);
			TermNature[] tns = term.getNatures().termNatures;
			for(int j=0;j< tns.length;j++) {
				int index = NATURE_INDEX.get(tns[j].nature.natureStr);
				emit_p[index][i]= tns[j].frequency+ 1e-16;
				emit_p[index][i]= emit_p[index][i]/(INIT_NATURE_F[index]+1);
			}
			
		}
		
		return emit_p;
	}
	
	
	private static List<Integer> getPath(List<Term> list) {
		List<String> paths = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		paths.add("0");
		for(int i =0; i< list.size();i++) {
			Term term = list.get(i);
			TermNature[] tns = term.getNatures().termNatures;
			temp.clear();
			for(int j=0;j< tns.length;j++) {
				if(NATURE_INDEX_DETAIL.containsKey(tns[j].nature.natureStr)) {
					double index_dt = NATURE_INDEX_DETAIL.get(tns[j].nature.natureStr);
					int index = (int)index_dt;
					for(String path : paths) {
						String new_path = path+";"+ String.valueOf(index);
						temp.add(new_path);
					}
				}else {
					int index=-1;
					for(String path : paths) {
						String new_path = path+";"+ String.valueOf(index);
						temp.add(new_path);
					}
				}
			}
			paths.clear();
			paths.addAll(temp);
		}
		
		int path_index = 0;
		double path_score =0;
		for(int i =0; i< paths.size();i++) {
			String[] items = paths.get(i).split(";");
			double score =1.0;
			for(int j=0;j< items.length-1;j++) {
				if(Integer.parseInt(items[j])==-1||Integer.parseInt(items[j+1])==-1) {
					score *= 1e-25;
				}else {
				int index= Integer.parseInt(items[j]);
				int next = Integer.parseInt(items[j+1]);
				score *= trans_p[index][next];//这里需要再乘以当前词的词性
				}
			}
			if(score > path_score) {
				path_score= score;
				path_index =i;
			}
		}
		String[] items = paths.get(path_index).split(";");
		List<Integer> nature_path = new ArrayList<Integer>();
		for(int i =1;i<items.length;i++) {
			nature_path.add(Integer.parseInt(items[i]));
			
		}
		return nature_path;
	}
	
	public static int getPathNum(List<Term> list) {
		int num = 1;
		for(int i =0; i< list.size();i++) {
			Term term = list.get(i);
			num*=term.getNatures().size();
		}
		return num;
	}
	
	public static List<Term> naturefixer(List<Term> list){
		int num = getPathNum(list);
		List<List<Term>> templist = null;
		if(num > 600) {
			List<Term> result = new ArrayList<Term>();
			templist = Utility.fixedGrouping(list, 3);
			for(int i =0; i< templist.size();i++) {
				result.addAll(fixer(templist.get(i)));
			}
		}
		else {
			return fixer(list);
		}
		return null;
	}
	
	public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {

	    if (null == source || source.size() == 0 || n <= 0)
	        return null;
	    List<List<T>> result = new ArrayList<List<T>>();
	    int remainder = source.size() % n;
	    int size = (source.size() / n);
	    for (int i = 0; i < size; i++) {
	        List<T> subset = null;
	        subset = source.subList(i * n, (i + 1) * n);
	        result.add(subset);
	    }
	    if (remainder > 0) {
	        List<T> subset = null;
	        subset = source.subList(size * n, size * n + remainder);
	        result.add(subset);
	    }
	    return result;
	}
}
