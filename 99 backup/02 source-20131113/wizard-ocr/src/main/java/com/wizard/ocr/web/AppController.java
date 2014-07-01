package com.wizard.ocr.web;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.ocr.bean.InfoBean;
import com.wizard.ocr.bean.Response;

@Controller
@RequestMapping("app")
public class AppController {

	@RequestMapping(value = "/getInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Response<InfoBean> doGetInfo(@RequestParam int[] imgdata,
			@RequestParam int width, @RequestParam int height,
			HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_4BYTE_ABGR);

		int i = 0;

		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {

				int r = imgdata[i];
				int g = imgdata[i + 1];
				int b = imgdata[i + 2];
				int a = imgdata[i + 3];

				if (255 <= (r + g + b)) {
					r = 255;
					g = 255;
					b = 255;
				}

				int rgb = ((a & 255) << 24) + ((r & 255) << 16)
						+ ((b & 255) << 8) + (g & 255);
				i += 4;
				image.setRGB(x, y, rgb);
			}
		}

		File file = new File("D:\\1.gif");

		if (file.exists()) {
			file.delete();
		}

		ImageIO.write(image, "gif", file);

		Runtime.getRuntime().exec(
				"tesseract D:\\1.gif D:\\1 -l chi_sim -psm 7 num");

		InfoBean info = new InfoBean();
		info.setCode(readResult());
		info.setPhone(phoneNum());
		info.setId(idNum());

		removeFile();

		return new Response<InfoBean>(true, info);
	}

	private void removeFile() {
		File file1 = new File("D:\\1.gif");

		if (file1.exists()) {
			file1.delete();
		}

		File file2 = new File("D:\\1.txt");

		if (file2.exists()) {
			file2.delete();
		}

	}

	private String readResult() {
		BufferedReader reader = null;
		File file = new File("D:\\1.txt");
		String result = "";
		while (!file.exists()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			file = new File("D:\\1.txt");
		}
		try {
			reader = new BufferedReader(new FileReader(file));
			result = reader.readLine();
			reader.close();
		} catch (IOException e) {
			result = readResult();
		}
		return result;
	}

	private String phoneNum() {
		String[] head = { "130", "131", "137", "138", "139", "159", "180" };
		return head[(int) Math.random() * 6]
				+ leftFillMethod(String.valueOf(random(0, 100000000)), 8);
	}

	public String leftFillMethod(String str, int j) {
		String nstr = "";
		for (int i = 0; i < j - str.length(); i++) {
			nstr += "0";
		}
		nstr = nstr + str;
		return nstr;

	}

	private String idNum() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = randomDate("1950-1-1", "1995-12-31");
		return "12010" + ((int) Math.random() * 6) + format.format(date)
				+ leftFillMethod(String.valueOf(random(0, 10000)), 4);
	}

	private static Date randomDate(String begindate, String enddate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(begindate);
			Date end = format.parse(enddate);
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());
			return new Date(date);
		} catch (Exception e) {
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}

}
