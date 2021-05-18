package org.bhu.test;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.util.List;

import org.bhu.commons.lang.analyzer.util.FileReader;
import org.bhu.commons.lang.analyzer.util.FileWriter;
import org.bhu.commons.lang.analyzer.util.TokenizeUtils;

public class ToAnalysisFileTester {
	public static void main(String[] args) throws IOException {

		String[] filenames = { "汽车测试语料.txt", "电子测试语料.txt" };

		TokenizeUtils tokenizer = new TokenizeUtils();
		tokenizer.initUsrDic();

		long length = 0L;
		long start = System.currentTimeMillis();

		for (String name : filenames) {
			FileReader reader = new FileReader("data/" + name);
			List<String> lines = reader.read2List();
			FileWriter writer = new FileWriter("data/" + name.replace(".txt", "")+"_seg.txt");
			for (String line : lines) {
				int index = line.indexOf(">");
				String sline = line.substring(index + 1);
				sline = sline.substring(0,sline.indexOf("</"));
//				System.out.println(sline);
				String rs = tokenizer.getWordNatureLine(sline);
				writer.writeLine(rs);

			}
			writer.close();
			long elapsed = (System.currentTimeMillis() - start);
			System.out.println(String.format("time elapsed:%d, rate:%fkb/s", elapsed,
					(length * 1.0) / 1024.0f / (elapsed * 1.0 / 1000.0f)));
		}
	}
}
