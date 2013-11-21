package com.wizard.util.common;

import junit.framework.Assert;

import org.junit.Test;

import com.wizard.util.common.MathUtil;

public class MathUtilTest {

	@Test
	public void testMod() {
		int out = MathUtil.mod(11, 10);
		Assert.assertEquals(1, out);
	}

	@Test
	public void testRound01() {
		double in = 1.102;
		double out = MathUtil.round(in, 2);
		Assert.assertEquals(1.10, out);
	}

	@Test
	public void testRound02() {
		double in = 1.105;
		double out = MathUtil.round(in, 2);
		Assert.assertEquals(1.11, out);
	}

	@Test
	public void testRound03() {
		double in = 1.105;
		double out = MathUtil.round(in);
		Assert.assertEquals(1d, out);
	}

	@Test
	public void testRound04() {
		double in = 1.5;
		double out = MathUtil.round(in);
		Assert.assertEquals(2d, out);
	}

	@Test
	public void testCeil01() {
		double in = 1.59;
		double out = MathUtil.ceil(in, 1);
		Assert.assertEquals(1.6, out);
	}

	@Test
	public void testCeil02() {
		double in = 1.59;
		double out = MathUtil.ceil(in);
		Assert.assertEquals(2.0, out);
	}

	@Test
	public void testFloor01() {
		double in = 1.59;
		double out = MathUtil.floor(in, 1);
		Assert.assertEquals(1.5, out);
	}

	@Test
	public void testFloor02() {
		double in = 1.59;
		double out = MathUtil.floor(in);
		Assert.assertEquals(1.0, out);
	}
	
	@Test
	public void testArithmetic(){
		String exp = "(1+2)*3";
		double out = MathUtil.arithmetic(exp);
		Assert.assertEquals(9.0, out);
	}

}
