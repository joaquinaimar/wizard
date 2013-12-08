package com.wizard.util.common;

import java.security.MessageDigest;

public final class SecurityUtil {

	private SecurityUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public final static String encodeMd5(final String str) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = str.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char security[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				security[k++] = hexDigits[byte0 >>> 4 & 0xf];
				security[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(security);
		} catch (Exception e) {
			return null;
		}

	}

}
