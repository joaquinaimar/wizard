package com.wizard.common;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ConversionUtil {

	private ConversionUtil() {
	}

	// =========================================================================
	// convertToBytes
	// =========================================================================
	public static byte[] convertToBytes(String str, Charset charset) {
		return str.getBytes(charset);
	}

	public static byte[] convertToBytes(String str, String charsetName) {
		return convertToBytes(str, Charset.forName(charsetName));
	}

	public static byte[] convertToBytes(String str) {
		return convertToBytes(str, Charset.defaultCharset());
	}

	public static byte[] convertToBytes(char[] chars, Charset charset) {
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = charset.encode(cb);
		return bb.array();
	}

	public static byte[] convertToBytes(char[] chars, String charsetName) {
		return convertToBytes(chars, Charset.forName(charsetName));
	}

	public static byte[] convertToBytes(char[] chars) {
		return convertToBytes(chars, Charset.defaultCharset());
	}

	public static byte[] convertToBytes(short s) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (s & 0xff);
		bytes[1] = (byte) ((s & 0xff00) >> 8);
		return bytes;
	}

	public static byte[] convertToBytes(char c) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (c);
		bytes[1] = (byte) (c >> 8);
		return bytes;
	}

	public static byte[] convertToBytes(int i) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (i & 0xff);
		bytes[1] = (byte) ((i & 0xff00) >> 8);
		bytes[2] = (byte) ((i & 0xff0000) >> 16);
		bytes[3] = (byte) ((i & 0xff000000) >> 24);
		return bytes;
	}

	public static byte[] convertToBytes(long l) {
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (l & 0xff);
		bytes[1] = (byte) ((l >> 8) & 0xff);
		bytes[2] = (byte) ((l >> 16) & 0xff);
		bytes[3] = (byte) ((l >> 24) & 0xff);
		bytes[4] = (byte) ((l >> 32) & 0xff);
		bytes[5] = (byte) ((l >> 40) & 0xff);
		bytes[6] = (byte) ((l >> 48) & 0xff);
		bytes[7] = (byte) ((l >> 56) & 0xff);
		return bytes;
	}

	public static byte[] convertToBytes(float f) {
		int intBits = Float.floatToIntBits(f);
		return convertToBytes(intBits);
	}

	public static byte[] convertToBytes(double d) {
		long intBits = Double.doubleToLongBits(d);
		return convertToBytes(intBits);
	}

	// =========================================================================
	// convertToChar
	// =========================================================================
	public static char convertToChar(byte[] bytes) {
		return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	// =========================================================================
	// convertToChars
	// =========================================================================
	public static char[] convertToChars(String str) {
		return str.toCharArray();
	}

	public static char[] convertToChars(byte[] bytes, Charset charset) {
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = charset.decode(bb);
		return cb.array();
	}

	public static char[] convertToChars(byte[] bytes, String charsetName) {
		return convertToChars(bytes, Charset.forName(charsetName));
	}

	public static char[] convertToChars(byte[] bytes) {
		return convertToChars(bytes, Charset.defaultCharset());
	}

	// =========================================================================
	// convertToShort
	// =========================================================================
	public static short convertToShort(byte[] bytes) {
		return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	// =========================================================================
	// convertToInt
	// =========================================================================
	public static int convertToInt(byte[] bytes) {
		return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8))
				| (0xff0000 & (bytes[2] << 16))
				| (0xff000000 & (bytes[3] << 24));
	}

	// =========================================================================
	// convertToLong
	// =========================================================================
	public static long convertToLong(byte[] bytes) {
		return (0xffL & (long) bytes[0]) | (0xff00L & ((long) bytes[1] << 8))
				| (0xff0000L & ((long) bytes[2] << 16))
				| (0xff000000L & ((long) bytes[3] << 24))
				| (0xff00000000L & ((long) bytes[4] << 32))
				| (0xff0000000000L & ((long) bytes[5] << 40))
				| (0xff000000000000L & ((long) bytes[6] << 48))
				| (0xff00000000000000L & ((long) bytes[7] << 56));
	}

	// =========================================================================
	// convertToFloat
	// =========================================================================
	public static float convertToFloat(byte[] bytes) {
		return Float.intBitsToFloat(convertToInt(bytes));
	}

	// =========================================================================
	// convertToDouble
	// =========================================================================
	public static double convertToDouble(byte[] bytes) {
		long l = convertToLong(bytes);
		return Double.longBitsToDouble(l);
	}

	// =========================================================================
	// convertToString
	// =========================================================================
	public static String convertToString(byte[] bytes, Charset charset) {
		return new String(bytes, charset);
	}

	public static String convertToString(byte[] bytes, String charsetName) {
		return convertToString(bytes, Charset.forName(charsetName));
	}

	public static String convertToString(byte[] bytes) {
		return convertToString(bytes, Charset.defaultCharset());
	}

	public static String convertToString(char[] chars) {
		return new String(chars);
	}

	public static String convertToString(short s) {
		return String.valueOf(s);
	}

	public static String convertToString(int i) {
		return String.valueOf(i);
	}

	public static String convertToString(long l) {
		return String.valueOf(l);
	}

	public static String convertToString(float f) {
		return String.valueOf(f);
	}

	public static String convertToString(double d) {
		return String.valueOf(d);
	}

	public static String convertToString(Date date, DateFormat dateFormat) {
		return dateFormat.format(date);
	}

	public static String convertToString(Date date, String format) {
		return convertToString(date, new SimpleDateFormat(format));
	}

	// =========================================================================
	// convertToDate
	// =========================================================================
	public static Date convertToDate(long m) {
		return new Date(m);
	}

	public static Date convertToDate(byte[] bytes) {
		return convertToDate(convertToLong(bytes));
	}

	public static Date convertToDate(String str, DateFormat dateFormat) {
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date convertToDate(String str, String format) {
		return convertToDate(str, new SimpleDateFormat(format));
	}
}
