package com.sun.moviedb_45.util;

public class StringUtils {
    public static String getImage(String image_url) {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.BASE_IMAGE_PATH)
                .append(Constants.IMAGE_SIZE_W200)
                .append(image_url);
        return builder.toString();
    }
}
