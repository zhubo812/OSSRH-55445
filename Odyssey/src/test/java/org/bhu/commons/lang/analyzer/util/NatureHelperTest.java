package org.bhu.commons.lang.analyzer.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NatureHelperTest {

	
	@Test
	public void tester() {
		List<String> list = new ArrayList<String>();
		for(int i=0;i< 10; i++) {
			list.add(String.valueOf(i));
		}
		
		fixedGrouping(list, 3);
	}
	
	
	public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {

	    if (null == source || source.size() == 0 || n <= 0)
	        return null;
	    List<List<T>> result = new ArrayList<List<T>>();
	    int remainder= 0;
	    for (int i = 0; i < source.size()-2; i++) {
	        List<T> subset = null;
	        subset = source.subList(i * (n-1), i * (n-1)+n);
	        System.out.println(subset);
	        result.add(subset);
	        remainder = source.size() -(i * (n-1)+n);
	    }
	    if (remainder > 0) {
	        List<T> subset = null;
	        subset = source.subList(remainder,source.size()-1);
	        result.add(subset);
	    }
	    return result;
	}
}
