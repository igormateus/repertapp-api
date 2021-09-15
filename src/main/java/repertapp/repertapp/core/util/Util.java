package repertapp.repertapp.core.util;

public class Util {

    /**
     * Check if the string is null or empty
     * @param value
     * @return
     */
    public static boolean isNullOrEmpty(String value) {
        if (value != null && !value.isEmpty())
            return false;

        return true;
    }
    
}
