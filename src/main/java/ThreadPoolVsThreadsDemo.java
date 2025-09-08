import java.util.concurrent.*;

public class ThreadPoolVsThreadsDemo {
    
    // Simple task that simulates work
    class WorkTask implements Runnable {
        private String taskName;
        
        public WorkTask(String taskName) {
            this.taskName = taskName;
        }
        
        @Override
        public void run() {
            System.out.println(taskName + " started by " + Thread.currentThread().getName());
            
            try {
                Thread.sleep(1000); // Simulate 1 second of work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println(taskName + " completed by " + Thread.currentThread().getName());
        }
    }
    
    // Demo 1: Using individual threads (problems)
    public void demonstrateIndividualThreads() throws InterruptedException {
        System.out.println("=== Using Individual Threads (Problems) ===");
        
        long startTime = System.currentTimeMillis();
        
        // Create 10 individual threads
        Thread[] threads = new Thread[10];
        
        for (int i = 1; i <= 10; i++) {
            threads[i-1] = new Thread(new WorkTask("Task-" + i));
            threads[i-1].start();
            System.out.println("Created thread: " + threads[i-1].getName());
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Threads created: 10");
        System.out.println("Memory used: ~10MB (1MB per thread stack)");
        System.out.println();
    }
    
    // Demo 2: Using ThreadPool (solution)
    public void demonstrateThreadPool() throws InterruptedException {
        System.out.println("=== Using ThreadPool (Solution) ===");
        
        long startTime = System.currentTimeMillis();
        
        // Create thread pool with only 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit 10 tasks to the pool
        for (int i = 1; i <= 10; i++) {
            executor.submit(new WorkTask("PoolTask-" + i));
        }
        
        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Threads created: 3 (reused for 10 tasks)");
        System.out.println("Memory used: ~3MB (3 threads only)");
        System.out.println();
    }
    
    // Demo 3: Heavy load comparison
    public void demonstrateHeavyLoad() throws InterruptedException {
        System.out.println("=== Heavy Load Comparison ===");
        
        int taskCount = 100;
        
        // Individual threads approach
        System.out.println("1. Individual Threads for " + taskCount + " tasks:");
        long start1 = System.currentTimeMillis();
        
        Thread[] threads = new Thread[taskCount];
        for (int i = 0; i < taskCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(100); // Quick task
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        long end1 = System.currentTimeMillis();
        System.out.println("   Time: " + (end1 - start1) + "ms");
        System.out.println("   Threads created: " + taskCount);
        System.out.println("   Memory impact: HIGH (~" + taskCount + "MB)");
        
        // ThreadPool approach
        System.out.println("\n2. ThreadPool for " + taskCount + " tasks:");
        long start2 = System.currentTimeMillis();
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(100); // Same quick task
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
        
        long end2 = System.currentTimeMillis();
        System.out.println("   Time: " + (end2 - start2) + "ms");
        System.out.println("   Threads created: 10 (reused)");
        System.out.println("   Memory impact: LOW (~10MB)");
        System.out.println();
    }
    
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolVsThreadsDemo demo = new ThreadPoolVsThreadsDemo();
        
        demo.demonstrateIndividualThreads();
        Thread.sleep(1000);
        
        demo.demonstrateThreadPool();
        Thread.sleep(1000);
        
        demo.demonstrateHeavyLoad();
        
        System.out.println("=== Why ThreadPool is Required ===");
        System.out.println("\nProblems with Individual Threads:");
        System.out.println("❌ High memory usage (1MB per thread)");
        System.out.println("❌ Thread creation overhead");
        System.out.println("❌ No limit on concurrent threads");
        System.out.println("❌ System can run out of resources");
        System.out.println("❌ Context switching overhead");
        
        System.out.println("\nBenefits of ThreadPool:");
        System.out.println("✅ Limited memory usage");
        System.out.println("✅ Thread reuse (no creation overhead)");
        System.out.println("✅ Controlled concurrency");
        System.out.println("✅ Better resource management");
        System.out.println("✅ Task queuing when threads busy");
        System.out.println("✅ Automatic thread lifecycle management");
    }
}
