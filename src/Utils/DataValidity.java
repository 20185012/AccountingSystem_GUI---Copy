package Utils;

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
        return password.length()<=15 && password.length() > 0;
    }
    public static boolean isValidNumber(String phoneNumber)
    {
        return phoneNumber.length()<=15 && phoneNumber.length() > 5;
    }
    public static boolean isValidNumberStartWithPlus(String phoneNumber){
        String PhonePattern = "^[+][0-9]+";
        java.util.regex.Pattern pp = java.util.regex.Pattern.compile(PhonePattern);
        java.util.regex.Matcher mm = pp.matcher(phoneNumber);
        return mm.matches();
    }
    public static boolean isValidNameLength(String name)
    {
        return name.length()<=30 && name.length() >= 1;
    }
    public static boolean isValidNameAlphabeticForm(String name){
        return !name.equals("") && name.matches("^[a-zA-Z]*$");
    }
}
