package com.wizard.util.common;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ConversionUtil {

	private ConversionUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	// =========================================================================
	// convertToBytes
	// =========================================================================
	public static byte[] convertToBytes(final String str, final Charset charset) {
		return str.getBytes(charset);
	}

	public static byte[] convertToBytes(final String str,
			final String charsetName) {
		return convertToBytes(str, Charset.forName(charsetName));
	}

	public static byte[] convertToBytes(final String str) {
		return convertToBytes(str, Charset.defaultCharset());
	}

	public static byte[] convertToBytes(final char[] chars,
			final Charset charset) {
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = charset.encode(cb);
		return bb.array();
	}

	public static byte[] convertToBytes(final char[] chars,
			final String charsetName) {
		return convertToBytes(chars, Charset.forName(charsetName));
	}

	public static byte[] convertToBytes(final char[] chars) {
		return convertToBytes(chars, Charset.defaultCharset());
	}

	public static byte[] convertToBytes(final short s) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (s & 0xff);
		bytes[1] = (byte) ((s & 0xff00) >> 8);
		return bytes;
	}

	public static byte[] convertToBytes(final char c) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (c);
		bytes[1] = (byte) (c >> 8);
		return bytes;
	}

	public static byte[] convertToBytes(final int i) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (i & 0xff);
		bytes[1] = (byte) ((i & 0xff00) >> 8);
		bytes[2] = (byte) ((i & 0xff0000) >> 16);
		bytes[3] = (byte) ((i & 0xff000000) >> 24);
		return bytes;
	}

	public static byte[] convertToBytes(final long l) {
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

	public static byte[] convertToBytes(final float f) {
		int intBits = Float.floatToIntBits(f);
		return convertToBytes(intBits);
	}

	public static byte[] convertToBytes(final double d) {
		long intBits = Double.doubleToLongBits(d);
		return convertToBytes(intBits);
	}

	// =========================================================================
	// convertToChar
	// =========================================================================
	public static char convertToChar(final byte[] bytes) {
		return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	public static char convertToChar(final int i) {
		return (char) i;
	}

	// =========================================================================
	// convertToChars
	// =========================================================================
	public static char[] convertToChars(final byte[] bytes,
			final Charset charset) {
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = charset.decode(bb);
		return cb.array();
	}

	public static char[] convertToChars(final byte[] bytes,
			final String charsetName) {
		return convertToChars(bytes, Charset.forName(charsetName));
	}

	public static char[] convertToChars(final byte[] bytes) {
		return convertToChars(bytes, Charset.defaultCharset());
	}

	public static char[] convertToChars(final String str) {
		return str.toCharArray();
	}

	// =========================================================================
	// convertToShort
	// =========================================================================
	public static short convertToShort(final byte[] bytes) {
		return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	public static short convertToShort(final String str, final int radix) {
		return Short.parseShort(str, radix);
	}

	public static short convertToShort(final String str) {
		return Short.parseShort(str);
	}

	// =========================================================================
	// convertToInt
	// =========================================================================
	public static int convertToInt(final byte[] bytes) {
		return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8))
				| (0xff0000 & (bytes[2] << 16))
				| (0xff000000 & (bytes[3] << 24));
	}

	public static int convertToInt(final String str, final int radix) {
		return Integer.parseInt(str, radix);
	}

	public static int convertToInt(final String str) {
		return Integer.parseInt(str);
	}

	// =========================================================================
	// convertToLong
	// =========================================================================
	public static long convertToLong(final byte[] bytes) {
		return (0xffL & (long) bytes[0]) | (0xff00L & ((long) bytes[1] << 8))
				| (0xff0000L & ((long) bytes[2] << 16))
				| (0xff000000L & ((long) bytes[3] << 24))
				| (0xff00000000L & ((long) bytes[4] << 32))
				| (0xff0000000000L & ((long) bytes[5] << 40))
				| (0xff000000000000L & ((long) bytes[6] << 48))
				| (0xff00000000000000L & ((long) bytes[7] << 56));
	}

	public static long convertToLong(final String str, final int radix) {
		return Long.parseLong(str, radix);
	}

	public static long convertToLong(final String str) {
		return Long.parseLong(str);
	}

	// =========================================================================
	// convertToFloat
	// =========================================================================
	public static float convertToFloat(final byte[] bytes) {
		return Float.intBitsToFloat(convertToInt(bytes));
	}

	public static float convertToFloat(final String str) {
		return Float.parseFloat(str);
	}

	// =========================================================================
	// convertToDouble
	// =========================================================================
	public static double convertToDouble(final byte[] bytes) {
		return Double.longBitsToDouble(convertToLong(bytes));
	}

	public static double convertToDouble(final String str) {
		return Double.parseDouble(str);
	}

	// =========================================================================
	// convertToString
	// =========================================================================
	public static String convertToString(final byte[] bytes,
			final Charset charset) {
		return new String(bytes, charset);
	}

	public static String convertToString(final byte[] bytes,
			final String charsetName) {
		return convertToString(bytes, Charset.forName(charsetName));
	}

	public static String convertToString(final byte[] bytes) {
		return convertToString(bytes, Charset.defaultCharset());
	}

	public static String convertToString(final char[] chars) {
		return new String(chars);
	}

	public static String convertToString(final short s) {
		return String.valueOf(s);
	}

	public static String convertToString(final int i) {
		return String.valueOf(i);
	}

	public static String convertToString(final long l) {
		return String.valueOf(l);
	}

	public static String convertToString(final float f) {
		return String.valueOf(f);
	}

	public static String convertToString(final double d) {
		return String.valueOf(d);
	}

	public static String convertToString(final Date date,
			final DateFormat dateFormat) {
		return dateFormat.format(date);
	}

	public static String convertToString(final Date date, final String format) {
		return convertToString(date, new SimpleDateFormat(format));
	}

	// =========================================================================
	// convertToDate
	// =========================================================================
	public static Date convertToDate(final long m) {
		return new Date(m);
	}

	public static Date convertToDate(final byte[] bytes) {
		return convertToDate(convertToLong(bytes));
	}

	public static Date convertToDate(final String str,
			final DateFormat dateFormat) {
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date convertToDate(final String str, final String format) {
		return convertToDate(str, new SimpleDateFormat(format));
	}

	public static Date convertToDate(final String str) {
		return convertToDate(str, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
	}
	
	// =========================================================================
	// convertBytesToHex
	// =========================================================================
	public static String convertBytesToHex(byte[] data, int m, int n) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char[] temp = new char[n * 2];
		int k = m + n;
		for (int l = m; l < k; l++) {
			byte b = data[l];
			temp[l * 2] = hexDigits[b >>> 4 & 0x0f];
			temp[l * 2 + 1] = hexDigits[b & 0x0f];
		}
		return new String(temp);
	}

	public static String convertBytesToHex(byte[] data) {
		return convertBytesToHex(data, 0, data.length);
	}
}
