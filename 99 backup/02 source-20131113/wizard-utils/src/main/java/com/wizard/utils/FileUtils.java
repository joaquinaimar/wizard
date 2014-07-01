package com.wizard.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public final class FileUtils {

	private FileUtils() {
	}

	public static File[] getFile(String path) {
		return getFile(path, false);
	}

	public static File[] getFile(String path, boolean hidden) {
		return getFile(new File(path), hidden);
	}

	public static File[] getFile(File directory) {
		return getFile(directory, false);
	}

	public static File[] getFile(File directory, boolean hidden) {
		if (!directory.exists())
			return null;
		else if (!hidden && directory.isHidden())
			return new File[] {};
		else if (directory.isFile())
			return new File[] { directory };
		else if (directory.isDirectory())
			return getAllFiles(directory, hidden);
		else
			return null;
	}

	public static File[] getAllFiles(File directory) {
		return getAllFiles(directory, false);
	}

	public static File[] getAllFiles(File directory, boolean hidden) {
		File[] files = directory.listFiles();
		List<File> fileLst = new ArrayList<File>();
		for (File file : files) {
			File[] fs = getFile(file, hidden);
			for (File f : fs) {
				fileLst.add(f);
			}
		}
		return fileLst.toArray(new File[fileLst.size()]);
	}

	public static String getFileMd5(String path) throws Exception {
		return getFileMd5(new File(path));
	}

	public static String getFileMd5(File file) throws Exception {
		if (!file.isFile())
			return null;
		MessageDigest messagedigest = null;
		messagedigest = MessageDigest.getInstance("MD5");
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[8192];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			messagedigest.update(buffer, 0, length);
		}
		in.close();
		return ConvertUtils.bytesToHex(messagedigest.digest());
	}

	public static String getFileCRC(String path) throws Exception {
		return getFileCRC(new File(path));
	}

	public static String getFileCRC(File file) throws Exception {
		FileInputStream in = new FileInputStream(file);
		CRC32 crc32 = new CRC32();
		for (CheckedInputStream cin = new CheckedInputStream(in, crc32); cin
				.read() != -1;) {
		}
		in.close();
		return Long.toHexString(crc32.getValue());
	}

}
