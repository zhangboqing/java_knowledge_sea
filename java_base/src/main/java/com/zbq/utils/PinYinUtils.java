package com.zbq.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.commons.lang3.StringUtils;

public class PinYinUtils {
	
	public static String getPinyin(String input) {
		if (StringUtils.isBlank(input)) {
			return "";
		}
		HanyuPinyinOutputFormat format = getFormat();
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			try {
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
				if (pinyinArray != null) {
					output.append(pinyinArray[0].charAt(0));
				} else {
					output.append(String.valueOf(c));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return output.toString();
	}
	
	private static HanyuPinyinOutputFormat getFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		return format;
	}
	
	public static void main (String[] args){
		
		String tet = "中国人";
		String py = getPinyin(tet);
		System.out.println(py);
		
	}

}
