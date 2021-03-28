package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidity {
    public static boolean isValidEmailAddress(String email)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isOnlyAlphabeticalChars(String word)
    {
        return !word.equals("") && word.matches("^[a-zA-Z]*$");
    }

    public static boolean isValidPasswordLength(String password)
    {

        return password.length()<=15 && password.length() != 0;
    }

    public static boolean isValidLithuanianPhoneNumber(String phoneNumber) {


        // Compile regular expression
        Pattern pattern = Pattern.compile("^\\+3706\\d\\d\\d\\d\\d\\d\\d$" ,Pattern.CASE_INSENSITIVE);
        Pattern alternatePattern = Pattern.compile("^86\\d\\d\\d\\d\\d\\d\\d$" ,Pattern.CASE_INSENSITIVE);
        // Match regex against phoneNumber
        Matcher matcher = pattern.matcher(phoneNumber);
        Matcher alternateMatcher = alternatePattern.matcher(phoneNumber);
        // Use results...
        return matcher.matches() || alternateMatcher.matches();
    }


}
