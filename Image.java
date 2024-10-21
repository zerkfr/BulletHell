package main;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Image {
	
	private BufferedImage backgroundImage;
	
	public BufferedImage getImage(String file) {
    try {
        // Use ClassLoader to get the InputStream
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream(file);
        if (input != null) {
            backgroundImage = ImageIO.read(input);
        } else {
            System.err.println("Image not found");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return backgroundImage;
}
}
