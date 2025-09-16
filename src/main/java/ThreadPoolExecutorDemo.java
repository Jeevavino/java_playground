import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        // Custom ThreadPoolExecutor with all parameters
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                              // corePoolSize: 2 core threads
                4,                              // maximumPoolSize: max 4 threads
                60L,                            // keepAliveTime: 60 seconds
                TimeUnit.SECONDS,               // unit: seconds
                new ArrayBlockingQueue<>(100),    // workQueue: bounded queue (size 3)
                new CustomThreadFactory(),      // threadFactory: custom factory
                new ThreadPoolExecutor.CallerRunsPolicy()  // handler: caller runs
        );


        executor.prestartAllCoreThreads();
        // Submit 10 tasks to demonstrate behavior
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            try {
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " running on: " +
                            Thread.currentThread().getName());
                    sleep(2000);
                });
                System.out.println("Submitted task " + taskId);
            } catch (Exception e) {
                System.out.println("Task " + taskId + " rejected: " + e.getMessage());
            }
        }

        // Monitor pool status
        monitorPool(executor);

        executor.shutdown();
    }

    // Custom ThreadFactory
    static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "CustomPool-Thread-" + threadNumber.getAndIncrement());
            thread.setDaemon(false);  // Non-daemon threads
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }

    private static void monitorPool(ThreadPoolExecutor executor) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("\n--- Pool Status ---");
                System.out.println("Core Pool Size: " + executor.getCorePoolSize());
                System.out.println("Current Pool Size: " + executor.getPoolSize());
                System.out.println("Active Threads: " + executor.getActiveCount());
                System.out.println("Queue Size: " + executor.getQueue().size());
                System.out.println("Completed Tasks: " + executor.getCompletedTaskCount());
                sleep(1000);
            }
        }).start();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}