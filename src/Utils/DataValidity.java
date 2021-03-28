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
        return word != null && word.matches("^[a-zA-Z]*$");
    }

    public static boolean isValidPasswordLength(String password)
    {

        return password.length()<=15 && password.length() != 0;
    }


}
