package org.bhu.commons.lang.analyzer.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

	private String path;
	private String encoding;
	private boolean found = false;
	private BufferedReader reader;
	private String line = null;
	private File file;
//	private static final String UTF8 = "utf-8";
	
	/**
	 * 
	 * @param path
	 *            文本的绝对路径 编码默认为UTF-8
	 */
	public FileReader(String path) {
		init(path);
		file = new File(this.path);
		getReader();
	}
	
	public FileReader(String path, String encoding) {
		init(path);
		file = new File(this.path);
		this.encoding = encoding;
		getReader();
	}
	
	public FileReader(File file) {
		init(file.getAbsolutePath());
		this.file = file;
		getReader();
	}
	
	private void init(String path){
		this.path = path;
		try {
//			this.encoding = getCode(path);
			this.encoding = codeString2(path);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public FileReader(InputStream inputStream) {
		getReader(inputStream);
	}
	

	/**
	 * 按行读取文本
	 * 
	 * @return 返回一行
	 */
	public String readLine() {
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * FileReader读取一个文本的所有内容
	 * 
	 * @return 返回一行
	 */
	public String read2End() {
		Long filelength = this.file.length();
		byte[] fileContent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(fileContent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(fileContent, this.encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * FileReader读取已分好词且无分词标记的文本
	 * 
	 * @return 返回包含文本所有词的List，可以有重复值
	 */
	public List<String> readWordSet() {
		List<String> wordset = new ArrayList<String>();
		try {
			for (; (line = reader.readLine()) != null;) {
				line = line.concat(line);
				String[] array = line.split(" ");
				for (String word : array) {
					wordset.add(word);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wordset;
	}

	/***
	 * 
	 * 文本的绝对路径
	 * @return 返回文本所有行的集合List
	 */
	public List<String> read2List() {
		List<String> list = new ArrayList<String>();

				try {
					for (String line; (line = reader.readLine()) != null;) {
						if (line.trim().length() == 0)
							continue;
						list.add(line.trim());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		return list;
	}
	
	@SuppressWarnings("unused")
	private BufferedReader getReader(String path, String encoding) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(this.file), encoding));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}

	private void getReader() {
		try {
			this.reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(this.file), this.encoding));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	private void getReader(InputStream in) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	
	/**
	 * 
	 * @return 返回文档名
	 */
	public String getFileName(){
		return this.file.getName();
	}
	
	/**
	 * 
	 * @return 返回文档的绝对路径
	 */
	public String getAbsolutePath(){
		return this.file.getAbsolutePath();
	}
	
	public String getPath() {
		return this.file.getPath();
	}
	
	public static String codeString(String fileName) throws Exception{  
	    BufferedInputStream bin = new BufferedInputStream(  
	    new FileInputStream(fileName));  
	    int p = (bin.read() << 8) + bin.read();  
	    String code = null;  
	      
	    switch (p) {  
	        case 0xefbb:  
	            code = "UTF-8";  
	            break;  
	        case 0xfffe:  
	            code = "Unicode";  
	            break;  
	        case 0xfeff:  
	            code = "UTF-16BE";  
	            break;  
	        default:  
	            code = "GBK";  
	    }  
	      
	    return code;  
	}  
	
	public static String codeString2(String fileName) throws IOException {

		InputStream inputStream = new FileInputStream(fileName);

		byte[] head = new byte[3];

		inputStream.read(head);



		String code = "GBK";//或GB2312，即ANSI

		if (head[0] == -1 && head[1] == -2 ) //0xFFFE

		code = "UTF-16";

		else if (head[0] == -2 && head[1] == -1 ) //0xFEFF

		code = "Unicode";//包含两种编码格式：UCS2-Big-Endian和UCS2-Little-Endian

		else if(head[0]==-27 && head[1]==-101 && head[2] ==-98)

		code = "UTF-8"; //UTF-8(不含BOM)

		else if(head[0]==-17 && head[1]==-69 && head[2] ==-65)

		code = "UTF-8"; //UTF-8-BOM



		inputStream.close();



		//System.out.println(code);

		return code;

		}
	
	public void close() {
		try {
			this.reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
	
}
