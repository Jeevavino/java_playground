import java.util.concurrent.*;

public class CompletableFutureEssentialDemo {

    public static void main(String[] args) {
        System.out.println("=== CompletableFuture Essential Methods ===\n");

        // 1. Basic Creation
        demonstrateCreation();

        // 2. Transformation
        demonstrateTransformation();

        // 3. Combining
        demonstrateCombining();

        // 4. Error Handling
        demonstrateErrorHandling();

        System.out.println("\n=== Demo Completed ===");
    }

    // ============================================
    // 1. Creation Methods (Most Used)
    // ============================================
    private static void demonstrateCreation() {
        System.out.println("--- 1. Creation Methods ---");

        // supplyAsync - Most common way to create async task
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Hello World";
        });

        System.out.println("✅ Result: " + future.join());
        System.out.println();
    }

    // ============================================
    // 2. Transformation Methods (Most Used)
    // ============================================
    private static void demonstrateTransformation() {
        System.out.println("--- 2. Transformation Methods ---");

        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("⚙️  Fetching data...");
                    sleep(1000);
                    return "john";
                })
                // thenApply - Transform the result
                .thenApply(name -> {
                    System.out.println("🔄 Processing: " + name);
                    return name.toUpperCase();
                });

        // thenAccept - Consume the result (no return value)
        CompletableFuture<Void> consumed = future.thenAccept(upperName -> {
            System.out.println("🔄 Consuming result: " + upperName);
            System.out.println("✅ Final processed name: User_" + upperName);
        });

        // Wait for consumption to complete
        consumed.join();
        System.out.println();
    }

    // ============================================
    // 3. Combining Futures (Most Used)
    // ============================================
    private static void demonstrateCombining() {
        System.out.println("--- 3. Combining Futures ---");

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("⚙️  Task 1 running...");
            sleep(1000);
            return "Data1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("⚙️  Task 2 running...");
            sleep(800);
            return "Data2";
        });

        // thenCombine - Combine two futures
        CompletableFuture<String> combined = future1.thenCombine(future2, (data1, data2) -> {
            return data1 + " + " + data2;
        });

        System.out.println("✅ Combined: " + combined.join());
        System.out.println();
    }

    // ============================================
    // 4. Error Handling (Most Used)
    // ============================================
    private static void demonstrateErrorHandling() {
        System.out.println("--- 4. Error Handling ---");

        CompletableFuture<String> withError = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("⚙️  Risky operation...");
                    if (Math.random() > 0.5) {
                        throw new RuntimeException("Something went wrong!");
                    }
                    return "Success";
                })
                // exceptionally - Handle errors
                .exceptionally(throwable -> {
                    System.out.println("❌ Error: " + throwable.getMessage());
                    return "Fallback value";
                });

        System.out.println("✅ Result: " + withError.join());
        System.out.println();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
