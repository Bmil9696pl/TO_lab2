package DataDecoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataDecoder {
    public static String[] prepareAttributes(String input) {
        String message = "";
        String[] attributes = null;
        Pattern pattern = Pattern.compile("AAXX\\s([^\\n\\r]*.+?(?= 333 | 444 | 222 )|.*)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            message = matcher.group(1);
        }

        if(!message.equals("")) {
            attributes = message.split("\\s+");
        }

        return attributes;
    }
}
