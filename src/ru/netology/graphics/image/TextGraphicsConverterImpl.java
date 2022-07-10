package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterImpl implements TextGraphicsConverter {

    private double maxRatio;
    private int maxwidth;
    private int maxHeight;
    private TextColorSchema schema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        StringBuilder charBuilder = new StringBuilder();
        if ((double) img.getWidth() / img.getHeight() > maxRatio && maxRatio > 0) {
            throw new BadImageSizeException((double) img.getWidth() / img.getHeight(), maxRatio);
        }
        int newWidth = 0;
        int newHeight = 0;
        if (maxwidth > 0 || maxHeight > 0 && img.getWidth() > maxwidth || img.getHeight() > maxHeight) {
            double temp1 = (double) img.getWidth() / maxwidth;
            double temp2 = (double) img.getHeight() / maxHeight;
            if (temp1 > temp2) {
                newWidth = (int) (Math.round(img.getWidth() / temp1));
                newHeight = (int) (Math.round(img.getHeight() / temp1));
            } else {
                newWidth = (int) (Math.round(img.getWidth() / temp2));
                newHeight = (int) (Math.round(img.getHeight() / temp2));
            }
        } else {
            newHeight = img.getHeight();
            newWidth = img.getWidth();
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();

        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                charBuilder.append(c);
            }
            charBuilder.append("\n");
        }
        return charBuilder.toString();

    }

    @Override
    public void setMaxWidth(int width) {
        this.maxwidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }


}
