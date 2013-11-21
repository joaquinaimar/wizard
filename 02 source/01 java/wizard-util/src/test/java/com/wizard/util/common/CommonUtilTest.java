package com.wizard.util.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wizard.util.common.CommonUtil;

public class CommonUtilTest {

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
