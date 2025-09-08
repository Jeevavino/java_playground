public class SimpleThreadCollaboration {
    
    // Shared data that threads will work on together
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private int sum = 0;
    private int product = 1;
    
    // Thread 1: Calculate sum of first half
    class SumCalculator extends Thread {
        @Override
        public void run() {
            System.out.println("SumCalculator started...");
            for (int i = 0; i < 5; i++) {
                sum += numbers[i];
                System.out.println("Added " + numbers[i] + ", current sum: " + sum);
                try {
                    Thread.sleep(500); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("SumCalculator finished. Final sum: " + sum);
        }
    }
    
    // Thread 2: Calculate product of second half
    class ProductCalculator extends Thread {
        @Override
        public void run() {
            System.out.println("ProductCalculator started...");
            for (int i = 5; i < 10; i++) {
                product *= numbers[i];
                System.out.println("Multiplied " + numbers[i] + ", current product: " + product);
                try {
                    Thread.sleep(700); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("ProductCalculator finished. Final product: " + product);
        }
    }
    
    // Thread 3: Print array elements
    class ArrayPrinter extends Thread {
        @Override
        public void run() {
            System.out.println("ArrayPrinter started...");
            System.out.print("Array elements: ");
            for (int num : numbers) {
                System.out.print(num + " ");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("\nArrayPrinter finished.");
        }
    }
    
    public void runDemo() throws InterruptedException {
        System.out.println("=== Simple Thread Collaboration Demo ===");
        System.out.println("Goal: Calculate sum, product, and print array using 3 threads\n");
        
        // Create threads
        SumCalculator sumThread = new SumCalculator();
        ProductCalculator productThread = new ProductCalculator();
        ArrayPrinter printerThread = new ArrayPrinter();
        
        // Start all threads
        System.out.println("Starting all threads...\n");
        sumThread.start();
        productThread.start();
        printerThread.start();
        
        // Wait for all threads to complete using join()
        System.out.println("\nMain thread waiting for all workers to finish...");
        sumThread.join();        // Wait for sum calculation
        productThread.join();    // Wait for product calculation  
        printerThread.join();    // Wait for printing
        
        // Now all threads are done, main thread can continue
        System.out.println("\n=== All threads completed! ===");
        System.out.println("Final Results:");
        System.out.println("Sum of first half (1-5): " + sum);
        System.out.println("Product of second half (6-10): " + product);
        System.out.println("Combined result: " + (sum + product));
        System.out.println("Main thread finished!");
    }
    
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadCollaboration demo = new SimpleThreadCollaboration();
        demo.runDemo();
    }
}
