package com.wizard.svn.support;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FileUtils {

	/**
	 * 获取文件
	 * 
	 * @param path
	 * @param hidden
	 * @return
	 */
	public static File[] getFile(String path, boolean hidden) {
		return getFile(new File(path), hidden);
	}

	/**
	 * 获取文件
	 * 
	 * @param directory
	 * @param hidden
	 * @return
	 */
	public static File[] getFile(File directory, boolean hidden) {

		if (!directory.exists()) {
			return null;
		} else if (!hidden && directory.isHidden()) {
			return new File[] {};
		} else if (directory.isFile()) {
			return new File[] { directory };
		} else if (directory.isDirectory()) {
			return getAllFiles(directory, hidden);
		}
		return null;

	}

	/**
	 * 获取目录下所有文件
	 * 
	 * @param directory
	 * @return
	 */
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

}
