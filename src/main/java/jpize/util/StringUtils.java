package jpize.util;

public class StringUtils {

    public static boolean isEmpty(CharSequence string) {
        return (string == null || string.length() == 0);
    }

    public static boolean isNotEmpty(CharSequence string) {
        return (string != null && string.length() != 0);
    }


    public static boolean isBlank(CharSequence string) {
        if(isEmpty(string))
            return true;

        for(int i = 0; i < string.length(); i++)
            if(!Character.isWhitespace(string.charAt(i)))
                return false;

        return true;
    }


    public static int countMatches(CharSequence string, char c) {
        if(isEmpty(string))
            return 0;

        int count = 0;
        for(int i = 0; i < string.length(); i++)
            if(c == string.charAt(i))
                count++;

        return count;
    }

    public static int countMatches(CharSequence string, CharSequence substring) {
        if(isEmpty(string) || isEmpty(substring))
            return 0;

        int count = 0;
        int index = 0;
        while((index = indexOf(string, substring, index)) != -1){
            count++;
            index += substring.length();
        }
        return count;
    }

    static int indexOf(CharSequence string, CharSequence target, int start) {
        if(string instanceof String)
            return ((String) string).indexOf(target.toString(), start);
        if(string instanceof StringBuilder)
            return ((StringBuilder) string).indexOf(target.toString(), start);
        if(string instanceof StringBuffer)
            return ((StringBuffer) string).indexOf(target.toString(), start);
        return string.toString().indexOf(target.toString(), start);
    }


    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
