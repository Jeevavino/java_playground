import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCreationDemo {
    private final AtomicInteger counter = new AtomicInteger(0);
    
    // Method 1: Extending Thread class (non-static)
    class MyThread extends Thread {
        private String taskName;
        
        public MyThread(String taskName) {
            this.taskName = taskName;
        }
        
        @Override
        public void run() {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 1 - Extending Thread: " + taskName + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
        }
    }
    
    // Method 2: Implementing Runnable interface (non-static)
    class MyTask implements Runnable {
        private String taskName;
        
        public MyTask(String taskName) {
            this.taskName = taskName;
        }
        
        @Override
        public void run() {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 2 - Implementing Runnable: " + taskName + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
        }
    }
    
    // Method 5: Callable that returns a value (non-static)
    class MyCallable implements Callable<String> {
        private String taskName;
        
        public MyCallable(String taskName) {
            this.taskName = taskName;
        }
        
        @Override
        public String call() throws Exception {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 5 - Callable: " + taskName + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
            return "Result from " + taskName + " (Task ID: " + taskId + ")";
        }
    }
    
    // Simulate some work (non-static)
    private void simulateWork() {
        try {
            Thread.sleep(1000); // Simulate 1 second of work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void runDemo() throws Exception {
        System.out.println("=== Java Thread Creation Methods Demo ===\n");
        
        // Method 1: Extending Thread class
        System.out.println("1. Creating threads by extending Thread class:");
        MyThread thread1 = new MyThread("ExtendedThread-1");
        MyThread thread2 = new MyThread("ExtendedThread-2");
        thread1.start();
        thread2.start();
        
        Thread.sleep(1500); // Wait for threads to complete
        
        // Method 2: Implementing Runnable interface
        System.out.println("\n2. Creating threads by implementing Runnable:");
        Thread thread3 = new Thread(new MyTask("RunnableTask-1"));
        Thread thread4 = new Thread(new MyTask("RunnableTask-2"));
        thread3.start();
        thread4.start();
        
        Thread.sleep(1500);
        
        // Method 3: Lambda expression (Java 8+)
        System.out.println("\n3. Creating threads using Lambda expressions:");
        Thread lambdaThread1 = new Thread(() -> {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 3 - Lambda Expression: LambdaTask-1" + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
        });
        
        Thread lambdaThread2 = new Thread(() -> {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 3 - Lambda Expression: LambdaTask-2" + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
        });
        
        lambdaThread1.start();
        lambdaThread2.start();
        
        Thread.sleep(1500);
        
        // Method 4: Executor framework (Thread Pool)
        System.out.println("\n4. Creating threads using Executor framework:");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit Runnable tasks
        executor.submit(new MyTask("ExecutorTask-1"));
        executor.submit(new MyTask("ExecutorTask-2"));
        
        // Submit Lambda tasks
        executor.submit(() -> {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 4 - Executor Lambda: ExecutorLambda-1" + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
        });
        
        Thread.sleep(2000);
        
        // Method 5: Callable with Future (returns value)
        System.out.println("\n5. Creating threads using Callable (with return value):");
        Future<String> future1 = executor.submit(new MyCallable("CallableTask-1"));
        Future<String> future2 = executor.submit(new MyCallable("CallableTask-2"));
        
        // Get results from Callable
        try {
            String result1 = future1.get(); // This blocks until result is available
            String result2 = future2.get();
            System.out.println("Received: " + result1);
            System.out.println("Received: " + result2);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        
        // Method 6: Virtual Threads (Java 21+) - Uncomment if using Java 21+
        /*
        System.out.println("\n6. Creating Virtual Threads (Java 21+):");
        Thread virtualThread1 = Thread.ofVirtual().start(() -> {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 6 - Virtual Thread: VirtualTask-1" + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
        });
        
        Thread virtualThread2 = Thread.ofVirtual().start(() -> {
            int taskId = counter.incrementAndGet();
            System.out.println("Method 6 - Virtual Thread: VirtualTask-2" + 
                             " | Task ID: " + taskId + 
                             " | Thread: " + Thread.currentThread().getName());
            simulateWork();
        });
        
        virtualThread1.join();
        virtualThread2.join();
        */
        
        // Cleanup
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        System.out.println("\n=== All threads completed! ===");
        System.out.println("Total tasks executed: " + counter.get());
    }
    
    public static void main(String[] args) throws Exception {
        ThreadCreationDemo demo = new ThreadCreationDemo();
        demo.runDemo();
    }
}
