import java.util.concurrent.*;

public class SimpleThreadPoolDemo {
    
    // Simple task that simulates some work
    class SimpleTask implements Runnable {
        private String taskName;
        
        public SimpleTask(String taskName) {
            this.taskName = taskName;
        }
        
        @Override
        public void run() {
            System.out.println(taskName + " started by " + Thread.currentThread().getName());
            
            try {
                Thread.sleep(2000); // Simulate 2 seconds of work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println(taskName + " completed by " + Thread.currentThread().getName());
        }
    }
    
    public void runDemo() throws InterruptedException {
        System.out.println("=== Simple Thread Pool Demo ===");
        
        // Create thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit 6 tasks to the pool
        for (int i = 1; i <= 6; i++) {
            executor.submit(new SimpleTask("Task-" + i));
        }
        
        // Shutdown the pool
        executor.shutdown();
        
        // Wait for all tasks to complete
        executor.awaitTermination(30, TimeUnit.SECONDS);
        
        System.out.println("All tasks completed!");
    }
    
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPoolDemo demo = new SimpleThreadPoolDemo();
        demo.runDemo();
    }
}
