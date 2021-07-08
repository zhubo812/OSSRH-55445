package org.bhu.commons.lang.analyzer.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Utility {
	private static final String seg = "/";
	public static final int CC_NUM = 6768;

	// The number of Chinese Char,including 5 empty position between 3756-3761
	public static final int WORD_MAXLENGTH = 100;

	public static final int WT_DELIMITER = 0;

	public static final int WT_CHINESE = 1;

	public static final int WT_OTHER = 2;

	public static final int CT_SENTENCE_BEGIN = 1;// Sentence begin

	public static final int CT_SENTENCE_END = 4;// Sentence ending

	public static final int CT_SINGLE = 5;// SINGLE byte

	public static final int CT_DELIMITER = CT_SINGLE + 1;// delimiter

	public static final int CT_CHINESE = CT_SINGLE + 2;// Chinese Char

	public static final int CT_LETTER = CT_SINGLE + 3;// HanYu Pinyin

	public static final int CT_NUM = CT_SINGLE + 4;// HanYu Pinyin

	public static final int CT_INDEX = CT_SINGLE + 5;// HanYu Pinyin

	public static final int CT_OTHER = CT_SINGLE + 12;// Other

	public static final int MAX_WORDS = 650;

	public static final int MAX_SEGMENT_NUM = 10;

	public static final String POSTFIX_SINGLE = "坝邦堡杯城池村单岛道堤店洞渡队法峰府冈港阁宫沟国海号河湖环集江奖礁角街井郡坑口矿里岭楼路门盟庙弄牌派坡铺旗桥区渠泉人山省市水寺塔台滩坛堂厅亭屯湾文屋溪峡县线乡巷型洋窑营屿语园苑院闸寨站镇州庄族陂庵町";

	public static final String[] POSTFIX_MUTIPLE = { "半岛", "草原", "城市", "大堤", "大公国", "大桥", "地区", "帝国", "渡槽", "港口",
			"高速公路", "高原", "公路", "公园", "共和国", "谷地", "广场", "国道", "海峡", "胡同", "机场", "集镇", "教区", "街道", "口岸", "码头", "煤矿",
			"牧场", "农场", "盆地", "平原", "丘陵", "群岛", "沙漠", "沙洲", "山脉", "山丘", "水库", "隧道", "特区", "铁路", "新村", "雪峰", "盐场", "盐湖",
			"渔场", "直辖市", "自治区", "自治县", "自治州", "" };

	public static final String TRANS_ENGLISH = "·—阿埃艾爱安昂敖奥澳笆芭巴白拜班邦保堡鲍北贝本比毕彼别波玻博勃伯泊卜布才采仓查差柴彻川茨慈次达大戴代丹旦但当道德得的登迪狄蒂帝丁东杜敦多额俄厄鄂恩尔伐法范菲芬费佛夫福弗甫噶盖干冈哥戈革葛格各根古瓜哈海罕翰汗汉豪合河赫亨侯呼胡华霍基吉及加贾坚简杰金京久居君喀卡凯坎康考柯科可克肯库奎拉喇莱来兰郎朗劳勒雷累楞黎理李里莉丽历利立力连廉良列烈林隆卢虏鲁路伦仑罗洛玛马买麦迈曼茅茂梅门蒙盟米蜜密敏明摩莫墨默姆木穆那娜纳乃奈南内尼年涅宁纽努诺欧帕潘畔庞培佩彭皮平泼普其契恰强乔切钦沁泉让热荣肉儒瑞若萨塞赛桑瑟森莎沙山善绍舍圣施诗石什史士守斯司丝苏素索塔泰坦汤唐陶特提汀图土吐托陀瓦万王旺威韦维魏温文翁沃乌吾武伍西锡希喜夏相香歇谢辛新牙雅亚彦尧叶依伊衣宜义因音英雍尤于约宰泽增詹珍治中仲朱诸卓孜祖佐伽娅尕腓滕济嘉津赖莲琳律略慕妮聂裴浦奇齐琴茹珊卫欣逊札哲智兹芙汶迦珀琪梵斐胥黛";

	public static final String TRANS_RUSSIAN = "·阿安奥巴比彼波布察茨大德得丁杜尔法夫伏甫盖格哈基加坚捷金卡科可克库拉莱兰勒雷里历利连列卢鲁罗洛马梅蒙米姆娜涅宁诺帕泼普奇齐乔切日萨色山申什斯索塔坦特托娃维文乌西希谢亚耶叶依伊以扎佐柴达登蒂戈果海赫华霍吉季津柯理琳玛曼穆纳尼契钦丘桑沙舍泰图瓦万雅卓兹";

	public static final String TRANS_JAPANESE = "安奥八白百邦保北倍本比滨博步部彩菜仓昌长朝池赤川船淳次村大代岛稻道德地典渡尔繁饭风福冈高工宫古谷关广桂贵好浩和合河黑横恒宏后户荒绘吉纪佳加见健江介金今进井静敬靖久酒菊俊康可克口梨理里礼栗丽利立凉良林玲铃柳隆鹿麻玛美萌弥敏木纳南男内鸟宁朋片平崎齐千前浅桥琴青清庆秋丘曲泉仁忍日荣若三森纱杉山善上伸神圣石实矢世市室水顺司松泰桃藤天田土万望尾未文武五舞西细夏宪相小孝新星行雄秀雅亚岩杨洋阳遥野也叶一伊衣逸义益樱永由有佑宇羽郁渊元垣原远月悦早造则泽增扎宅章昭沼真政枝知之植智治中忠仲竹助椎子佐阪坂堀荻菅薰浜濑鸠筱";

	// Translation type
	public static final int TT_ENGLISH = 0;

	public static final int TT_RUSSIAN = 1;

	public static final int TT_JAPANESE = 2;

	// Seperator type
	public static final String SEPERATOR_C_SENTENCE = "。！？：；…";

	public static final String SEPERATOR_C_SUB_SENTENCE = "、，（）“”‘’";

	public static final String SEPERATOR_E_SENTENCE = "!?:;";

	public static final String SEPERATOR_E_SUB_SENTENCE = ",()\"'";

	public static final String SEPERATOR_LINK = "\n\r 　";

	// Sentence begin and ending string
	public static final String SENTENCE_BEGIN = "始##始";

	public static final String SENTENCE_END = "末##末";

	// Seperator between two words
	public static final String WORD_SEGMENTER = "@";

	public static final int MAX_WORDS_PER_SENTENCE = 120;

	public static final int MAX_UNKNOWN_PER_SENTENCE = 200;

	public static final int MAX_POS_PER_WORD = 20;

	public static final int LITTLE_FREQUENCY = 6;

	public enum TAG_TYPE {
		TT_NORMAL, TT_PERSON, TT_PLACE, TT_TRANS_PERSON
	};

	public static final int MAX_FREQUENCE = 2079997;// 7528283+329805

	// //1993123+86874

	public static final int MAX_SENTENCE_LEN = 2000;

	public static final double INFINITE_VALUE = 10000.00;

	// 平滑参数
	public static final double SMOOTH_PARAM = 0.1;

	public static final String PERSON = "未##人";

	public static final String LOCATION = "未##地";

	public static final String UNKNOWN_NUM = "未##数";

	public static final String UNKNOWN_TIME = "未##时";

	public static final String UNKNOWN_LETTER = "未##串";

	public static final String UNKNOWN_NT = "未##机";

	public static final String UNKNOWN_NZ = "未##专";

	public static final String UNKOWN_TAG = "x";
	public static final String NUM_TAG = "m";
	public static final String SURNAME_TAG = "snr";
	public static final String JPN_SURNAME_TAG = "jsnr";
	public static final String NAME_TAG = "nr";
	public static final String LOCATION_TAG = "ns";
	public static final String DIRECTION_TAG = "s";
	public static final String PROPERNOUN_TAG = "nz";
	public static final String NW_TAG = "nw";
	public static final String PS = "ps";
	public static final String CONJUNCTION_TAG = "c";
	public static final String AUXILIARY_TAG = "u";
	public static final String VERB_TAG = "v";
	public static final String YUQI_TAG = "y";
	public static final String ADJECTIVE_TAG = "a";
	public static final String NOUN_TAG = "n";
	public static final String ADVERB_TAG = "d";
	public static final String PUNCTUATION_TAG = "w";
	public static final String POSITION_TAG = "f";
	public static final String PREPOSITION_TAG = "p";
	public static final String QUANTIFIER_TAG = "q";
	public static final String PRONOUN_TAG = "r";
	public static final String DIS_TAG = "b";// 区别词
	public static final String TIME_TAG = "t";

	public static final String UTF8 = "utf-8";
	public static final String GBK = "gbk";

	@SuppressWarnings("resource")
	public static boolean gbGenerate(String fileName) {
		File file;
		int i, j;
		file = new File(fileName);
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(file));
			if (!file.canWrite())
				return false;// fail while opening the file
			for (i = 161; i < 255; i++)
				for (j = 161; j < 255; j++)
					out.println("" + i + j + "," + i + "," + j);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

	/***************************************************************************
	 * 
	 * Func Name : CC_Generate
	 * 
	 * Description: Generate the Chinese Char List file
	 * 
	 * 
	 * Parameters : sFilename: the file name for the output CC List
	 * 
	 * Returns : public static boolean Author : Kevin Zhang History : 1.create
	 * 2002-1-8
	 **************************************************************************/
	public static boolean CC_Generate(String fileName) {
		File file;
		int i, j;
		file = new File(fileName);
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(file));
			for (i = 176; i < 255; i++)
				for (j = 161; j < 255; j++)
					out.println("" + i + j + "," + i + "," + j);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/***************************************************************************
	 * 
	 * Func Name : CC_Find
	 * 
	 * Description: Find a Chinese sub-string in the Chinese String
	 * 
	 * 
	 * Parameters : string:Null-terminated string to search
	 * 
	 * strCharSet:Null-terminated string to search for
	 * 
	 * Returns : String Author : Kevin Zhang History : 1.create 2002-1-8
	 **************************************************************************/
	public static boolean CC_Find(final byte[] string, final byte[] strCharSet) {
		if (string != null && strCharSet != null) {
			int index = strstr(string, strCharSet);
			if (index != -1 && (index % 2 == 1)) {
				return false;
			}
		}
		return true;
	}

	/***************************************************************************
	 * 
	 * Func Name : charType
	 * 
	 * Description: Judge the type of sChar or (sChar,sChar+1)
	 * 
	 * 
	 * Parameters : sFilename: the file name for the output CC List
	 * 
	 * Returns : int : the type of char Author : Kevin Zhang History : 1.create
	 * 2002-1-8
	 **************************************************************************/
	public static int charType(String str) {

		if (str != null && str.length() > 0) {
			byte[] b = str.getBytes();
			byte b1 = b[0];
			byte b2 = b.length > 1 ? b[1] : 0;
			if (getUnsigned(b1) < 128) {
				if ("\"!,.?()[]{}+=".indexOf((char) b1) != -1)
					return CT_DELIMITER;
				if (Character.isLetter(str.charAt(0))) {
					return CT_LETTER;
				}
				return CT_SINGLE;
			} else if (getUnsigned(b1) == 162)
				return CT_INDEX;
			else if (getUnsigned(b1) == 163 && getUnsigned(b2) > 175 && getUnsigned(b2) < 186)
				return CT_NUM;
			else if (getUnsigned(b1) == 163 && (getUnsigned(b2) >= 193 && getUnsigned(b2) <= 218
					|| getUnsigned(b2) >= 225 && getUnsigned(b2) <= 250))
				return CT_LETTER;
			else if (getUnsigned(b1) == 161 || getUnsigned(b1) == 163)
				return CT_DELIMITER;
			else if (getUnsigned(b1) >= 176 && getUnsigned(b1) <= 247)
				return CT_CHINESE;

		}
		return CT_OTHER;

	}

	/***************************************************************************
	 * 
	 * Func Name : GetCCPrefix
	 * 
	 * Description: Get the max Prefix string made up of Chinese Char
	 * 
	 * 
	 * Parameters : sSentence: the original sentence which includes Chinese or
	 * Non-Chinese char
	 * 
	 * Returns : the end of the sub-sentence Author : Kevin Zhang History :
	 * 1.create 2002-1-8
	 **************************************************************************/
	public static int getCCPrefix(byte[] sSentence) {
		int nLen = sSentence.length;
		int nCurPos = 0;
		while (nCurPos < nLen && getUnsigned(sSentence[nCurPos]) > 175 && getUnsigned(sSentence[nCurPos]) < 248) {
			nCurPos += 2;// Get next Chinese Char
		}
		return nCurPos;
	}


	/***************************************************************************
	 * 
	 * Func Name : IsAllNonChinese
	 * 
	 * Description: Judge the string is all made up of Single Byte Char
	 * 
	 * 
	 * Parameters : sSentence: the original sentence which includes Chinese or
	 * Non-Chinese char
	 * 
	 * Returns : the end of the sub-sentence Author : Kevin Zhang History :
	 * 1.create 2002-1-24
	 **************************************************************************/
	public static boolean isAllNonChinese(byte[] sString) {
		int nLen = sString.length;
		int i = 0;

		while (i < nLen) {
			if (getUnsigned(sString[i]) < 248 && getUnsigned(sString[i]) > 175)
				return false;
			if (sString[i] < 0)
				i += 2;
			else
				i += 1;
		}
		return true;
	}

	/***************************************************************************
	 * 
	 * Func Name : IsAllSingleByte
	 * 
	 * Description: Judge the string is all made up of Single Byte Char
	 * 
	 * 
	 * Parameters : sSentence: the original sentence which includes Chinese or
	 * Non-Chinese char
	 * 
	 * Returns : the end of the sub-sentence Author : Kevin Zhang History :
	 * 1.create 2002-1-24
	 **************************************************************************/
	public static boolean isAllSingleByte(String str) {
		if (str != null) {
			int len = str.length();
			int i = 0;
			byte[] b = str.getBytes();
			while (i < len && b[i] < 128) {
				i++;
			}
			if (i < len)
				return false;
			return true;
		}
		return false;
	}

	/***************************************************************************
	 * 
	 * Func Name : IsAllNum
	 * 
	 * Description: Judge the string is all made up of Num Char
	 * 
	 * 
	 * Parameters : sSentence: the original sentence which includes Chinese or
	 * Non-Chinese char
	 * 
	 * Returns : the end of the sub-sentence Author : Kevin Zhang History :
	 * 1.create 2002-1-24
	 **************************************************************************/
	// public static boolean isAllNum(String str) {
	//
	// if (str != null) {
	// int i = 0;
	// String temp = str + " ";
	// // 判断开头是否是+-之类的符号
	// if ("±+—-＋".indexOf(temp.substring(0, 1)) != -1)
	// i++;
	// /** 如果是全角的０１２３４５６７８９ 字符* */
	// while (i < str.length() && "０１２３４５６７８９".indexOf(str.substring(i, i + 1))
	// != -1)
	// i++;
	//
	// // Get middle delimiter such as .
	// if (i < str.length()) {
	// String s = str.substring(i, i + 1);
	// if ("∶·．／".indexOf(s) != -1 || ".".equals(s) || "/".equals(s)) {// 98．1％
	// i++;
	// while (i + 1 < str.length() && "０１２３４５６７８９".indexOf(str.substring(i + 1,
	// i + 2)) != -1)
	//
	// i++;
	// }
	// }
	//
	// if (i >= str.length())
	// return true;
	//
	// while (i < str.length() && GFString.cint(str.substring(i, i + 1)) >= 0
	// && GFString.cint(str.substring(i, i + 1)) <= 9)
	// i++;
	// // Get middle delimiter such as .
	// if (i < str.length()) {
	// String s = str.substring(i, i + 1);
	// if ("∶·．／".indexOf(s) != -1 || ".".equals(s) || "/".equals(s)) {// 98．1％
	// i++;
	// while (i + 1 < str.length() && "0123456789".indexOf(str.substring(i + 1,
	// i + 2)) != -1)
	// i++;
	// }
	// }
	//
	// if (i < str.length()) {
	//
	// if ("百千万亿佰仟％‰".indexOf(str.substring(i, i + 1)) == -1 &&
	// !"%".equals(str.substring(i, i + 1)))
	// i--;
	// }
	// if (i >= str.length())
	// return true;
	// }
	// return false;
	// }

	/***************************************************************************
	 * 
	 * Func Name : IsAllIndex
	 * 
	 * Description: Judge the string is all made up of Index Num Char
	 * 
	 * 
	 * Parameters : sSentence: the original sentence which includes Chinese or
	 * Non-Chinese char
	 * 
	 * Returns : the end of the sub-sentence Author : Kevin Zhang History :
	 * 1.create 2002-1-24
	 **************************************************************************/
	public static boolean isAllIndex(byte[] sString) {
		int nLen = sString.length;
		int i = 0;

		while (i < nLen - 1 && getUnsigned(sString[i]) == 162) {
			i += 2;
		}
		if (i >= nLen)
			return true;
		while (i < nLen && (sString[i] > 'A' - 1 && sString[i] < 'Z' + 1)
				|| (sString[i] > 'a' - 1 && sString[i] < 'z' + 1)) {// single
			// byte
			// number
			// char
			i += 1;
		}

		if (i < nLen)
			return false;
		return true;

	}

	/***************************************************************************
	 * 
	 * Func Name : IsAllLetter
	 * 
	 * Description: Judge the string is all made up of Letter Char
	 * 
	 * 
	 * Parameters : sSentence: the original sentence which includes Chinese or
	 * Non-Chinese char
	 * 
	 * Returns : the end of the sub-sentence Author : Kevin Zhang History :
	 * 1.create 2002-1-24
	 **************************************************************************/
	public static boolean isAllLetter(String str) {
		int i = 0;

		if (str != null) {
			int nLen = str.length();
			byte[] b = str.getBytes();
			while (i < nLen - 1 && getUnsigned(b[i]) == 163
					&& ((getUnsigned(b[i + 1]) >= 193 && getUnsigned(b[i + 1]) <= 218)
							|| (getUnsigned(b[i + 1]) >= 225 && getUnsigned(b[i + 1]) <= 250))) {
				i += 2;
			}
			if (i < nLen)
				return false;
			return true;
		}
		return false;
	}

	/***************************************************************************
	 * 
	 * Func Name : IsAllDelimiter
	 * 
	 * Description: Judge the string is all made up of Delimiter
	 * 
	 * 
	 * Parameters : sSentence: the original sentence which includes Chinese or
	 * Non-Chinese char
	 * 
	 * Returns : the end of the sub-sentence Author : Kevin Zhang History :
	 * 1.create 2002-1-24
	 **************************************************************************/
	public static boolean isAllDelimiter(byte[] sString) {
		int nLen = sString.length;
		int i = 0;

		while (i < nLen - 1 && (getUnsigned(sString[i]) == 161 || getUnsigned(sString[i]) == 163)) {
			i += 2;
		}
		if (i < nLen)
			return false;
		return true;
	}

	/***************************************************************************
	 * 
	 * Func Name : BinarySearch
	 * 
	 * Description: Lookup the index of nVal in the table nTable which length is
	 * nTableLen
	 * 
	 * Parameters : nPOS: the POS value
	 * 
	 * Returns : the index value Author : Kevin Zhang History : 1.create
	 * 2002-1-25
	 **************************************************************************/
	public static int binarySearch(int val, int[] table) {
		if (table != null) {
			int len = table.length;
			int start = 0, end = len - 1, mid = (start + end) / 2;

			while (start <= end)// Binary search
			{
				if (table[mid] == val) {
					return mid;// find it
				} else if (table[mid] < val) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
				mid = (start + end) / 2;
			}
		}
		return -1;// Can not find it;
	}

	/***************************************************************************
	 * 
	 * Func Name : IsForeign
	 * 
	 * Description: Decide whether the word is not a Non-fereign word
	 * 
	 * Parameters : sWord: the word
	 * 
	 * Returns : the index value Author : Kevin Zhang History : 1.create
	 * 2002-1-26
	 **************************************************************************/
	public static boolean isForeign(String word) {
		if (word != null) {
			int foreignCount = getForeignCharCount(word);
			int charCount = word.length();
			if (charCount > 2 || foreignCount >= 1 * charCount / 2)
				return true;
		}
		return false;
	}

	/***************************************************************************
	 * 
	 * Func Name : IsAllForeign
	 * 
	 * Description: Decide whether the word is not a Non-fereign word
	 * 
	 * Parameters : sWord: the word
	 * 
	 * Returns : the index value Author : Kevin Zhang History : 1.create
	 * 2002-3-25
	 **************************************************************************/
	public static boolean isAllForeign(String sWord) {
		int nForeignCount = getForeignCharCount(sWord);
		if (2 * nForeignCount == sWord.length())
			return true;
		return false;
	}

	/***************************************************************************
	 * 
	 * Func Name : IsForeign
	 * 
	 * Description: Decide whether the word is Chinese Num word
	 * 
	 * Parameters : sWord: the word
	 * 
	 * Returns : the index value Author : Kevin Zhang History : 1.create
	 * 2002-1-26
	 **************************************************************************/
	public static boolean isAllChineseNum(String word) {// 百分之五点六的人早上八点十八分起床

		String chineseNum = "零○一二两三四五六七八九十廿百千万亿壹贰叁肆伍陆柒捌玖拾佰仟∶·．／点";//
		String prefix = "几数第上成";

		if (word != null) {
			String temp = word + " ";
			for (int i = 0; i < word.length(); i++) {

				if (temp.indexOf("分之", i) != -1)// 百分之五
				{
					i += 2;
					continue;
				}

				String tchar = temp.substring(i, i + 1);
				if (chineseNum.indexOf(tchar) == -1 && (i != 0 || prefix.indexOf(tchar) == -1))
					return false;
			}
			return true;
		}

		return false;
	}

	/***************************************************************************
	 * 
	 * Func Name : GetForeignCharCount
	 * 
	 * Description:
	 * 
	 * Parameters : sWord: the word
	 * 
	 * Returns : the index value Author : Kevin Zhang History : 1.create
	 * 2002-4-4 2.Modify 2002-5-21
	 **************************************************************************/
	public static int getForeignCharCount(String sWord) {
		int nForeignCount, nCount;
		// English char counnts
		nForeignCount = getCharCount(TRANS_ENGLISH, sWord);
		// Japan char counnts
		nCount = getCharCount(TRANS_JAPANESE, sWord);
		if (nForeignCount <= nCount)
			nForeignCount = nCount;
		// Russian char counnts
		nCount = getCharCount(TRANS_RUSSIAN, sWord);
		if (nForeignCount <= nCount)
			nForeignCount = nCount;
		return nForeignCount;
	}

	/**
	 * 得到字符集的字符在字符串中出现的次数
	 * 
	 * @param charSet
	 * @param word
	 * @return
	 */
	public static int getCharCount(String charSet, String word) {
		int nCount = 0;

		if (word != null) {
			String temp = word + " ";
			for (int i = 0; i < word.length(); i++) {
				String s = temp.substring(i, i + 1);
				if (charSet.indexOf(s) != -1)
					nCount++;
			}
		}

		return nCount;
	}

	/***************************************************************************
	 * 
	 * Func Name : GetForeignCharCount
	 * 
	 * Description: Return the foreign type
	 * 
	 * Parameters : sWord: the word
	 * 
	 * Returns : the index value Author : Kevin Zhang History : 1.create
	 * 2002-4-4 2.Modify 2002-5-21
	 **************************************************************************/
	public int GetForeignType(String sWord) {
		int nForeignCount, nCount, nType = TT_ENGLISH;
		nForeignCount = getCharCount(TRANS_ENGLISH, sWord);// English
		// char
		// counnts
		nCount = getCharCount(TRANS_RUSSIAN, sWord);// Russian
		// char
		// counnts
		if (nForeignCount < nCount) {
			nForeignCount = nCount;
			nType = TT_RUSSIAN;
		}
		nCount = getCharCount(TRANS_JAPANESE, sWord);// Japan
		// char
		// counnts
		if (nForeignCount < nCount) {
			nForeignCount = nCount;
			nType = TT_JAPANESE;
		}
		return nType;
	}

	public static byte[] readBytes(DataInputStream in, int len) {
		if (in != null && len > 0) {
			byte[] b = new byte[len];
			try {
				for (int i = 0; i < len; i++)
					b[i] = in.readByte();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return b;
		}

		return null;
	}

	public static byte[] readBytes(BufferedInputStream in, int len) {
		if (in != null && len > 0) {
			byte[] b = new byte[len];
			try {
				in.read(b, 0, len);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return b;
		}
		return null;
	}

	// public static boolean PostfixSplit(byte[] sWord, byte[] sWordRet, byte[]
	// sPostfix) {
	// byte[] sSinglePostfix = POSTFIX_SINGLE.getBytes();
	// byte[][] sMultiPostfix = new byte[POSTFIX_MUTIPLE.length][9];
	// for (int i = 0; i < sMultiPostfix.length; i++)
	// sMultiPostfix[i] = POSTFIX_MUTIPLE[i].getBytes();
	// int nPostfixLen = 0, nWordLen = sWord.length;
	// int i = 0;
	//
	// while (sMultiPostfix[i][0] != 0
	// && strncmp(GFCommon.bytesCopy(sWord, nWordLen - sMultiPostfix[i].length,
	// sWord.length - nWordLen
	// + sMultiPostfix[i].length), 0, sMultiPostfix[i], sMultiPostfix[i].length)
	// == false) {// Try
	// // to
	// // get
	// // the
	// // postfix of an
	// // address
	// i++;
	// }
	// GFCommon.bytesCopy(sPostfix, sMultiPostfix[i], 0, sMultiPostfix.length);
	// nPostfixLen = sMultiPostfix[i].length;// Get the length of place
	// // postfix
	//
	// if (nPostfixLen == 0) {
	// sPostfix[2] = 0;
	// strncpy(sPostfix, GFCommon.bytesCopy(sWord, nWordLen - 2, 2), 2);
	// if (CC_Find(sSinglePostfix, sPostfix))
	// nPostfixLen = 2;
	// }
	//
	// strncpy(sWordRet, sWord, nWordLen - nPostfixLen);
	// sWordRet[nWordLen - nPostfixLen] = 0;// Get the place name which have
	// // erasing the postfix
	// sPostfix[nPostfixLen] = 0;
	// return true;
	// }

	/**
	 * 比较第二个字节数组是否在第一个中出现
	 * 
	 * @param b1
	 * @param b2
	 * @return 返回第一次出现在位置。如果没有出现，则返回－1
	 */
	public static int strstr(byte[] b1, byte[] b2) {
		boolean flag = true;
		if (b1 != null && b2 != null) {
			for (int i = 0; i < b1.length; i++) {
				if (b1[i] != b2[0])
					continue;
				else {
					if (b1.length - i >= b2.length) {
						for (int j = 0; j < b2.length; j++) {
							if (b2[j] != b1[i + j]) {
								flag = false;
								break;
							}
						}

						if (flag) {
							return i;
						}
					}
				}
			}
		}

		return -1;
	}

	public static int strchr(byte[] bs, byte b) {
		if (bs != null) {
			for (int i = 0; i < bs.length; i++) {
				if (bs[i] == b)
					return i;
			}

		}

		return -1;
	}

	/**
	 * 比较两个字节数组前len个字节是否相等
	 * 
	 * @param b1
	 * @param b2
	 * @param len
	 * @return
	 */
	public static boolean strncmp(byte[] b1, int startIndex, byte[] b2, int len) {
		if (b1 != null && b2 != null && len > 0) {
			if (b1.length >= len && b2.length >= len) {
				for (int i = startIndex; i < len; i++) {
					if (b1[i] != b2[i])
						return true;
				}
			}
		}

		return false;
	}

	public static int getUnsigned(byte b) {
		if (b > 0)
			return b;
		else
			return (b & 0x7F + 128);
	}

	public static void strncpy(byte[] dest, byte[] src, int len) {
		if (dest != null && src != null) {
			if (dest.length >= len && len <= src.length) {
				for (int i = 0; i < len; i++)
					dest[i] = src[i];
			}
		}
	}

	/**
	 * 汉字在6768区位表中对应的ID号
	 */
	public static int CC_ID(String str) {
		int result = -1;
		if (str != null && str.length() > 0) {
			byte[] b = str.getBytes();
			result = (getUnsigned(b[0]) - 176) * 94 + (getUnsigned(b[1]) - 161);
		}
		return result;
	}

	/**
	 * The first char computed by the Chinese Char ID
	 * 
	 * @param id
	 * @return
	 */
	public static int CC_CHAR1(int id) {
		return (id) / 94 + 176;
	}

	/**
	 * The second char computed by the Chinese Char ID
	 * 
	 * @param id
	 * @return
	 */
	public static int CC_CHAR2(int id) {
		return (id) % 94 + 161;
	}

	public static int strcat(byte[] dest, byte[] src, int len) {
		if (dest != null && src != null && len > 0) {

			for (int i = 0; i < dest.length; i++) {
				if (dest[i] == 0) {
					for (int j = 0; j < len; j++)
						dest[i] = src[j];
					return i;
				}
			}

		}

		return -1;
	}

	public static int strcpy(byte[] dest, byte[] src) {
		return strcpy(dest, src, src.length);
	}

	public static int strcpy(byte[] dest, byte[] src, int len) {
		if (dest != null && src != null && len > 0) {
			int i = 0;
			for (i = 0; i < len; i++) {
				dest[i] = src[i];

			}
			return i;
		}

		return -1;
	}

	/**
	 * 根据ID号得到对应的GB汉字
	 * 
	 * @param id
	 *            0--6767
	 * @return
	 */
	public static String getGB(int id) {
		String result = null;

		if (id >= 0 && id < 6768) {
			byte[] b = new byte[2];
			b[0] = (byte) CC_CHAR1(id);
			b[1] = (byte) CC_CHAR2(id);
			try {
				result = new String(b, "GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static boolean isSingle(String s) {
		if (s != null && s.getBytes().length == 1)
			return true;
		else
			return false;
	}

	public static int[] removeInvalid(int[] src) {
		int[] result = null;
		int count = 0;
		if (src != null && src.length > 0) {
			for (int i = 0; i < src.length; i++) {
				if (i != 0 && src[i] == 0)
					break;
				else
					count++;
			}

			result = new int[count];
			for (int i = 0; i < count; i++)
				result[i] = src[i];
		}

		return result;
	}

	/**
	 * 判断字符串是否是年份
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isYearTime(String snum) {
		if (snum != null) {
			int len = snum.length();
			String first = snum.substring(0, 1);

			// 1992年, 98年,06年
			if (isAllSingleByte(snum) && (len == 4 || len == 2 && (cint(first) > 4 || cint(first) == 0)))
				return true;
			if (isNumeric(snum) && (len >= 6 || len == 4 && "０５６７８９".indexOf(first) != -1))
				return true;
			if (getCharCount("零○一二三四五六七八九壹贰叁肆伍陆柒捌玖", snum) == len && len >= 2)
				return true;
			if (len == 4 && getCharCount("千仟零○", snum) == 2)// 二仟零二年
				return true;
			if (len == 1 && getCharCount("千仟", snum) == 1)
				return true;
			if (len == 2 && getCharCount("甲乙丙丁戊己庚辛壬癸", snum) == 1
					&& getCharCount("子丑寅卯辰巳午未申酉戌亥", snum.substring(1)) == 1)
				return true;
		}
		return false;
	}

	/**
	 * 判断是否为数字串
	 * 
	 * @param str
	 *            待判断的字符串
	 * @return boolean
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 把表示数字含义的字符串转你成整形
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 如果是有意义的整数，则返回此整数值。否则，返回-1。
	 */
	public static int cint(String str) {
		if (str != null)
			try {
				int i = new Integer(str).intValue();
				return i;
			} catch (NumberFormatException e) {

			}

		return -1;
	}

	/**
	 * 判断一个字符串的所有字符是否在另一个字符串集合中
	 * 
	 * @param aggr
	 *            字符串集合
	 * @param str
	 *            需要判断的字符串
	 * @return
	 */
	public static boolean isInAggregate(String aggr, String str) {
		if (aggr != null && str != null) {
			str += "1";
			for (int i = 0; i < str.length(); i++) {
				String s = str.substring(i, i + 1);
				if (aggr.indexOf(s) == -1)
					return false;
			}
			return true;
		}

		return false;
	}

	/**
	 * 判断该字符串是否是半角字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDBCCase(String str) {
		if (str != null) {
			str += " ";
			for (int i = 0; i < str.length(); i++) {
				String s = str.substring(i, i + 1);
				if (s.getBytes().length != 1)
					return false;
			}

			return true;
		}

		return false;
	}

	/**
	 * 判断该字符串是否是全角字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isSBCCase(String str) {
		if (str != null) {
			str += " ";
			for (int i = 0; i < str.length(); i++) {
				String s = str.substring(i, i + 1);
				if (s.getBytes().length != 2)
					return false;
			}

			return true;
		}
		return false;
	}

	/**
	 * 判断是否是一个连字符（分隔符）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDelimiter(String str) {
		if (str != null && ("-".equals(str) || "－".equals(str)))
			return true;
		else
			return false;
	}

	public static boolean isUnknownWord(String word) {
		if (word != null && word.indexOf("未##") == 0)
			return true;
		else
			return false;
	}

	/**
	 * 计算两个词连接@后得到的字符串的hash值
	 * 
	 * @param s1
	 *            第一个词
	 * @param s2
	 *            第二个词
	 * @return s1@s2的hash值
	 */
	public static int hash(String s1, String s2) {
		int hash = 1;
		int len = s1.length() + s2.length() + 1;

		for (int i = 0; i < len; i++) {
			int offset;
			if (i < s1.length()) {
				offset = s1.charAt(i);
			} else if (i == s1.length()) {
				offset = '@';
			} else {
				offset = s2.charAt(i - s1.length() - 1);
			}
			hash = 31 * hash + offset;
		}

		return hash;
	}

	public static int hash(String s) {
		int hash = 1;
		for (int i = 0; i < s.length(); i++) {
			hash = 31 * hash + s.charAt(i);
		}
		return hash;
	}

	/**
	 * 是否是数字、连字符的情况，如：３-4月
	 * 
	 * @param pos
	 * @param str
	 * @return
	 */
	// public static boolean isNumDelimiter(int pos, String str) {
	// if (str != null && str.length() > 1) {
	// String first = str.substring(0, 1);
	// // //27904='m'*256 29696='t'*256
	// if ((Math.abs(pos) == POSTag.NUM || Math.abs(pos) == POSTag.TIME)
	// && ("—".equals(first) || "-".equals(first)))
	// return true;
	// }
	// return false;
	// }

	public static String getWord(String line) {
		int index = line.lastIndexOf(seg);
		if (index < 0)
			return line;
		return line.substring(0, index);
	}

	public static String getCate(String line) {
		int index = line.lastIndexOf(seg);
		if (index < 0)
			return line;
		return line.substring(index + 1);
	}

	/**
	 * 字符串的所有单个字符都是数字但本身并不是一个有意义的数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumStrNotNum(String word) {
		if (word != null) {
			if (word.length() == 2 && Utility.isInAggregate("第上成±—＋∶·．／", word))
				return true;
			if (word.length() == 1 && Utility.isInAggregate("+-./", word))
				return true;
		}
		return false;
	}

	public static boolean isChinsesStr(String str) {
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (!isChinese(ch[i]))
				return false;
		}
		return true;
	}

	public static boolean isChinese(char c) {
		int v = c;
		return (v >= 19968 && v <= 171941);
	}
	
	/**
	 * 将一组数据固定分组，每组n个元素
	 *
	 * @param source 要分组的数据源
	 * @param n      每组n个元素
	 * @param <T>
	 * @return
	 */
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