import java.util.regex.*;

class Solution {
     public static String removeDigits(String s) {
        Pattern pattern = Pattern.compile("\\D?\\d"); // Matches a digit and the closest preceding non-digit character
        Matcher matcher;
        
        while ((matcher = pattern.matcher(s)).find()) {
            s = matcher.replaceFirst(""); // Replace the first occurrence
        }
        
        return s;
    }

    public String clearDigits(String s) {
        return removeDigits(s);
    }
}