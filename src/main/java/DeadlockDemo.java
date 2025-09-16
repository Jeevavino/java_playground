public class DeadlockDemo {
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public static void main(String[] args) {
        DeadlockDemo demo = new DeadlockDemo();
        demo.runDeadlockExample();
    }

    public void runDeadlockExample() {
        // Using lambda expressions
        Thread thread1 = new Thread(() -> task1(), "Thread-1");
        Thread thread2 = new Thread(() -> task2(), "Thread-2");

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
            System.out.println("\nDeadlock detected!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void task1() {
        synchronized (resource1) {
            System.out.println("Thread-1: Got resource1");
            sleep(1000);
            System.out.println("Thread-1: Waiting for resource2...");
            synchronized (resource2) {
                System.out.println("Thread-1: Got resource2");
            }
        }
    }

    private void task2() {
        synchronized (resource2) {
            System.out.println("Thread-2: Got resource2");
            sleep(1000);
            System.out.println("Thread-2: Waiting for resource1...");
            synchronized (resource1) {
                System.out.println("Thread-2: Got resource1");
            }
        }
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
