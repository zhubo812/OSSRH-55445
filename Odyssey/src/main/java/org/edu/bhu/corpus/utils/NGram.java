package org.edu.bhu.corpus.utils;

import java.util.ArrayList;
import java.util.List;

/***
 * 类描述：构建N-Gram模型
 * @author Jackie
 *
 */
public class NGram {
	public List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

	public List<String> ngrams(int n, String... words) {
        List<String> ngrams = new ArrayList<String>();
        //String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }
	
	public List<String> ngrams(int n, char[] chars) {
        List<String> ngrams = new ArrayList<String>();
        //String[] words = str.split(" ");
        List<String> words = new ArrayList<String>();
        for(char c : chars) {
        	words.add(String.valueOf(c));
        }
        for (int i = 0; i < chars.length - n + 1; i++)
            ngrams.add(concat(words.toArray(new String[words.size()]), i, i+n));
        return ngrams;
    }
	
	public List<String> ngrams(int n, List<String> words) {
        List<String> ngrams = new ArrayList<String>();
        String[] wordsArray = words.toArray(new String[words.size()]);
        for (int i = 0; i < wordsArray.length - n + 1; i++)
            ngrams.add(concat(wordsArray, i, i+n));
        return ngrams;
    }
    private String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? "\t" : "") + words[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
    	NGram ng = new NGram();
        for (int n = 1; n <= 3; n++) {
            for (String ngram : ng.ngrams(n, "This is my car."))
                System.out.println(ngram);
            System.out.println();
        }
    }
    

    public void tester() {
    	NGram ng = new NGram();
      
            for (String ngram : ng.ngrams(2, "哈利佩尔托".toCharArray()))
                System.out.println(ngram);
            System.out.println();
        
    }
}
