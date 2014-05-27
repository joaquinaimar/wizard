package com.wizard.multimedia.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import junit.framework.Assert;

import org.junit.Test;

import com.wizard.multimedia.image.support.ImageType;

public class ImageCodeUtilTest {

	@Test
	public void testEncodeQRCode() {
		try {
			FileOutputStream out = new FileOutputStream(
					new File("D:\\test.png"));
			ImageCodeUtil.encodeQRCode("TEST", 500, 500, out);
		} catch (FileNotFoundException e) {
			Assert.fail();
		}
	}

	@Test
	public void testDecodeQRCode() {
		try {
			FileInputStream in = new FileInputStream(new File("D:\\test.png"));
			String content = ImageCodeUtil.decodeQRCode(in);
			Assert.assertEquals("TEST", content);
		} catch (FileNotFoundException e) {
			Assert.fail();
		}
	}

	@Test
	public void testPdfToImage01() {
		try {
			ImageCodeUtil.convertPdfToImage(new File("D:\\pdf\\python.pdf"),
					"D:\\pdf\\tif\\", ImageType.TYPE_TIFF);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testPdfToImage02() {
		try {
			ImageCodeUtil.convertPdfToImage(new File("D:\\pdf\\python.pdf"),
					"D:\\pdf\\jpg\\", ImageType.TYPE_JPEG);
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
