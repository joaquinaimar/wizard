package com.wizard.utils;

import java.io.File;

import org.junit.Assert;

public class FileUtilsTest {

	// @Test
	public void testGetFile01() {
		String path = "E:\\space\\testspace";
		File[] files = FileUtils.getFile(path, false);
		Assert.assertEquals(2, files.length);
	}

	// @Test
	public void testGetFile02() {
		String path = "E:\\space\\testspace";
		File[] files = FileUtils.getFile(path, true);
		Assert.assertEquals(6, files.length);
	}

	// @Test
	public void testGetFileMd5() throws Exception {
		String path = "D:\\TDDOWNLOAD\\UnitySetup-4.2.2.exe";
		String str1 = FileUtils.getFileMd5(path);
		String str2 = FileUtils.getFileMd5(path);
		Assert.assertEquals(32, str1.length());
		Assert.assertEquals(32, str2.length());
		Assert.assertEquals(str1, str2);
	}

}
