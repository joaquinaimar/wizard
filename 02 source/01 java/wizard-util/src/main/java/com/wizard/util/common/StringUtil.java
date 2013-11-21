package com.wizard.util.common;

import java.util.regex.Pattern;

public class StringUtil {

	public final static String BLANK = "";

	public final static String SPACE = " ";

	private StringUtil() {
		throw new RuntimeException("Cannot create util class instance!");
	}

	public static boolean isNull(final String str) {
		return CommonUtil.isNull(str);
	}

	public static boolean isBlank(final String str) {
		return CommonUtil.isNull(str) || BLANK.equals(str);
	}

	public static String doInitials(final String str) {
		char[] cs = str.toCharArray();
		for (int i = 0; i < cs.length; i++)
			if ((0 == i || ' ' == cs[i - 1]) && ('a' <= cs[i] && cs[i] < 'z'))
				cs[i] -= 32;
		return new String(cs);
	}

	public static String changeUnderscodeToHump(final String str) {
		char[] cs = str.toCharArray();
		char[] temps = new char[cs.length];
		int i = 0, j = 0;
		for (i = 0; i < cs.length; i++)
			if ('_' != cs[i])
				if (0 != i && '_' == cs[i - 1] && ('a' <= cs[i] && cs[i] < 'z'))
					temps[j++] = (char) (cs[i] - 32);
				else
					temps[j++] = cs[i];
		return new String(temps).substring(0, j);
	}

	public static String changeHumpToUnderscode(final String str) {
		char[] cs = str.toCharArray();
		char[] temps = new char[cs.length * 2];
		int i = 0, j = 0;
		for (i = 0; i < cs.length; i++)
			if (0 != i && ('A' <= cs[i] && cs[i] < 'Z')) {
				temps[j++] = '_';
				temps[j++] = (char) (cs[i] + 32);
			} else
				temps[j++] = cs[i];
		return new String(temps).substring(0, j);
	}

	public static boolean match(String str, String regex) {
		return Pattern.matches(regex, str);
	}

}
