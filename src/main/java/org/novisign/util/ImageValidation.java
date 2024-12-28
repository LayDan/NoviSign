package org.novisign.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class ImageValidation {
    public static boolean isImageUrlValid(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            String contentType = connection.getContentType();
            return contentType != null && contentType.startsWith("image/");
        } catch (Exception e) {
            return false;
        }
    }
}
