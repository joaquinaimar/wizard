package com.wizard.j2ee.validate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.wizard.j2ee.domain.FileVo;
import com.wizard.j2ee.util.SpringWebUtil;

public class ValidateCodeService {

	private final Random random = new Random();

	private String sessionKey = "ValidateCode";

	private String randomString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int width = 80;

	private int height = 26;

	private int lineSize = 40;

	private int stringNum = 4;

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	public boolean checkValidateCode(final String code) {
		return code.equals(SpringWebUtil.getSessionAttribute(sessionKey));
	}

	public FileVo getValidateCode() {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		String randomString = "";
		for (int i = 1; i <= stringNum; i++)
			randomString = drowString(g, randomString, i);
		SpringWebUtil.setSessionAttribute(sessionKey, randomString);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "gif", out);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_GIF);
			return new FileVo(out.toByteArray(), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			return null;
		} finally {
			g.dispose();
		}
	}

	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	private String drowString(Graphics g, String randomString, int i) {
		g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 18));
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
				.nextInt(121)));
		String rand = String.valueOf(getRandomString(random
				.nextInt(randomString.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return randomString;
	}

	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	private String getRandomString(int num) {
		return String.valueOf(randomString.charAt(num));
	}

}
