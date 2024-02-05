package SourceProgramReader;

public class Utility {
    public static final int WORD_SIZE = 16;
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
