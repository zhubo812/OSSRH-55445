package org.bhu.test;

import org.edu.bhu.corpus.utils.NrBuilder;
import org.junit.Test;

public class NrBuilderTest {

	@Test
	public void tester() {
		NrBuilder nb = new NrBuilder();
		String path = "E:/BaiduNetdiskDownload/2/English_Cn48W.txt";
		String[][] matrix = nb.buider(path);
	}
}
