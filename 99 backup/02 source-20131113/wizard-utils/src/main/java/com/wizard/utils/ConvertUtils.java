package com.wizard.utils;

public class ConvertUtils {

	private ConvertUtils() {
	}

	public static int bytesToInt(byte[] data) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			result += (data[i] & 0x000000FF) << shift;
		}
		return result;
	}

	public static byte[] intToByte(int data) {
		byte[] result = new byte[4];
		result[0] = (byte) ((data >> 24) & 0xFF);
		result[1] = (byte) ((data >> 16) & 0xFF);
		result[2] = (byte) ((data >> 8) & 0xFF);
		result[3] = (byte) (data & 0xFF);
		return result;
	}

	public static String bytesToHex(byte[] data, int m, int n) {
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

	public static String bytesToHex(byte[] data) {
		return bytesToHex(data, 0, data.length);
	}

}
