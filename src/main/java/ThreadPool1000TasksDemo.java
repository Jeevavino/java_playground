import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool1000TasksDemo {
    
    private AtomicInteger completedTasks = new AtomicInteger(0);
    private AtomicInteger activeTasks = new AtomicInteger(0);
    
    // Simple task that simulates work
    class NumberedTask implements Runnable {
        private int taskId;
        
        public NumberedTask(int taskId) {
            this.taskId = taskId;
        }
        
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int activeCount = activeTasks.incrementAndGet();
            
            // Print every 100th task to avoid too much output
            if (taskId % 100 == 0 || taskId <= 10) {
                System.out.println("Task " + taskId + " started by " + threadName + 
                                 " (Active: " + activeCount + ")");
            }
            
            try {
                // Simulate work (random time between 100-500ms)
                Thread.sleep(100 + (int)(Math.random() * 400));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            int completed = completedTasks.incrementAndGet();
            activeTasks.decrementAndGet();
            
            if (taskId % 100 == 0 || taskId <= 10) {
                System.out.println("Task " + taskId + " completed by " + threadName + 
                                 " (Completed: " + completed + "/1000)");
            }
        }
    }
    
    public void demonstrateSmallThreadPool() throws InterruptedException {
        System.out.println("=== Demo 1: Small ThreadPool (5 threads) handling 1000 tasks ===");
        
        completedTasks.set(0);
        activeTasks.set(0);
        
        // Create small thread pool with only 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        long startTime = System.currentTimeMillis();
        
        // Submit 1000 tasks
        System.out.println("Submitting 1000 tasks to 5-thread pool...");
        for (int i = 1; i <= 1000; i++) {
            executor.submit(new NumberedTask(i));
        }
        System.out.println("All 1000 tasks submitted! (Most are queued)");
        
        // Monitor progress
        monitorProgress(executor);
        
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("All tasks completed!");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Threads used: 5 (reused for 1000 tasks)");
        System.out.println();
    }
    
    public void demonstrateLargeThreadPool() throws InterruptedException {
        System.out.println("=== Demo 2: Large ThreadPool (50 threads) handling 1000 tasks ===");
        
        completedTasks.set(0);
        activeTasks.set(0);
        
        // Create larger thread pool with 50 threads
        ExecutorService executor = Executors.newFixedThreadPool(50);
        
        long startTime = System.currentTimeMillis();
        
        // Submit 1000 tasks
        System.out.println("Submitting 1000 tasks to 50-thread pool...");
        for (int i = 1; i <= 1000; i++) {
            executor.submit(new NumberedTask(i));
        }
        System.out.println("All 1000 tasks submitted!");
        
        // Monitor progress
        monitorProgress(executor);
        
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("All tasks completed!");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Threads used: 50 (much faster execution)");
        System.out.println();
    }
    
    public void demonstrateWithTaskQueue() throws InterruptedException {
        System.out.println("=== Demo 3: ThreadPool with Custom Queue Size ===");
        
        completedTasks.set(0);
        activeTasks.set(0);
        
        // Create custom thread pool with limited queue
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3,                              // Core threads
            3,                              // Max threads  
            60L, TimeUnit.SECONDS,          // Keep alive time
            new ArrayBlockingQueue<>(10)    // Queue size = 10
        );
        
        long startTime = System.currentTimeMillis();
        
        // Submit tasks and handle queue full scenario
        System.out.println("Submitting tasks to 3-thread pool with queue size 10...");
        
        int submitted = 0;
        for (int i = 1; i <= 50; i++) {
            try {
                executor.submit(new NumberedTask(i));
                submitted++;
                
                if (i <= 15) {
                    System.out.println("Submitted task " + i + 
                                     " (Queue size: " + executor.getQueue().size() + 
                                     ", Active: " + executor.getActiveCount() + ")");
                }
            } catch (RejectedExecutionException e) {
                System.out.println("Task " + i + " REJECTED! Queue is full.");
                break;
            }
        }
        
        System.out.println("Successfully submitted: " + submitted + " tasks");
        
        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
        
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println();
    }
    
    private void monitorProgress(ExecutorService executor) throws InterruptedException {
        // Monitor progress every 2 seconds
        while (!executor.isTerminated()) {
            Thread.sleep(2000);
            int completed = completedTasks.get();
            int active = activeTasks.get();
            
            if (completed < 1000) {
                System.out.println("Progress: " + completed + "/1000 completed, " + 
                                 active + " currently active");
            }
            
            if (completed >= 1000) break;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        ThreadPool1000TasksDemo demo = new ThreadPool1000TasksDemo();
        
        demo.demonstrateSmallThreadPool();
        Thread.sleep(2000);
        
        demo.demonstrateLargeThreadPool();
        Thread.sleep(2000);
        
        demo.demonstrateWithTaskQueue();
        
        System.out.println("=== How ThreadPool Handles 1000 Tasks ===");
        System.out.println("\n1. Task Submission:");
        System.out.println("   ✓ Tasks submitted instantly to queue");
        System.out.println("   ✓ No blocking during submission");
        
        System.out.println("\n2. Task Execution:");
        System.out.println("   ✓ Only pool-size threads execute simultaneously");
        System.out.println("   ✓ Remaining tasks wait in queue");
        System.out.println("   ✓ Threads pick next task when current completes");
        
        System.out.println("\n3. Thread Reuse:");
        System.out.println("   ✓ Same threads handle multiple tasks");
        System.out.println("   ✓ No thread creation/destruction overhead");
        
        System.out.println("\n4. Queue Management:");
        System.out.println("   ✓ Unlimited queue by default (LinkedBlockingQueue)");
        System.out.println("   ✓ Can set bounded queue for memory control");
        System.out.println("   ✓ Tasks rejected if queue full (with bounded queue)");
        
        System.out.println("\n5. Performance Impact:");
        System.out.println("   ✓ More threads = faster completion (up to optimal point)");
        System.out.println("   ✓ Too many threads = context switching overhead");
        System.out.println("   ✓ Optimal size depends on task type (CPU vs I/O bound)");
    }
}
