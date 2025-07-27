package com.anshul.qrvite.service;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.zxing.common.BitMatrix;

@Service
@Transactional
public class GenralUtilService {
	

	public BufferedImage toGradientQRCode(BitMatrix matrix) {
    	
   	 int width = matrix.getWidth();
   	    int height = matrix.getHeight();

   	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
   	    Graphics2D g2d = image.createGraphics();

   	    // Enable anti-aliasing (optional)
   	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

   	    // Create a vertical gradient for QR modules
   	    GradientPaint gradient = new GradientPaint(
   	            0, 0, new Color(30, 144, 255),        // DodgerBlue at top
   	            0, height, new Color(138, 43, 226)    // BlueViolet at bottom
   	    );
   	    g2d.setPaint(gradient);

   	    // Only draw the QR modules where matrix.get(x,y) is true
   	    for (int x = 0; x < width; x++) {
   	        for (int y = 0; y < height; y++) {
   	            if (matrix.get(x, y)) {
   	                g2d.fillRect(x, y, 1, 1);
   	            }
   	        }
   	    }

   	    g2d.dispose();
   	    return image;
   }
	
	// Converts Google Drive shareable link to direct download link
    public String convertGoogleDriveUrlToDirectLink(String inputUrl) {
        // Example: https://drive.google.com/file/d/FILE_ID/view?usp=sharing
        if (inputUrl.contains("drive.google.com")) {
            String fileId = inputUrl.split("/d/")[1].split("/")[0];
            return "https://drive.google.com/uc?export=download&id=" + fileId;
        }
        return inputUrl; // If it's already direct
    }

}
