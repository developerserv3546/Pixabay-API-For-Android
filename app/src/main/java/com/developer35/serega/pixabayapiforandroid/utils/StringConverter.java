package com.developer35.serega.pixabayapiforandroid.utils;


public class StringConverter {

    private static final String REGEX = "[^a-zA-Z0-9\\s]";
    private static final String REPLACEMENT = "";
    private static final String SPLIT_CHAR = "\\s";
    private static final String PLUS_CHAR = "+";
    private static final int MAX_LENGTH = 100;

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
}
