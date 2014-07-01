package com.wizard.svn;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.wizard.svn.support.SvnOptions;

/**
 * 
 * @author zhanglizhi042888
 * 
 */
public abstract class AbstractSvnOperation {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AbstractSvnOperation.class);

	/**
	 * 客户端
	 */
	protected SVNClientManager clientManager = null;

	/**
	 * 版本库的URL地址
	 */
	protected SVNURL repositoryURL = null;

	/**
	 * 工作空间
	 */
	protected String workpath = null;

	/**
	 * 
	 * @param option
	 */
	public AbstractSvnOperation(SvnOptions option) {

		// 安装仓库工厂
		SVNRepositoryFactoryImpl.setup();
		logger.info("SVNRepository Factory setup");

		try {
			repositoryURL = SVNURL.parseURIEncoded(option.getRepositoryURL());
			logger.info("repository URL success");
		} catch (SVNException e) {
			logger.error("repository URL error");
		}

		// 驱动选项
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);

		this.clientManager = SVNClientManager.newInstance(
				(DefaultSVNOptions) options, option.getName(),
				option.getPassword());
		logger.info("create SVNClientManager success");

		this.workpath = option.getWorkPath();

		doCheckout();

	}

	/**
	 * 导入整个路径至SVN根目录
	 * 
	 * @param path
	 * @return
	 */
	public Long doImport(String path) {

		File impDir = new File(path);
		if (!impDir.exists()) {
			logger.error("import path not exists");
		}

		try {
			SVNCommitInfo commitInfo = clientManager.getCommitClient()
					.doImport(impDir, repositoryURL, "import operation!", null,
							false, false, SVNDepth.INFINITY);
			logger.info(commitInfo.toString());
			return commitInfo.getNewRevision();
		} catch (SVNException e) {
			logger.error("import error");
			return null;
		}

	}

	/**
	 * 签出到工作站
	 * 
	 * @return
	 */
	public Long doCheckout() {

		return doCheckout(workpath);

	}

	/**
	 * 签出到指定目录
	 * 
	 * @return
	 */
	public Long doCheckout(String path) {

		File workDir = new File(path);
		if (!workDir.exists()) {
			logger.info("workpath not exists, create workpath");
			workDir.mkdirs();
		}

		try {

			SVNUpdateClient updateClient = clientManager.getUpdateClient();
			updateClient.setIgnoreExternals(false);
			long version = updateClient
					.doCheckout(repositoryURL, workDir, SVNRevision.HEAD,
							SVNRevision.HEAD, SVNDepth.INFINITY, true);

			logger.info("checkout success");
			return version;

		} catch (SVNException svne) {
			logger.error("checkout error :" + svne.getMessage());
			return null;
		}

	}

	/**
	 * 增加文件
	 * 
	 * @param path
	 * @return
	 */
	public Long doAdd(File... files) {

		SVNStatus status = null;

		for (File file : files) {
			try {
				status = clientManager.getStatusClient().doStatus(file, true);
				logger.info("get status success, value is " + status);
			} catch (SVNException e) {
				logger.error("get status error");
				return null;
			}

			if (null == status
					|| SVNStatusType.STATUS_UNVERSIONED == status
							.getContentsStatus()) {
				try {
					clientManager.getWCClient().doAdd(file, false, false,
							false, SVNDepth.INFINITY, false, false);
					logger.info("add succcess");
				} catch (SVNException e) {
					logger.error("add error");
					return null;
				}
			}
		}

		return doCommit(files);

	}

	/**
	 * 提交文件
	 * 
	 * @param file
	 * @return
	 */
	public Long doCommit(File... files) {

		SVNCommitInfo commitInfo = null;

		try {
			commitInfo = clientManager.getCommitClient().doCommit(files, true,
					"", null, null, true, false, SVNDepth.INFINITY);
			logger.info("commit succcess, detail is " + commitInfo.toString());
			return commitInfo.getNewRevision();
		} catch (SVNException e) {
			logger.error("commit error");
			return null;
		}

	}

	/**
	 * 更新文件
	 * 
	 * @param path
	 * @return
	 */
	public Long doUpdate(File... files) {

		SVNUpdateClient updateClient = clientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);

		long version = 0;
		long tempVersion = 0;

		for (File file : files) {
			try {
				tempVersion = updateClient.doUpdate(file, SVNRevision.HEAD,
						SVNDepth.INFINITY, false, false);
				logger.info("update succcess");
				version = (version > tempVersion) ? version : tempVersion;
			} catch (SVNException e) {
				logger.error("update error");
				return null;
			}
		}
		return version;
	}

	/**
	 * 锁定
	 * 
	 * @param file
	 */
	public void doLock(File... files) {
		try {
			clientManager.getWCClient().doLock(files, true, "lock");
			logger.info("lock succcess");
		} catch (SVNException e) {
			logger.error("lock error");
		}
	}

	/**
	 * 解除锁定
	 * 
	 * @param files
	 */
	public void doUnLock(File... files) {
		try {
			clientManager.getWCClient().doUnlock(files, true);
			logger.info("unlock succcess");
		} catch (SVNException e) {
			logger.error("unlock error");
		}
	}

}
