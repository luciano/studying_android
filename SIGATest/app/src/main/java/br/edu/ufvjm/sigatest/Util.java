package br.edu.ufvjm.sigatest;

import android.util.Log;

public class Util {


    public static boolean isStringNullOrEmpty(String input) {
        if (input == null || input.length() == 0)
            return true;
        return false;
    }

    public static String SanitizeBaseUrl(String url) {
        return SanitizeBaseUrl(url, true);
    }

    public static String SanitizeBaseUrl(String url, boolean stripAnchor) {
        if (stripAnchor) {
            // strip anchor link
            if (url.contains("#")) {
                url = url.substring(0, url.indexOf('#'));

            }
        }
        // strip mobile redirect for blogspot
        if (url.contains(".blogspot.")) {
            url = url.replace("?m=1", "");

            if (!url.contains(".blogspot.com/")) {

                url = url.replaceFirst("blogspot.[a-z]+/", "blogspot.com/");
            }
        }
        return url;
    }
}