public class StringImmutabilityDemo {

    public static void main(String[] args) {
        System.out.println("=== STRING IMMUTABILITY & STRING POOL DEMO ===\n");

        demonstrateImmutability();
        demonstrateStringPool();
        demonstratePerformance();
    }

    // 1. String Immutability
    public static void demonstrateImmutability() {
        System.out.println("1. IMMUTABILITY:");
        String str = "Hello";
        String original = str;

        str = str + " World";  // Creates new object

        System.out.println("Original: " + original);  // Still "Hello"
        System.out.println("Modified: " + str);       // "Hello World"
        System.out.println("Same reference? " + (original == str));  // false
        System.out.println();
    }

    // 2. String Pool
    public static void demonstrateStringPool() {
        System.out.println("2. STRING POOL:");

        // String literals - stored in pool
        String s1 = "Java";
        String s2 = "Java";

        // New keyword - stored in heap
        String s3 = new String("Java");
        String s4 = new String("Java");

        System.out.println("Literals (s1 == s2): " + (s1 == s2));     // true
        System.out.println("New objects (s3 == s4): " + (s3 == s4));  // false
        System.out.println("Literal vs new (s1 == s3): " + (s1 == s3)); // false
        System.out.println("Content equal (s1.equals(s3)): " + s1.equals(s3)); // true
        System.out.println();
    }

    // 3. Performance Impact
    public static void demonstratePerformance() {
        System.out.println("4. PERFORMANCE IMPACT:");

        // String concatenation - creates many objects
        long start = System.nanoTime();
        String str = "";
        for (int i = 0; i < 1000; i++) {
            str += "a";
        }
        long stringTime = System.nanoTime() - start;

        // StringBuilder - mutable, efficient
        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("a");
        }
        String result = sb.toString();
        long sbTime = System.nanoTime() - start;

        System.out.println("String concatenation: " + stringTime + " ns");
        System.out.println("StringBuilder: " + sbTime + " ns");
        System.out.println("StringBuilder is " + (stringTime/sbTime) + "x faster");
    }
}