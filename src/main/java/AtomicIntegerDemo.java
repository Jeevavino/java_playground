import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== AtomicInteger Demo ===\n");

        // Test 1: Regular Integer (BROKEN)
        RegularCounter regularCounter = new RegularCounter();
        regularCounter.test();

        System.out.println();

        // Test 2: AtomicInteger (WORKS)
        AtomicCounter atomicCounter = new AtomicCounter();
        atomicCounter.test();
    }
}


// Problem: Using regular Integer wrapper
class RegularCounter {

    private Integer counter = 0;

    public void test() throws InterruptedException {
        System.out.println("❌ Test 1: Regular Integer");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Expected: 2000");
        System.out.println("Got:      " + counter);
    }
}


// Solution: Using AtomicInteger
class AtomicCounter {

    private AtomicInteger counter = new AtomicInteger(0);

    public void test() throws InterruptedException {
        System.out.println("✅ Test 2: AtomicInteger");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Expected: 2000");
        System.out.println("Got:      " + counter.get());
    }
}
