
public class LambdaRunnableDemo {

    public static void main(String[] args) throws InterruptedException {


        // 1. ANONYMOUS CLASS - Verbose syntax
        System.out.println("=== Anonymous Class ===");
        Runnable anonymousTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class running on: " + Thread.currentThread().getName());
            }
        };

        Thread thread1 = new Thread(anonymousTask);
        thread1.start();
        thread1.join();

        // 2. LAMBDA EXPRESSION - Concise syntax
        System.out.println("\n=== Lambda Expression ===");
        Runnable lambdaTask = () -> {
            System.out.println("Lambda running on: " + Thread.currentThread().getName());
        };

        Thread thread2 = new Thread(lambdaTask);
        thread2.start();
        thread2.join();

        // 3. INLINE COMPARISON
        System.out.println("\n=== Side by Side ===");

        // Anonymous - 5 lines
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous: Hello World!");
            }
        }).start();

        // Lambda - 1 line
        new Thread(() -> System.out.println("Lambda: Hello World!")).start();

        Thread.sleep(100); // Let threads finish

        // 4. KEY DIFFERENCES
        System.out.println("\n=== Key Differences ===");
        System.out.println("Anonymous Class:");
        System.out.println("  ❌ Verbose (5+ lines)");
        System.out.println("  ❌ Requires @Override");
        System.out.println("  ✅ Can have multiple methods");
        System.out.println("  ✅ Can have instance variables");

        System.out.println("\nLambda Expression:");
        System.out.println("  ✅ Concise (1 line possible)");
        System.out.println("  ✅ No @Override needed");
        System.out.println("  ❌ Single method only");
        System.out.println("  ❌ No instance variables");
    }
}
