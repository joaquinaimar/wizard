package com.wizard.multimedia.image;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ImageCodeUtil {
	private ImageCodeUtil() {
	}

	public static void encodeQRCode(String content, int width, int height,
			OutputStream stream) {

		Map<EncodeHintType, Object> hts = new HashMap<EncodeHintType, Object>();
		hts.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hts.put(EncodeHintType.CHARACTER_SET, "utf-8");

		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, width, height, hts);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
		} catch (Exception e) {
		}

	}

	public static String decodeQRCode(InputStream input) {

		try {
			BufferedImage image = ImageIO.read(input);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
			Result result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();
		} catch (Exception e) {
		}

		return null;
	}

}
