package com.wizard.svn;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizard.svn.support.FileUtils;
import com.wizard.svn.support.SvnOptions;

/**
 * 
 * @author zhanglizhi042888
 * 
 */
public class SvnOperation extends AbstractSvnOperation {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(SvnOperation.class);

	/**
	 * 初始化SVN操作
	 * 
	 * @param option
	 */
	public SvnOperation(SvnOptions option) {
		super(option);
	}

	/**
	 * 增加文件
	 * 
	 * @param path
	 * @return
	 */
	public Long doAdd(String path) {

		File[] files = FileUtils.getFile(path, false);
		if (null == files) {
			logger.error("path not exists");
			return null;
		}

		return doAdd(files);

	}

	/**
	 * 提交文件
	 * 
	 * @param path
	 * @return
	 */
	public Long doCommit(String path) {

		File[] files = FileUtils.getFile(path, false);
		if (null == files) {
			logger.error("path not exists");
			return null;
		}

		return doCommit(files);

	}

	/**
	 * 解除锁定
	 * 
	 * @param path
	 * @return
	 */
	public Long doUpdate(String path) {
		File[] files = FileUtils.getFile(path, false);
		if (null == files) {
			logger.error("path not exists");
			return null;
		}

		return doUpdate(files);
	}

}
