package com.loong.x.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/1 17:01
 */
public class WaterMarkUtils {

    public static File addWatermark(File sourceImageFile, String watermarkText,String fileType, File destImageFile) throws IOException {
        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage markedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = markedImage.createGraphics();
        g2d.drawImage(sourceImage, 0, 0, null);
        Font font = new Font("宋体", Font.BOLD, 66);
        g2d.setFont(font);
        g2d.setColor(Color.RED);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(watermarkText, g2d);
        double x = (sourceImage.getWidth() - rect.getWidth()) / 2;
        double y = (sourceImage.getHeight() - rect.getHeight()) / 2;
        g2d.drawString(watermarkText, (int) x, (int) y);
        g2d.dispose();
        ImageIO.write(markedImage, fileType, destImageFile);
        return destImageFile;
    }
}
