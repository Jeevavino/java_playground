// Singelton.java
public class ThreadStackDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== THREAD STACK EXECUTION DEMO ===\n");

        // Create two threads
        Thread thread1 = new Thread(() -> executeWithStack("Thread-1"));
        Thread thread2 = new Thread(() -> executeWithStack("Thread-2"));

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for completion
        thread1.join();
        thread2.join();

        System.out.println("\nBoth threads completed!");
    }

    // Each thread will have its own stack when calling this method
    private static void executeWithStack(String threadName) {
        // Local variables - stored in each thread's separate stack
        int localCounter = 0;
        String localMessage = "Hello from " + threadName;

        System.out.println("🧵 " + threadName + " started with local variables");

        // Call methods to create stack frames
        for (int i = 1; i <= 3; i++) {
            localCounter++;
            processData(threadName, localCounter, i);
        }

        System.out.println("✅ " + threadName + " finished with counter: " + localCounter);
    }

    // This creates a new stack frame in each thread's stack
    private static void processData(String threadName, int counter, int iteration) {
        // More local variables in this stack frame
        String frameInfo = threadName + " - Frame " + iteration;
        int calculatedValue = counter * iteration;

        System.out.println("  📊 " + frameInfo + " | Value: " + calculatedValue);

        // Call another method to show stack depth
        validateData(threadName, calculatedValue);
    }

    // Another stack frame deeper in the call stack
    private static void validateData(String threadName, int value) {
        // Local variable in this stack frame
        boolean isValid = value > 0;

        System.out.println("    ✓ " + threadName + " validation: " + value + " is " +
                (isValid ? "valid" : "invalid"));

        // Small delay to see interleaved execution
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
