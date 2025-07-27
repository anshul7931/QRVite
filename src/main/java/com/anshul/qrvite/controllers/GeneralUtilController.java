package com.anshul.qrvite.controllers;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anshul.qrvite.constants.PageConstants;
import com.anshul.qrvite.service.GenralUtilService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Controller
public class GeneralUtilController {

	@Autowired
	GenralUtilService generalUtilService;
	
	@Autowired
	PageConstants pageConstants;
	
	@GetMapping("/error")
    public String viewErrorPage(@AuthenticationPrincipal UserDetails user) {
        return pageConstants.ERROR_404_PAGE;
    }


    @GetMapping("/generate-qr")
    @ResponseBody
    public ResponseEntity<byte[]> generateQRCode(
            @RequestParam String data,
            @RequestParam(defaultValue = "300") int size,
            @RequestParam(defaultValue = "gradient") String color
    ) throws Exception {

        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, size, size, hints);

        // Use custom gradient QR code generator here
        BufferedImage qrImage;
        if(color.equalsIgnoreCase("bw")) {
        	qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        }else {
        	qrImage = generalUtilService.toGradientQRCode(bitMatrix);
        }

        // Load and overlay logo as before
        ClassPathResource logoFile = new ClassPathResource("logo.png");
        BufferedImage logo = ImageIO.read(logoFile.getInputStream());

        int logoWidth = qrImage.getWidth() / 5;
        int logoHeight = qrImage.getHeight() / 5;
        int logoX = (qrImage.getWidth() - logoWidth) / 2;
        int logoY = (qrImage.getHeight() - logoHeight) / 2;

        Image scaledLogo = logo.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);

        Graphics2D g = qrImage.createGraphics();
        g.setComposite(AlphaComposite.SrcOver);
        g.drawImage(scaledLogo, logoX, logoY, null);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        byte[] pngData = baos.toByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .body(pngData);
    }


    
    @PostMapping(value = "/getImageFromDriveLink", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> fetchImage(@RequestParam String imgPath) {
        String imageUrl = generalUtilService.convertGoogleDriveUrlToDirectLink(imgPath);

        try {
            // Fetch the image bytes using Java's URL class
            URL url = new URL(imageUrl);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (InputStream is = url.openStream()) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            byte[] imageBytes = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // You can infer MIME type if needed
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(("Error fetching image: " + e.getMessage()).getBytes());
        }
    }

    
    
    
}
