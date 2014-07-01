package com.wizard.svn.support;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class FileUtilsTest {

	//@Test
	public void testGetFile01() {
		String path = "E:\\space\\testspace";
		File[] files = FileUtils.getFile(path, false);
		Assert.assertEquals(2, files.length);
	}

	//@Test
	public void testGetFile02() {
		String path = "E:\\space\\testspace";
		File[] files = FileUtils.getFile(path, true);
		Assert.assertEquals(6, files.length);
	}

}
