import java.util.concurrent.*;

public class SynchronizationUtilitiesDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Java Synchronization Utilities Demo ===\n");

        // 1. Semaphore Demo
        demonstrateSemaphore();

        Thread.sleep(2000);
        System.out.println("\n" + "=".repeat(50) + "\n");

        // 2. CountDownLatch Demo
        demonstrateCountDownLatch();

        Thread.sleep(2000);
        System.out.println("\n" + "=".repeat(50) + "\n");

        // 3. CyclicBarrier Demo
        demonstrateCyclicBarrier();
    }

    // ============================================
    // 1. SEMAPHORE - Limiting Concurrent Access
    // ============================================
    private static void demonstrateSemaphore() throws InterruptedException {
        System.out.println("--- SEMAPHORE Demo ---");
        System.out.println("Scenario: Parking lot with only 3 spaces\n");

        // Create semaphore with 3 permits (3 parking spaces)
        Semaphore parkingLot = new Semaphore(3);

        // Create ExecutorService with thread pool
        ExecutorService executor = Executors.newFixedThreadPool(6);

        // 6 cars trying to park
        for (int i = 1; i <= 6; i++) {
            final int carNumber = i;

            executor.submit(() -> {
                try {
                    System.out.println("🚗 Car " + carNumber + " arriving...");

                    parkingLot.acquire(); // Get a parking space
                    System.out.println("✅ Car " + carNumber + " PARKED (spaces left: " +
                            parkingLot.availablePermits() + ")");

                    Thread.sleep(2000); // Park for 2 seconds

                    System.out.println("🚗 Car " + carNumber + " leaving...");
                    parkingLot.release(); // Free up the space
                    System.out.println("✅ Car " + carNumber + " LEFT (spaces now: " +
                            parkingLot.availablePermits() + ")");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread.sleep(300); // Small delay between car arrivals
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // ============================================
    // 2. COUNTDOWNLATCH - Wait for Multiple Tasks
    // ============================================
    private static void demonstrateCountDownLatch() throws InterruptedException {
        System.out.println("--- COUNTDOWNLATCH Demo ---");
        System.out.println("Scenario: Race - wait for all 4 runners to finish\n");

        // Create latch with count of 4 (4 runners)
        CountDownLatch raceLatch = new CountDownLatch(4);

        // Create ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Main thread - Race organizer
        executor.submit(() -> {
            try {
                System.out.println("🏁 Race started! Waiting for all runners...\n");

                raceLatch.await(); // Wait until count reaches 0

                System.out.println("\n🎉 ALL RUNNERS FINISHED! Race complete!");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread.sleep(500);

        // 4 runners
        for (int i = 1; i <= 4; i++) {
            final int runnerNumber = i;

            executor.submit(() -> {
                try {
                    System.out.println("🏃 Runner " + runnerNumber + " running...");
                    Thread.sleep((long) (Math.random() * 3000 + 1000)); // Run for 1-4 seconds

                    System.out.println("✅ Runner " + runnerNumber + " FINISHED! " +
                            "(Remaining: " + (raceLatch.getCount() - 1) + ")");
                    raceLatch.countDown(); // Decrease count by 1

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread.sleep(200);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // ============================================
    // 3. CYCLICBARRIER - Synchronize at Barrier Point
    // ============================================
    private static void demonstrateCyclicBarrier() throws InterruptedException {
        System.out.println("--- CYCLICBARRIER Demo ---");
        System.out.println("Scenario: Multiplayer game - wait for all 3 players\n");

        // Create barrier for 3 threads with action when all arrive
        CyclicBarrier gameBarrier = new CyclicBarrier(3, () -> {
            System.out.println("\n🎮 ALL PLAYERS READY! Game starting...\n");
        });

        // Create ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 3 players
        for (int i = 1; i <= 3; i++) {
            final int playerNumber = i;

            executor.submit(() -> {
                try {
                    // Round 1: Loading
                    System.out.println("👤 Player " + playerNumber + " loading game...");
                    Thread.sleep((long) (Math.random() * 2000 + 1000));
                    System.out.println("✅ Player " + playerNumber + " LOADED, waiting for others...");

                    gameBarrier.await(); // Wait at barrier

                    // Round 1: Playing
                    System.out.println("🎮 Player " + playerNumber + " playing Round 1");
                    Thread.sleep(1500);
                    System.out.println("✅ Player " + playerNumber + " finished Round 1, waiting...");

                    gameBarrier.await(); // Reusable barrier!

                    // Round 2: Playing next round
                    System.out.println("🎮 Player " + playerNumber + " playing Round 2");
                    Thread.sleep(1000);
                    System.out.println("🏆 Player " + playerNumber + " completed!");

                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread.sleep(300);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
