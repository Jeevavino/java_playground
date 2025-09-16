import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServicePoolsDemo {
    private static AtomicInteger taskCounter = new AtomicInteger(0);
    private static AtomicInteger delayTaskCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("=== ExecutorService Thread Pool Types Demo ===\n");

        // 1. Fixed Thread Pool
        demonstrateFixedThreadPool();
        sleep(2000);

        // 2. Cached Thread Pool
        demonstrateCachedThreadPool();
        sleep(2000);

        // 3. Single Thread Executor
        demonstrateSingleThreadExecutor();
        sleep(2000);

        // 4. Scheduled Thread Pool
        demonstrateScheduledThreadPool();
        sleep(8000);
    }

    // 1. FIXED THREAD POOL
    private static void demonstrateFixedThreadPool() {
        System.out.println("1. FIXED THREAD POOL (3 threads)");
        System.out.println("- Fixed number of threads (3)");
        System.out.println("- Reuses threads from pool");
        System.out.println("- Good for CPU-intensive tasks\n");

        ExecutorService fixedPool = Executors.newFixedThreadPool(3);

        // Submit 6 tasks to 3-thread pool
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            fixedPool.submit(() -> {
                System.out.println("FixedPool - Task " + taskId +
                        " running on: " + Thread.currentThread().getName());
                sleep(1000);
            });
        }

        fixedPool.shutdown();
        System.out.println();
    }

    // 2. CACHED THREAD POOL
    private static void demonstrateCachedThreadPool() {
        System.out.println("2. CACHED THREAD POOL");
        System.out.println("- Creates new threads as needed");
        System.out.println("- Reuses idle threads (60s timeout)");
        System.out.println("- Good for short-lived async tasks\n");

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        // Submit 4 tasks quickly
        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            cachedPool.submit(() -> {
                System.out.println("CachedPool - Task " + taskId +
                        " running on: " + Thread.currentThread().getName());
                sleep(500);
            });
        }

        cachedPool.shutdown();
        System.out.println();
    }

    // 3. SINGLE THREAD EXECUTOR
    private static void demonstrateSingleThreadExecutor() {
        System.out.println("3. SINGLE THREAD EXECUTOR");
        System.out.println("- Only one thread executes tasks");
        System.out.println("- Tasks execute sequentially (FIFO)");
        System.out.println("- Good for sequential processing\n");

        ExecutorService singlePool = Executors.newSingleThreadExecutor();

        // Submit 4 tasks - they'll run one by one
        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            singlePool.submit(() -> {
                System.out.println("SingleThread - Task " + taskId +
                        " running on: " + Thread.currentThread().getName());
                sleep(500);
            });
        }

        singlePool.shutdown();
        System.out.println();
    }

    // 4. SCHEDULED THREAD POOL
    private static void demonstrateScheduledThreadPool() {
        System.out.println("4. SCHEDULED THREAD POOL");
        System.out.println("- Schedules tasks for future execution");
        System.out.println("- Supports delays and periodic execution");
        System.out.println("- Good for recurring tasks\n");

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(3);

        // Schedule a one-time task with delay
        scheduledPool.schedule(() -> {
            System.out.println("Scheduled - One-time task after 1 second on: " +
                    Thread.currentThread().getName());
        }, 1, TimeUnit.SECONDS);

        // Schedule a recurring task with FIXED RATE
        System.out.println("Starting scheduleAtFixedRate (every 1 second):");
        ScheduledFuture<?> periodicTask = scheduledPool.scheduleAtFixedRate(() -> {
            int count = taskCounter.incrementAndGet();
            System.out.println("FixedRate - Task #" + count + " started at: " +
                    System.currentTimeMillis() % 10000 + "ms on: " + Thread.currentThread().getName());
            sleep(1500); // Task takes 1.5 seconds, but rate is every 1 second
            System.out.println("FixedRate - Task #" + count + " finished at: " +
                    System.currentTimeMillis() % 10000 + "ms");
        }, 2, 1, TimeUnit.SECONDS);

        // Schedule a recurring task with FIXED DELAY
        System.out.println("Starting scheduleAtFixedDelay (1 second delay after completion):");
        ScheduledFuture<?> delayTask = scheduledPool.scheduleWithFixedDelay(() -> {
            int count = delayTaskCounter.incrementAndGet();
            System.out.println("FixedDelay - Task #" + count + " started at: " +
                    System.currentTimeMillis() % 10000 + "ms on: " + Thread.currentThread().getName());
            sleep(1500); // Task takes 1.5 seconds, then waits 1 second before next
            System.out.println("FixedDelay - Task #" + count + " finished at: " +
                    System.currentTimeMillis() % 10000 + "ms");
        }, 2, 1, TimeUnit.SECONDS);

        // Cancel both tasks after some time
        scheduledPool.schedule(() -> {
            System.out.println("\nStopping all periodic tasks...");
            periodicTask.cancel(false);
            delayTask.cancel(false);
            scheduledPool.shutdown();
        }, 8, TimeUnit.SECONDS);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
