package org.bhu.commons.lang.analyzer.util;

import org.junit.Test;

public class MatrixUtilsTest {

	@Test
	public void file2MatirxTester() {
		String inPath = "E:\\BaiduNetdiskDownload\\2\\ChineseNames_120W.txt";
		String outPath = "E:/BaiduNetdiskDownload/2/nameMatrix.txt";
		MatrixUtil mu = new MatrixUtil();
		mu.file2Matirx(inPath, outPath);
	}
}
