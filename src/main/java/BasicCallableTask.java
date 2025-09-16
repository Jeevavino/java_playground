import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BasicCallableTask implements Callable<String> {
    private final String taskName;
    private final int workTime;

    public BasicCallableTask(String taskName, int workTime) {
        this.taskName = taskName;
        this.workTime = workTime;
    }

    @Override
    public String call() throws Exception {
        System.out.println("🚀 Starting task: " + taskName);
        System.out.println("⏱️  Task " + taskName + " will work for " + workTime + " seconds");

        // Simulate some work
        for (int i = 1; i <= workTime; i++) {
            Thread.sleep(1000); // Sleep for 1 second
            System.out.println("⚙️  Task " + taskName + " - Working... " + i + "/" + workTime);
        }

        String result = "✅ Task " + taskName + " completed successfully!";
        System.out.println(result);

        return result;
    }
}

class CallableDemo {
    public static void main(String[] args) {
        System.out.println("=== Callable Demo Started ===\n");

        // Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Create callable tasks
            Callable<String> task1 = new BasicCallableTask("TASK-1", 3);
            Callable<String> task2 = new BasicCallableTask("TASK-2", 2);

            System.out.println("📋 Submitting tasks to executor...\n");

            // Submit tasks and get Future objects
            Future<String> future1 = executor.submit(task1);
            Future<String> future2 = executor.submit(task2);

            System.out.println("⏳ Waiting for results...\n");

            // Get results (this will block until tasks complete)
            String result1 = future1.get();
            String result2 = future2.get();

            System.out.println("\n=== Results ===");
            System.out.println("Result 1: " + result1);
            System.out.println("Result 2: " + result2);

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        } finally {
            System.out.println("\n🔚 Shutting down executor...");
            executor.shutdown();
        }

        System.out.println("=== Demo Completed ===");
    }
}


