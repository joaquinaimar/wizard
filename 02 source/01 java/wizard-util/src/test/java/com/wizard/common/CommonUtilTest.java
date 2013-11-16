package com.wizard.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CommonUtilTest {

	@Test
	public void testChangeListToArray() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");

		String[] results = CommonUtil.changeListToArray(list, String.class);

		String[] expecteds = { "a", "b" };
		Assert.assertArrayEquals(expecteds, results);

	}
}
