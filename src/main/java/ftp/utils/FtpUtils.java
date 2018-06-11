package ftp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FtpUtils {

    public static final int RESPONSE_TYPE_RUNNING = 1;
    public static final int RESPONSE_TYPE_OK = 2;
    public static final int RESPONSE_TYPE_COMMAND_WAITING = 3;
    public static final int RESPONSE_TYPE_COMMAND_FAILED = 4;
    public static final int RESPONSE_TYPE_COMMAND_UNSUPPORTED = 5;

    public static int getResponseCode(String response) {

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            String responseCode = matcher.group();
            return Integer.valueOf(responseCode);
        } else {
            return 0;
        }
    }

    public static int getResponseType(String response) {
        int responseCode = getResponseCode(response);
        String responseCodeStr = String.valueOf(responseCode);
        if (responseCodeStr.startsWith("1")) return RESPONSE_TYPE_RUNNING;
        if (responseCodeStr.startsWith("2")) return RESPONSE_TYPE_OK;
        if (responseCodeStr.startsWith("3")) return RESPONSE_TYPE_COMMAND_WAITING;
        if (responseCodeStr.startsWith("4")) return RESPONSE_TYPE_COMMAND_FAILED;
        if (responseCodeStr.startsWith("5")) return RESPONSE_TYPE_COMMAND_UNSUPPORTED;

        return 0;
    }
}
