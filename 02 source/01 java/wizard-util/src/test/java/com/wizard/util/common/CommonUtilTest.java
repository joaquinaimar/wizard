package com.wizard.util.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wizard.util.common.CommonUtil;

public class CommonUtilTest {

	@Test
	public void testConsoleOutPrint() {
		int i = 1;
		CommonUtil.consoleOutPrint(i);
	}

	@Test
	public void testConsoleOutPrintln() {
		int i = 2;
		CommonUtil.consoleOutPrintln(i);
	}

	@Test
	public void testConsoleErrPrint() {
		int i = 3;
		CommonUtil.consoleErrPrint(i);
	}

	@Test
	public void testConsoleErrPrintln() {
		int i = 4;
		CommonUtil.consoleErrPrintln(i);
	}

	@Test
	public void testChangeListToArray01() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");

		String[] results = CommonUtil.changeListToArray(list);

		String[] expecteds = { "a", "b" };
		Assert.assertArrayEquals(expecteds, results);

	}

	@Test
	public void testChangeListToArray02() {
		List<String> list = new ArrayList<String>();

		String[] results = CommonUtil.changeListToArray(list);

		String[] expecteds = null;
		Assert.assertArrayEquals(expecteds, results);

	}

}
