public class StringUtils {


    // Checks if a string is null or empty
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // Capitalizes the first letter of a string
    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Reverses a string
    public static String reverse(String str) {
        if (str == null) return null;
        return new StringBuilder(str).reverse().toString();
    }

    // Removes all whitespace from a string
    public static String removeWhitespace(String str) {
        if (str == null) return null;
        return str.replaceAll("\\s+", "");
    }

}

 class MainDemo {
    public static void main(String[] args) {
        String name = "  balamurugan  ";

        System.out.println(StringUtils.capitalize(name.trim())); // Output: Balamurugan
        System.out.println(StringUtils.reverse("Java"));         // Output: avaJ
        System.out.println(StringUtils.isNullOrEmpty(""));       // Output: true
        System.out.println(StringUtils.removeWhitespace("a b c"));// Output: abc
    }
}

