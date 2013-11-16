package com.wizard.common;

import junit.framework.Assert;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testDoInitials01() {
		String in = "abc";
		String out = StringUtil.doInitials(in);
		Assert.assertEquals("Abc", out);
	}

	@Test
	public void testDoInitials02() {
		String in = " abc def ";
		String out = StringUtil.doInitials(in);
		Assert.assertEquals(" Abc Def ", out);
	}

	@Test
	public void testUnderscodeToHump01() {
		String in = "abc_def";
		String out = StringUtil.changeUnderscodeToHump(in);
		Assert.assertEquals("abcDef", out);
	}

	@Test
	public void testChangeUnderscodeToHump02() {
		String in = "abc__def";
		String out = StringUtil.changeUnderscodeToHump(in);
		Assert.assertEquals("abcDef", out);
	}

	@Test
	public void testChangeHumpToUnderscode01() {
		String in = "abcDef";
		String out = StringUtil.changeHumpToUnderscode(in);
		Assert.assertEquals("abc_def", out);
	}

	@Test
	public void testChangeHumpToUnderscode02() {
		String in = "abc";
		String out = StringUtil.changeHumpToUnderscode(in);
		Assert.assertEquals("abc", out);
	}

}
