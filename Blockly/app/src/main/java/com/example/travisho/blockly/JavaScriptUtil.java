package com.example.travisho.blockly;

/**
 * Created by Travis Ho on 4/29/2017.
 */

public class JavaScriptUtil {
    /**
     * Creates a double quoted Javascript string, escaping backslashes, forward slashes, single
     * quotes, double quotes, and escape characters.
     */
    public static String makeJsString(String str) {
        // TODO(#17): More complete character escaping: unicode characters to hex, octal, or \\u.
        String escapedStr = str.replace("\\", "\\\\")  // Must escape backslashes first.
                .replace("</", "<\\/")  // See: http://stackoverflow.com/a/6117915
                .replace("\'", "\\\'")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
        return "\"" + escapedStr + "\"";
    }

    private JavaScriptUtil() {} // Do not instantiate.
}
