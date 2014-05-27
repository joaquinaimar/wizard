package com.wizard.multimedia.image;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.wizard.multimedia.image.support.ImageType;

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

	public static void convertPdfToImage(File file, String createPath, int type)
			throws Exception {
		@SuppressWarnings("resource")
		FileChannel channel = new RandomAccessFile(file, "r").getChannel();
		MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				channel.size());
		PDFFile pdffile = new PDFFile(buf);

		for (int i = 1; i <= pdffile.getNumPages(); i++) {
			PDFPage page = pdffile.getPage(i);
			Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()
					.getWidth(), (int) page.getBBox().getHeight());
			Image img = page.getImage(rect.width, rect.height, rect, null,
					true, true);

			BufferedImage tag = new BufferedImage(rect.width, rect.height,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
					null);

			String fileName = createPath
					+ "\\"
					+ file.getName().substring(0,
							file.getName().lastIndexOf('.'));
			FileOutputStream out = null;
			ImageEncoder enc = null;
			switch (type) {
			case ImageType.TYPE_JPEG:
				out = new FileOutputStream(fileName + i + ".jpg");
				enc = ImageCodec.createImageEncoder("jpeg", out,
						new JPEGEncodeParam());
				break;
			case ImageType.TYPE_TIFF:
				out = new FileOutputStream(fileName + i + ".tif");
				enc = ImageCodec.createImageEncoder("tiff", out,
						new TIFFEncodeParam());
				break;
			}
			if (null != out && null != enc) {
				enc.encode(tag);
				out.close();
			}

		}
	}
}
