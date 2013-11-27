package com.wizard.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import com.wizard.util.common.CommonUtil;
import com.wizard.util.common.ConversionUtil;

public final class FileUtil {

	private FileUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public static File getFile(final String path) {
		return new File(path);
	}

	public static boolean exists(final String path) {
		return exists(getFile(path));
	}

	public static boolean exists(final File file) {
		return file.exists();
	}

	public static File getDirectory(final File file) {
		return file.getParentFile();
	}

	public static File getDirectory(final String path) {
		return getDirectory(getFile(path));
	}

	public static long getFileSize(final File file) {
		return file.length();
	}

	public static long getFileSize(final String path) {
		return getFileSize(getFile(path));
	}

	public static Date getFileDate(final File file) {
		return new Date(file.lastModified());
	}

	public static Date getFileDate(final String path) {
		return getFileDate(getFile(path));
	}

	public static File[] getFiles(final String path) {
		return getFiles(path, false);
	}

	public static File[] getFiles(final String path, final boolean hidden) {
		return getFiles(new File(path), hidden);
	}

	public static File[] getFiles(final File directory) {
		return getFiles(directory, false);
	}

	public static File[] getFiles(final File directory, final boolean hidden) {
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

	public static File[] getAllFiles(final File directory) {
		return getAllFiles(directory, false);
	}

	public static File[] getAllFiles(final File directory, final boolean hidden) {
		File[] files = directory.listFiles();
		List<File> fileLst = new ArrayList<File>();
		for (File file : files) {
			File[] fs = getFiles(file, hidden);
			for (File f : fs)
				fileLst.add(f);
		}
		return CommonUtil.changeListToArray(fileLst);
	}

	public static FileInputStream getInputStream(final File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static FileInputStream getInputStream(final String path) {
		return getInputStream(getFile(path));
	}

	public static byte[] getFileData(final FileInputStream inputStream,
			final int start, final int len) {
		try {
			byte[] datas = new byte[len];
			inputStream.read(datas, start, len);
			inputStream.close();
			return datas;
		} catch (IOException e) {
			return null;
		}
	}

	public static byte[] getFileData(final FileInputStream inputStream) {
		try {
			return getFileData(inputStream, 0, inputStream.available());
		} catch (IOException e) {
			return null;
		}
	}

	public static byte[] getFileData(final File file, final int start,
			final int len) {
		return getFileData(getInputStream(file), start, len);
	}

	public static byte[] getFileData(final File file) {
		return getFileData(getInputStream(file));
	}

	public static byte[] getFileData(final String path, final int start,
			final int len) {
		return getFileData(getInputStream(path), start, len);
	}

	public static byte[] getFileData(final String path) {
		return getFileData(getInputStream(path));
	}

	public static FileOutputStream getOutputStream(final File file) {
		try {
			return new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static FileOutputStream getOutputStream(final String path) {
		return getOutputStream(getFile(path));
	}

	public static String getFileMd5(final String path) throws Exception {
		return getFileMd5(new File(path));
	}

	public static String getFileMd5(final File file) throws Exception {
		if (!file.isFile())
			return null;
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[8192];
		int length = -1;
		while ((length = in.read(buffer)) != -1)
			messagedigest.update(buffer, 0, length);

		in.close();
		return ConversionUtil.convertBytesToHex(messagedigest.digest());
	}

	public static String getFileCRC(final String path) throws Exception {
		return getFileCRC(new File(path));
	}

	public static String getFileCRC(final File file) throws Exception {
		FileInputStream in = new FileInputStream(file);
		CRC32 crc32 = new CRC32();
		for (CheckedInputStream cin = new CheckedInputStream(in, crc32); cin
				.read() != -1;) {
		}
		in.close();
		return Long.toHexString(crc32.getValue());
	}

}
