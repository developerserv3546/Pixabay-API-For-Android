package com.developer35.serega.pixabayapiforandroid.utils;


import java.util.HashMap;

public class StringConverter {

    private static final HashMap<String, String> IMAGE_TYPES;
    private static final HashMap<String, String> IMAGE_CATEGORIES;
    private static final HashMap<String, String> IMAGE_ORIENTATIONS;
    private static final String REGEX = "[^a-zA-Z0-9\\s]";
    private static final String REPLACEMENT = "";
    private static final String SPLIT_CHAR = "\\s";
    private static final String PLUS_CHAR = "+";
    private static final String URL_SEPARATOR = "/";
    private static final int MAX_LENGTH = 100;

    static {
        IMAGE_TYPES = new HashMap<>();
        IMAGE_TYPES.put("All images", "all");
        IMAGE_TYPES.put("Photos", "photo");
        IMAGE_TYPES.put("Vector graphics", "vector");
        IMAGE_TYPES.put("Illustrations", "illustration");

        IMAGE_CATEGORIES = new HashMap<>();
        IMAGE_CATEGORIES.put("Any category", "all");
        IMAGE_CATEGORIES.put("Fashion", "fashion");
        IMAGE_CATEGORIES.put("Nature", "nature");
        IMAGE_CATEGORIES.put("Backgrounds", "backgrounds");
        IMAGE_CATEGORIES.put("Education", "education");
        IMAGE_CATEGORIES.put("People", "people");
        IMAGE_CATEGORIES.put("Feelings", "feelings");
        IMAGE_CATEGORIES.put("Religion", "religion");
        IMAGE_CATEGORIES.put("Health", "health");
        IMAGE_CATEGORIES.put("Places", "places");
        IMAGE_CATEGORIES.put("Animals", "animals");
        IMAGE_CATEGORIES.put("Industry", "industry");
        IMAGE_CATEGORIES.put("Food", "food");
        IMAGE_CATEGORIES.put("Computer", "computer");
        IMAGE_CATEGORIES.put("Sports", "sports");
        IMAGE_CATEGORIES.put("Transportation", "transportation");
        IMAGE_CATEGORIES.put("Travel", "travel");
        IMAGE_CATEGORIES.put("Buildings", "buildings");
        IMAGE_CATEGORIES.put("Business", "business");
        IMAGE_CATEGORIES.put("Music", "music");

        IMAGE_ORIENTATIONS = new HashMap<>();
        IMAGE_ORIENTATIONS.put("Any orientation", "all");
        IMAGE_ORIENTATIONS.put("Horizontal", "horizontal");
        IMAGE_ORIENTATIONS.put("Vertical", "vertical");
    }

    public static String getQueryString(String input) {

        input = input.replaceAll(REGEX, REPLACEMENT);
        String[] words = input.split(SPLIT_CHAR);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                builder.append(words[i]);
                if (i + 1 < words.length) {
                    builder.append(PLUS_CHAR);
                }
            }
        }

        String query = builder.toString();

        if (query.length() > MAX_LENGTH) {
            query = query.substring(0, MAX_LENGTH);
            if (query.endsWith(PLUS_CHAR)) {
                query = query.substring(0, query.length() - 1);
            }
        }

        return query;
    }

    public static String getImageNameFromUrl(String url) {
        int index = url.lastIndexOf(URL_SEPARATOR);
        return url.substring(index);
    }

    public static String getImageTypeQuery(String key) {
        return IMAGE_TYPES.get(key);
    }

    public static String getImageCategoryQuery(String key) {
        return IMAGE_CATEGORIES.get(key);
    }

    public static String getImageOrientationQuery(String key) {
        return IMAGE_ORIENTATIONS.get(key);
    }
}
