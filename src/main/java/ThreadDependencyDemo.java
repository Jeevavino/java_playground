public class ThreadDependencyDemo {
    
    // Shared variables to pass data between threads
    private String rawData = "";
    private String processedData = "";
    private String finalResult = "";
    
    // Thread 1: Data Collector - Gets raw data
    class DataCollector extends Thread {
        @Override
        public void run() {
            System.out.println("Thread 1 (DataCollector): Starting data collection...");
            
            try {
                // Simulate collecting data from external source
                Thread.sleep(1000);
                rawData = "UserData,OrderData,ProductData";
                System.out.println("Thread 1: Collected raw data: " + rawData);
                
                Thread.sleep(500);
                System.out.println("Thread 1: Data collection completed!");
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // Thread 2: Data Processor - Processes data from Thread 1
    class DataProcessor extends Thread {
        @Override
        public void run() {
            System.out.println("Thread 2 (DataProcessor): Waiting for raw data...");
            
            // This thread depends on Thread 1's output
            if (rawData.isEmpty()) {
                System.out.println("Thread 2: No raw data available yet!");
                return;
            }
            
            System.out.println("Thread 2: Processing raw data: " + rawData);
            
            try {
                // Simulate data processing
                Thread.sleep(800);
                processedData = rawData.replace(",", " | ").toUpperCase();
                System.out.println("Thread 2: Processed data: " + processedData);
                
                Thread.sleep(300);
                System.out.println("Thread 2: Data processing completed!");
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // Thread 3: Report Generator - Creates final report from Thread 2's output
    class ReportGenerator extends Thread {
        @Override
        public void run() {
            System.out.println("Thread 3 (ReportGenerator): Waiting for processed data...");
            
            // This thread depends on Thread 2's output
            if (processedData.isEmpty()) {
                System.out.println("Thread 3: No processed data available yet!");
                return;
            }
            
            System.out.println("Thread 3: Generating report from: " + processedData);
            
            try {
                // Simulate report generation
                Thread.sleep(600);
                finalResult = "REPORT: [" + processedData + "] - Generated on " + 
                             java.time.LocalTime.now();
                System.out.println("Thread 3: Final report created!");
                System.out.println("Thread 3: " + finalResult);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void runSequentialDemo() throws InterruptedException {
        System.out.println("=== Sequential Thread Dependency Demo ===");
        System.out.println("Flow: Collect Data → Process Data → Generate Report\n");
        
        // Create threads
        DataCollector collector = new DataCollector();
        DataProcessor processor = new DataProcessor();
        ReportGenerator generator = new ReportGenerator();
        
        // Execute threads sequentially - each depends on previous
        System.out.println("Step 1: Starting data collection...");
        collector.start();
        collector.join(); // Wait for Thread 1 to complete
        
        System.out.println("\nStep 2: Starting data processing...");
        processor.start();
        processor.join(); // Wait for Thread 2 to complete
        
        System.out.println("\nStep 3: Starting report generation...");
        generator.start();
        generator.join(); // Wait for Thread 3 to complete
        
        System.out.println("\n=== All steps completed! ===");
        System.out.println("Final Output: " + finalResult);
    }
    
    public void runParallelDemo() throws InterruptedException {
        System.out.println("\n=== What happens without proper dependency? ===");
        System.out.println("Running all threads at the same time (wrong approach):\n");
        
        // Reset data
        rawData = "";
        processedData = "";
        finalResult = "";
        
        // Create new threads
        DataCollector collector2 = new DataCollector();
        DataProcessor processor2 = new DataProcessor();
        ReportGenerator generator2 = new ReportGenerator();
        
        // Start all threads at once (wrong approach)
        collector2.start();
        processor2.start();
        generator2.start();
        
        // Wait for all to complete
        collector2.join();
        processor2.join();
        generator2.join();
        
        System.out.println("\nResult: Threads 2 and 3 couldn't work properly!");
        System.out.println("Final result: " + (finalResult.isEmpty() ? "FAILED" : finalResult));
    }
    
    public static void main(String[] args) throws InterruptedException {
        ThreadDependencyDemo demo = new ThreadDependencyDemo();
        
        // Show correct approach with dependencies
        demo.runSequentialDemo();
        
        Thread.sleep(2000);
        
        // Show what happens without proper dependency management
        demo.runParallelDemo();
    }
}
