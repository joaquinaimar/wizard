package com.wizard.svn;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizard.svn.support.SvnOptions;


public class SvnOperationTest {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(SvnOperationTest.class);

	private SvnOperation operation = null;

	@Before
	public void setUp() {

		SvnOptions option = new SvnOptions();

		option.setRepositoryURL("http://svn.apache.org/repos/asf/hadoop/common/trunk");
		option.setName("anonymous");
//		option.setPassword("042888");
		option.setWorkPath("E:\\hadoop");

		this.operation = new SvnOperation(option);

	}

	@Test
	public void testDoUpdate() {

		Long version = this.operation.doCheckout();

		logger.info("version:" + version);

	}

}
