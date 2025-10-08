import java.util.concurrent.*;

public class BatchScheduler {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Simple Batch Scheduler ===\n");

        // Create scheduler - runs tasks on schedule
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Create worker pool - 5 workers to do the tasks
        ExecutorService workers = Executors.newFixedThreadPool(5);

        // Create batch processor
        BatchProcessor processor = new BatchProcessor(workers);

        // Run every 5 seconds (change to 120 for 2 minutes)
        scheduler.scheduleAtFixedRate(processor, 0, 5, TimeUnit.SECONDS);

        // Run for 15 seconds (3 cycles)
        Thread.sleep(16000);

        // Stop everything
        scheduler.shutdown();
        workers.shutdown();
        System.out.println("\nDone!");
    }
}

class BatchProcessor implements Runnable {

    private ExecutorService workers;
    private int cycleNumber = 0;
    private int taskId = 1;

    public BatchProcessor(ExecutorService workers) {
        this.workers = workers;
    }

    @Override
    public void run() {
        cycleNumber++;
        System.out.println("\n--- Cycle " + cycleNumber + " ---");

        // Create 5 tasks
        Future<?> task1 = workers.submit(() -> doWork(taskId++, 1));
        Future<?> task2 = workers.submit(() -> doWork(taskId++, 2));
        Future<?> task3 = workers.submit(() -> doWork(taskId++, 3));
        Future<?> task4 = workers.submit(() -> doWork(taskId++, 4));
        Future<?> task5 = workers.submit(() -> doWork(taskId++, 5));

        // Wait for all 5 to finish
        try {
            task1.get();
            task2.get();
            task3.get();
            task4.get();
            task5.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("All 5 tasks done!\n");
    }

    private void doWork(int taskId, int workerNum) {
        System.out.println("Worker-" + workerNum + " doing Task-" + taskId);

        // Simulate work (1-3 seconds)
        try {
            Thread.sleep(1000 + (int)(Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Worker-" + workerNum + " finished Task-" + taskId);
    }
}
