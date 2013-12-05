package com.wizard.util.file;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testGetFileMd501() {
		String path = "C:\\Users\\wizard\\Desktop\\dbo.sql";
		long s = System.currentTimeMillis();
		String code = FileUtil.getFileMd5(path);
		System.out.println(System.currentTimeMillis() - s);
		System.out.println(code);
	}

	@Test
	public void testGetFileSHA101() {
		String path = "C:\\Users\\wizard\\Desktop\\dbo.sql";
		long s = System.currentTimeMillis();
		String code = FileUtil.getFileSHA1(path);
		System.out.println(System.currentTimeMillis() - s);
		System.out.println(code);
	}

	public void testGetFileCRC3201() {
		String path = "C:\\Users\\wizard\\Desktop\\dbo.sql";
		long s = System.currentTimeMillis();
		String code = FileUtil.getFileCRC32(path);
		System.out.println(System.currentTimeMillis() - s);
		System.out.println(code);
	}

}
