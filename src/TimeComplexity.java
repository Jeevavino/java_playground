import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class TimeComplexity {

    public static void main(String[] args) {
        TimeComplexity demo = new TimeComplexity();
        int size = 10000000;

        System.out.println("Time Complexity Demo (n = " + size + ")");
        System.out.println("=====================================");

        demo.testConstant(size);
        demo.testLinear(size);
        demo.testQuadratic(size);
        demo.testLogarithmic(size);
        demo.testNLogN(size);
    }

    // O(1) - Constant Time
    void testConstant(int size) {
        Instant start = Instant.now();

        // Simple multiple operations - all O(1)
        int a = 42;
        int b = 17;

        // Basic operations
        int sum = a + b;
        int product = a * b;
        int difference = a - b;

        // Bitwise and comparison
        int bitwiseOr = a | b;
        boolean isGreater = sum > product;

        // Array access
        int[] arr = {sum, product, difference};
        int result = arr[0] + arr[2] + 3;

        // Final computation
        int finalResult = result + bitwiseOr + (isGreater ? 1 : 0);

        Instant end = Instant.now();
        System.out.printf("O(1) Constant:    %,8d ns (result: %d)\n",
                Duration.between(start, end).toNanos(), finalResult);
    }



    // O(n) - Linear Time
    void testLinear(int size) {
        Instant start = Instant.now();

        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += i;
        }

        Instant end = Instant.now();
        System.out.printf("O(n) Linear:      %,8d ns\n",
                Duration.between(start, end).toNanos());
    }

    // O(n²) - Quadratic Time
    void testQuadratic(int size) {
        Instant start = Instant.now();

        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                count++;
            }
        }

        Instant end = Instant.now();
        System.out.printf("O(n²) Quadratic:  %,8d ns\n",
                Duration.between(start, end).toNanos());
    }

    // O(log n) - Logarithmic Time
    void testLogarithmic(int size) {
        int[] sorted = new int[size];
        for (int i = 0; i < size; i++) {
            sorted[i] = i;
        }

        Instant start = Instant.now();

        Arrays.binarySearch(sorted, size/2);

        Instant end = Instant.now();
        System.out.printf("O(log n) Binary:  %,8d ns\n",
                Duration.between(start, end).toNanos());
    }

    // O(n log n) - Linearithmic Time
    void testNLogN(int size) {
        int[] random = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            random[i] = rand.nextInt();
        }

        Instant start = Instant.now();

        Arrays.sort(random);

        Instant end = Instant.now();
        System.out.printf("O(n log n) Sort:  %,8d ns\n",
                Duration.between(start, end).toNanos());
    }
}
