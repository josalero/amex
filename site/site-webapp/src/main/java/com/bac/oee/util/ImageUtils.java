package com.bac.oee.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageUtils.
 */
public class ImageUtils {

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ImageUtils.class);

	/**
	 * Blur image.
	 * 
	 * @param imagePath
	 *            the image path
	 * @return the buffered image
	 */
	public static BufferedImage blurImage(String imagePath) {

		BufferedImage myImage = null;

		try {

			myImage = ImageIO.read(new FileInputStream(imagePath));

			int width = myImage.getWidth(null);
			int height = myImage.getHeight(null);

			BufferedImage biSrc = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			Graphics g1 = biSrc.getGraphics();
			g1.drawImage(myImage, 0, 0, null);

			BufferedImage biDest = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			// float data[] = { 0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f,
			// 0.0625f, 0.125f, 0.0625f };
			float data[] = { 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f,
					1 / 9f, 1 / 9f, 1 / 9f };

			Kernel kernel = new Kernel(3, 3, data);
			ConvolveOp convolve = new ConvolveOp(kernel,
					ConvolveOp.EDGE_ZERO_FILL, null);

			convolve.filter(biSrc, biDest);

			myImage = biDest;
			// BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3,
			// blurKernel));
			// myImage = blur.filter(myImage, null);
			// g1.dispose();

		} catch (Exception exception) {
			log.error(exception);
		}

		return myImage;
	}
}
