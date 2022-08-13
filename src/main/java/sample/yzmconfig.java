package sample;

public class yzmconfig {

    public static int num;

    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z \\d A-Z]+$";
        return str.matches(regex);
    }

    public static boolean yzmnum(String yzm) {
        return yzm.length() == num;
    }

}
