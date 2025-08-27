import java.io.*;
import java.util.Scanner;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.concurrent.TimeoutException;

// Custom Exception Classes
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Multi-catch Block Demonstration Class
class MultiCatchDemo {
    
    // JDK 7+ Multi-catch feature demonstration
    public void demonstrateBasicMultiCatch() {
        System.out.println("=== BASIC MULTI-CATCH DEMO (JDK 7+ Feature) ===");
        
        String[] testInputs = {"abc", "0", "5", null};
        
        for (String input : testInputs) {
            try {
                System.out.println("\n🧪 Testing input: " + (input == null ? "null" : "\"" + input + "\""));
                
                // This can throw NumberFormatException
                int number = Integer.parseInt(input);
                
                // This can throw ArithmeticException
                int result = 100 / number;
                
                System.out.println("✅ Result: 100 / " + number + " = " + result);
                
            } catch (NumberFormatException | ArithmeticException e) {
                // Multi-catch block handles both exceptions
                System.out.println("❌ Multi-catch handled: " + e.getClass().getSimpleName() + " -> " + e.getMessage());
            } catch (NullPointerException e) {
                // Separate catch for null pointer
                System.out.println("❌ Null input detected: " + e.getClass().getSimpleName());
            }
        }
        System.out.println();
    }
    
    // Advanced multi-catch with more exception types
    public void demonstrateAdvancedMultiCatch() {
        System.out.println("=== ADVANCED MULTI-CATCH DEMO (JDK 21) ===");
        
        try {
            performComplexOperation("invalid_file.txt", "bad_sql_query", "invalid_date");
            
        } catch (IOException | SQLException | DateTimeException | IllegalArgumentException e) {
            // Multi-catch for related exceptions
            System.out.println("🚨 Operation failed with multi-catch: " + e.getClass().getSimpleName());
            System.out.println("📝 Error details: " + e.getMessage());
            System.out.println("💡 All these exceptions are handled uniformly");
            
        } catch (TimeoutException e) {
            // Separate handling for timeout
            System.out.println("⏰ Timeout occurred: " + e.getMessage());
            
        } catch (Exception e) {
            // Catch-all for any other exceptions
            System.out.println("❓ Unexpected error: " + e.getClass().getSimpleName());
        }
        System.out.println();
    }
    
    private void performComplexOperation(String filename, String sqlQuery, String dateStr) 
            throws IOException, SQLException, DateTimeException, TimeoutException {
        
        // Simulate different types of exceptions
        if (filename.contains("invalid")) {
            throw new IOException("File not accessible: " + filename);
        }
        if (sqlQuery.contains("bad")) {
            throw new SQLException("Invalid SQL syntax: " + sqlQuery);
        }
        if (dateStr.contains("invalid")) {
            throw new DateTimeException("Cannot parse date: " + dateStr);
        }
        
        System.out.println("✅ Complex operation completed successfully");
    }
    
    // Multi-catch vs Multiple catch blocks comparison
    public void compareMultiCatchVsMultipleCatch() {
        System.out.println("=== MULTI-CATCH vs MULTIPLE CATCH COMPARISON ===");
        
        System.out.println("🔄 Testing with MULTI-CATCH approach:");
        testArrayOperations1();
        
        System.out.println("\n🔄 Testing with MULTIPLE CATCH approach:");
        testArrayOperations2();
        System.out.println();
    }
    
    private void testArrayOperations1() {
        try {
            int[] numbers = {1, 2, 3, 4, 5};
            String indexStr = "abc"; // Will cause NumberFormatException
            
            int index = Integer.parseInt(indexStr);
            int result = numbers[index] / 0; // Could cause ArithmeticException or ArrayIndexOutOfBoundsException
            
        } catch (NumberFormatException | ArithmeticException | ArrayIndexOutOfBoundsException e) {
            // Single multi-catch block (JDK 7+ feature)
            System.out.println("✨ Multi-catch handled: " + e.getClass().getSimpleName());
        }
    }
    
    private void testArrayOperations2() {
        try {
            int[] numbers = {1, 2, 3, 4, 5};
            String indexStr = "abc"; // Will cause NumberFormatException
            
            int index = Integer.parseInt(indexStr);
            int result = numbers[index] / 0; // Could cause ArithmeticException or ArrayIndexOutOfBoundsException
            
        } catch (NumberFormatException e) {
            System.out.println("🔢 NumberFormatException: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("➗ ArithmeticException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("📋 ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }
}

// JDK 21 Enhanced Multi-catch Features
class JDK21MultiCatchFeatures {
    
    public void demonstrateJDK21Enhancements() {
        System.out.println("=== JDK 21 ENHANCED MULTI-CATCH FEATURES ===");
        
        // Pattern matching with instanceof (JDK 21 enhancement)
        demonstratePatternMatchingWithExceptions();
        
        // Switch expressions with exception handling (JDK 21)
        demonstrateSwitchWithExceptions();
        
        // Virtual threads with multi-catch (JDK 21 Project Loom)
        demonstrateVirtualThreadsWithMultiCatch();
    }
    
    private void demonstratePatternMatchingWithExceptions() {
        System.out.println("\n🔍 Pattern Matching with Exception Handling:");
        
        try {
            processDataWithPatternMatching("invalid_data");
            
        } catch (IOException | IllegalArgumentException | NumberFormatException e) {
            // Enhanced pattern matching in catch (JDK 21 style)
            switch (e) {
                case IOException io -> {
                    System.out.println("📁 I/O Error: " + io.getMessage());
                    // Handle I/O specific recovery
                }
                case IllegalArgumentException iae -> {
                    System.out.println("🚫 Invalid Argument: " + iae.getMessage());
                    // Handle argument validation
                }
                case NumberFormatException nfe -> {
                    System.out.println("🔢 Number Format Error: " + nfe.getMessage());
                    // Handle number parsing issues
                }
                default -> System.out.println("❓ Unexpected exception type");
            }
        }
    }
    
    private void processDataWithPatternMatching(String data) throws IOException, IllegalArgumentException {
        if (data.contains("invalid")) {
            throw new IllegalArgumentException("Data validation failed for: " + data);
        }
        if (data.contains("io_error")) {
            throw new IOException("I/O operation failed");
        }
    }
    
    private void demonstrateSwitchWithExceptions() {
        System.out.println("\n🔀 Switch Expression with Exception Handling:");
        
        String[] operations = {"divide", "parse", "access", "unknown"};
        
        for (String operation : operations) {
            try {
                String result = performOperation(operation);
                System.out.println("✅ " + operation + " -> " + result);
                
            } catch (ArithmeticException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // Multi-catch with switch expression for error reporting
                String errorType = switch (e) {
                    case ArithmeticException ae -> "Mathematical Error";
                    case NumberFormatException nfe -> "Parsing Error";
                    case ArrayIndexOutOfBoundsException aie -> "Index Error";
                    default -> "Unknown Error";
                };
                
                System.out.println("❌ " + operation + " failed: " + errorType + " - " + e.getMessage());
            }
        }
    }
    
    private String performOperation(String operation) {
        return switch (operation) {
            case "divide" -> {
                int result = 10 / 0; // ArithmeticException
                yield "Division result: " + result;
            }
            case "parse" -> {
                int number = Integer.parseInt("abc"); // NumberFormatException
                yield "Parsed number: " + number;
            }
            case "access" -> {
                int[] array = {1, 2, 3};
                int value = array[10]; // ArrayIndexOutOfBoundsException
                yield "Array value: " + value;
            }
            default -> "Operation completed successfully";
        };
    }
    
    private void demonstrateVirtualThreadsWithMultiCatch() {
        System.out.println("\n🧵 Virtual Threads with Multi-catch (JDK 21):");
        
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            
            // Submit tasks that might throw exceptions
            var future1 = executor.submit(() -> {
                throw new RuntimeException("Task 1 failed");
            });
            
            var future2 = executor.submit(() -> {
                throw new IllegalStateException("Task 2 failed");
            });
            
            try {
                future1.get();
                future2.get();
                
            } catch (java.util.concurrent.ExecutionException e) {
                // Multi-catch for different wrapped exceptions
                Throwable cause = e.getCause();
                
                switch (cause) {
                    case RuntimeException re -> 
                        System.out.println("🔧 Runtime exception in virtual thread: " + re.getMessage());
                    case IllegalStateException ise -> 
                        System.out.println("⚠️ State exception in virtual thread: " + ise.getMessage());
                    default -> 
                        System.out.println("❓ Other exception in virtual thread: " + cause.getMessage());
                }
            }
            
        } catch (InterruptedException | IOException e) {
            System.out.println("🚨 Thread execution error: " + e.getClass().getSimpleName());
        }
    }
}

// Multi-catch Best Practices and Edge Cases
class MultiCatchBestPractices {
    
    public void demonstrateBestPractices() {
        System.out.println("=== MULTI-CATCH BEST PRACTICES ===");
        
        // Best Practice 1: Group related exceptions
        demonstrateRelatedExceptionGrouping();
        
        // Best Practice 2: Order from specific to general
        demonstrateExceptionOrdering();
        
        // Best Practice 3: Variable is implicitly final
        demonstrateImplicitFinalVariable();
        
        // Common Pitfall: Don't overuse multi-catch
        demonstrateWhenNotToUseMultiCatch();
    }
    
    private void demonstrateRelatedExceptionGrouping() {
        System.out.println("\n✅ GOOD: Group related exceptions together");
        
        try {
            performNetworkOperation();
            
        } catch (IOException | TimeoutException e) {
            // Good: Both are network-related exceptions
            System.out.println("🌐 Network operation failed: " + e.getClass().getSimpleName());
            // Apply same recovery strategy for both
        } catch (SecurityException e) {
            // Different handling for security issues
            System.out.println("🔒 Security error: " + e.getMessage());
        }
    }
    
    private void demonstrateExceptionOrdering() {
        System.out.println("\n✅ GOOD: Proper exception ordering (specific to general)");
        
        try {
            performDataProcessing();
            
        } catch (FileNotFoundException | SQLException e) {
            // Specific exceptions first
            System.out.println("📁 Data access error: " + e.getClass().getSimpleName());
            
        } catch (IOException | RuntimeException e) {
            // More general exceptions after specific ones
            System.out.println("⚠️ General processing error: " + e.getClass().getSimpleName());
            
        } catch (Exception e) {
            // Most general exception last
            System.out.println("❓ Unexpected error: " + e.getClass().getSimpleName());
        }
    }
    
    private void demonstrateImplicitFinalVariable() {
        System.out.println("\n🔒 Multi-catch variable is implicitly final");
        
        try {
            throw new IllegalArgumentException("Test exception");
            
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("📝 Exception type: " + e.getClass().getSimpleName());
            // e = new IllegalArgumentException(); // ❌ This would cause compile error!
            // The variable 'e' is implicitly final in multi-catch
            
            System.out.println("💡 Cannot reassign 'e' - it's implicitly final");
        }
    }
    
    private void demonstrateWhenNotToUseMultiCatch() {
        System.out.println("\n❌ When NOT to use multi-catch:");
        
        try {
            performMixedOperations();
            
        } catch (IOException e) {
            // Different handling for I/O errors
            System.out.println("📁 I/O Error - attempting file recovery");
            // Specific I/O recovery logic here
            
        } catch (SQLException e) {
            // Different handling for database errors
            System.out.println("🗃️ Database Error - rolling back transaction");
            // Database-specific recovery logic here
            
        } catch (SecurityException e) {
            // Different handling for security errors
            System.out.println("🔒 Security Error - logging security breach");
            // Security-specific handling here
        }
        
        // ❌ DON'T combine these in multi-catch if they need different handling:
        // catch (IOException | SQLException | SecurityException e) { ... }
    }
    
    private void performNetworkOperation() throws IOException, TimeoutException {
        throw new IOException("Network connection failed");
    }
    
    private void performDataProcessing() throws FileNotFoundException, SQLException {
        throw new FileNotFoundException("Data file not found");
    }
    
    private void performMixedOperations() throws IOException, SQLException, SecurityException {
        throw new SQLException("Database connection failed");
    }
}

// Main Application Class
class CompleteMultiCatchExceptionHandling {
    
    public void runAllDemos() {
        System.out.println("🎓 COMPREHENSIVE JAVA MULTI-CATCH EXCEPTION HANDLING");
        System.out.println("Compatible with JDK 21 Features");
        System.out.println("=" .repeat(65));
        
        // Multi-catch demonstrations
        MultiCatchDemo multiCatchDemo = new MultiCatchDemo();
        multiCatchDemo.demonstrateBasicMultiCatch();
        multiCatchDemo.demonstrateAdvancedMultiCatch();
        multiCatchDemo.compareMultiCatchVsMultipleCatch();
        
        // JDK 21 specific features
        JDK21MultiCatchFeatures jdk21Features = new JDK21MultiCatchFeatures();
        jdk21Features.demonstrateJDK21Enhancements();
        
        // Best practices
        MultiCatchBestPractices bestPractices = new MultiCatchBestPractices();
        bestPractices.demonstrateBestPractices();
        
        printMultiCatchSummary();
    }
    
    private void printMultiCatchSummary() {
        System.out.println("\n🎯 MULTI-CATCH SUMMARY FOR JDK 21:");
        System.out.println("═" .repeat(50));
        
        System.out.println("\n📚 MULTI-CATCH SYNTAX:");
        System.out.println("• catch (ExceptionA | ExceptionB | ExceptionC e) { ... }");
        System.out.println("• Use pipe (|) to separate exception types");
        System.out.println("• Exception variable is implicitly final");
        
        System.out.println("\n✅ ADVANTAGES:");
        System.out.println("• Reduces code duplication");
        System.out.println("• Cleaner, more readable code");
        System.out.println("• Smaller bytecode generation");
        System.out.println("• Better maintenance");
        
        System.out.println("\n🔧 JDK 21 ENHANCEMENTS:");
        System.out.println("• Pattern matching with switch expressions");
        System.out.println("• Virtual threads support");
        System.out.println("• Enhanced instanceof patterns");
        System.out.println("• Improved switch expressions");
        
        System.out.println("\n📋 BEST PRACTICES:");
        System.out.println("• Group logically related exceptions");
        System.out.println("• Order from specific to general");
        System.out.println("• Don't overuse - separate when handling differs");
        System.out.println("• Remember: catch variable is implicitly final");
        
        System.out.println("\n🚨 RESTRICTIONS:");
        System.out.println("• Cannot catch both parent and child exceptions together");
        System.out.println("• Exception variable cannot be reassigned");
        System.out.println("• All exceptions must be disjoint (no inheritance relationship)");
        
        System.out.println("\n💡 WHEN TO USE:");
        System.out.println("• ✅ Same handling logic for different exceptions");
        System.out.println("• ✅ Related exceptions (I/O, Network, etc.)");
        System.out.println("• ❌ Different recovery strategies needed");
        System.out.println("• ❌ Exception-specific logging required");
    }
}

// Main Class
public class CompleteMultiCatchExceptionDemo {
    public static void main(String[] args) {
        CompleteMultiCatchExceptionHandling app = new CompleteMultiCatchExceptionHandling();
        app.runAllDemos();
        
        System.out.println("\n🎉 Multi-catch exception handling demo completed!");
        System.out.println("💪 Ready to use multi-catch effectively in JDK 21!");
    }
}
