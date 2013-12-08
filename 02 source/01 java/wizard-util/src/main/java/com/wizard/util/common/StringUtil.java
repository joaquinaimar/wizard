package com.wizard.util.common;

import java.util.regex.Pattern;

public final class StringUtil {

	public final static String BLANK = "";

	public final static String SPACE = " ";

	private final static char CHAR_SPACE = ' ';

	private StringUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public static boolean isNull(final String str) {
		return CommonUtil.isNull(str);
	}

	public static String isNull(final String str, final String defaultStr) {
		return isNull(str) ? defaultStr : str;
	}

	public static boolean isBlank(final String str) {
		return CommonUtil.isNull(str) || BLANK.equals(str);
	}

	public static String isBlank(final String str, final String defaultStr) {
		return isBlank(str) ? defaultStr : str;
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

	public static boolean match(final String str, final String regex) {
		return Pattern.matches(regex, str);
	}

	public static String trim(final String str) {
		return isNull(str) ? str : str.trim();
	}

	public static String trimLeft(final String str) {
		if (isNull(str))
			return str;
		char[] cs = str.toCharArray();
		int i = 0;
		for (; i < cs.length; i++)
			if (CHAR_SPACE != cs[i])
				break;
		return new String(cs, i, cs.length - i);
	}

	public static String trimRight(final String str) {
		if (isNull(str))
			return str;
		char[] cs = str.toCharArray();
		int i = cs.length - 1;
		for (; i >= 0; i--)
			if (CHAR_SPACE != cs[i])
				break;
		return new String(cs, 0, i + 1);
	}

}
