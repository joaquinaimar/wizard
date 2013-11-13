package com.wizard.utils;

import junit.framework.Assert;

import org.junit.Test;

public class ConvertUtilsTest {

	@Test
	public void testByteToInt() {
		int in = 100;
		byte[] data = ConvertUtils.intToByte(in);
		int out = ConvertUtils.bytesToInt(data);

		Assert.assertEquals(in, out);
	}

}
