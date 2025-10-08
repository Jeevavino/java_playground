 import java.util.concurrent.*;


// Keep your existing BasicCallableTask class

 public class FutureVsCompletableFutureDemo {

     public static void main(String[] args) {
         System.out.println("=== Understanding Future vs CompletableFuture ===\n");

         // ============================================
         // PART 1: Traditional Future (BLOCKING)
         // ============================================
         System.out.println("--- Part 1: Traditional Future ---\n");

         ExecutorService executor = Executors.newFixedThreadPool(2);

         try {
             System.out.println("📌 Submitting task...");

             Future<String> future = executor.submit(() -> {
                 System.out.println("⚙️  Working on task...");
                 Thread.sleep(2000); // 2 seconds work
                 return "Task completed!";
             });

             System.out.println("⏸️  Main thread is BLOCKED - waiting for result...");

             // This BLOCKS the main thread until task finishes!
             String result = future.get();

             System.out.println("✅ Got result: " + result);
             System.out.println("❌ Problem: Main thread couldn't do anything else!\n");

         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             executor.shutdown();
         }

         // ============================================
         // PART 2: CompletableFuture (NON-BLOCKING)
         // ============================================
         System.out.println("--- Part 2: CompletableFuture ---\n");

         try {
             System.out.println("📌 Starting async task...");

             CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                 System.out.println("⚙️  Working on task...");
                 try {
                     Thread.sleep(2000); // 2 seconds work
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 return "Task completed!";
             });

             // Attach a callback - runs automatically when task finishes
             completableFuture.thenAccept(result -> {
                 System.out.println("✅ Got result in callback: " + result);
             });

             System.out.println("⚡ Main thread is FREE! Can do other work...");
             System.out.println("💼 Doing other work...");
             System.out.println("💼 Still doing other work...");

             // Only wait at the end if needed
             Thread.sleep(3000); // Give time for task to complete

             System.out.println("✅ Advantage: Main thread stayed active!\n");

         } catch (Exception e) {
             e.printStackTrace();
         }


         System.out.println("\n=== Summary ===");
         System.out.println("Future:            Blocks and waits ⏸️");
         System.out.println("CompletableFuture: Non-blocking with callbacks ⚡");
         System.out.println("                   Can chain tasks 🔗");
         System.out.println("\n=== Demo Completed ===");
     }
 }


