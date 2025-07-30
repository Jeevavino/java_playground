/**
 * Pen.java
 * This class represents a Pen object with attributes and behaviors.
 * Demonstrates OOP concepts like encapsulation, abstraction, and object state.
 *
 * Key Learning Points:
 * - Object lives on the Heap (dynamic memory allocation)
 * - Methods live on the Stack (method calls and local variables)
 * - Final class cannot be inherited (inheritance restriction)
 * - Static members belong to class, not instances
 */

// Final class - not allowed to be inherited (compile-time restriction)
public final class Pen {

    // ========== INSTANCE VARIABLES (Object State - stored on HEAP) ==========
    private int inkLevel;        // Instance scope - accessible throughout this object's lifetime
    // Static typing - type declared at compile time
    private String inkColor;     // Instance scope - encapsulated (private access modifier)
    private String tipType;      // Instance scope - each object has its own copy
    private final String brand = "Parker"; // Instance scope + final - immutable after initialization
    // Cannot be modified after object creation
    private boolean isCapped;    // Instance scope - tracks pen cap state

    // ========== CLASS VARIABLE (Shared among ALL objects - stored in METHOD AREA) ==========
    public static int penCount;  // Class scope - shared among ALL Pen objects
    // Static - belongs to class, not individual instances
    // Initialized once when class is first loaded

    // ========== CONSTRUCTORS (Special methods for object initialization) ==========

    // Default Constructor - implicit if no other constructor exists
    // JVM provides this automatically if no constructors are defined
    public Pen() {
        // Method scope - this constructor body has its own scope
        // No parameters - creates object with default values
    }

    // Parameterized Constructor - user defined for custom initialization
    // Constructor scope - parameters are method-scoped
    public Pen(int inkLevel, String inkColor, String tipType) {
        // Parameter scope - inkLevel, inkColor, tipType exist only within this constructor
        this.inkLevel = inkLevel;     // 'this' keyword resolves naming conflict
        this.inkColor = inkColor;     // 'this' refers to current object instance
        this.tipType = tipType;       // Distinguishes between parameter and instance variable
        this.isCapped = true;         // Instance variable initialization
        penCount++;                   // Static variable - incremented for each object creation

        // Local variable scope within constructor method
        System.out.println("   [A new '" + this.brand + "' Pen object has been created on the HEAP.]");
    }

    // ========== STATIC METHOD (Class-level method - belongs to class, not instance) ==========

    // Static method scope - can only access static variables directly
    // Cannot use 'this' keyword as it doesn't belong to any specific instance
    public static int getPenCount() {
        return penCount; // Can only access static members without object reference
    }

    // ========== INSTANCE METHODS (Object behaviors - require object instance to call) ==========

    // Method scope - message parameter exists only within this method
    public void write(String message) {
        // Parameter scope - 'message' is local to this method (method parameter)
        // Method call stack - this method creates new stack frame

        this.capOff(); // Method call - creates new stack frame for capOff()

        // Another conditional scope or Block Scope - code inside if block has its own scope
        if (this.isCapped) { // 'this' keyword for readability (accessing instance variable)
            // Block scope - 'warning' variable exists only within this if block
            var warning = "Pen is Capped"; // Dynamic typing - compiler infers String type
            // Local variable - must be initialized before use
            System.out.println("Can't write. The cap is on!" + warning);
            return; // Early return - exits method immediately
        }

        // Another conditional scope or Block Scope
        if (this.inkLevel <= 0) {
            System.out.println("Out of ink! Cannot write: '" + message + "'");
            return; // Method terminates here if no ink
        }

        // Method execution continues here if pen is uncapped and has ink
        System.out.println("Writing: '" + message + "' with the " + inkColor + " pen.");
        inkLevel -= message.length(); // Modifying instance variable (state change)
        // String.length() - method call on String object

        this.capOn(); // Method call - automatic capping after writing
        System.out.println("Finished writing. Pen is now capped.");
    }

    // Final method - cannot be overridden in subclasses (if class wasn't final)
    // Method scope - accessible throughout class but behavior cannot be changed
    public final void checkInkLevel() {
        // Method scope - accessing instance variable
        System.out.println("Ink level is now: " + inkLevel + "%");
    }

    // Setter method with validation - demonstrates encapsulation
    // Method scope - parameter 'inkLevel' shadows instance variable
    public void setInkLevel(int inkLevel) {
        // Parameter scope - inkLevel parameter exists only in this method
        // Conditional scope for validation
        if (inkLevel >= 0 && inkLevel <= 100) {
            this.inkLevel = inkLevel; // 'this' required to distinguish parameter from instance variable
        } else {
            System.out.println("Invalid ink level! Must be between 0-100.");
        }
    }

    // Method scope - parameter exists only within this method
    public void changeColor(String newColor) {
        // Accessing instance variables - 'this' used for clarity
        System.out.println("Changing color from " + this.inkColor + " to " + newColor + ".");
        this.inkColor = newColor; // State modification - changing object's color
    }

    // ========== PRIVATE METHODS (Internal class behavior - encapsulation) ==========

    // Private scope - only accessible within this class (information hiding)
    // Helper method - supports public methods but hidden from external access
    private void capOn() {
        System.out.println("Click! Capping the pen.");
        this.isCapped = true; // State change - pen is now capped
    }

    // Private scope - encapsulated behavior
    private void capOff() {
        System.out.println("Click! Uncapping the pen.");
        this.isCapped = false; // State change - pen is now uncapped
    }

    // ========== ADDITIONAL METHODS FOR PASS BY VALUE/REFERENCE DEMONSTRATIONS ==========

    // Getter methods added to support pass by reference demonstrations
    public int getInkLevel() {
        return this.inkLevel;
    }

    public String getInkColor() {
        return this.inkColor;
    }

    // toString method for better object representation in demonstrations
    @Override
    public String toString() {
        return "Pen{" +
                "inkLevel=" + inkLevel +
                ", inkColor='" + inkColor + '\'' +
                ", tipType='" + tipType + '\'' +
                ", brand='" + brand + '\'' +
                ", isCapped=" + isCapped +
                '}';
    }
}



// ========== SEPARATE CLASS (Different class scope) ==========
class TestDemo {
    // Main method - entry point of Java application
    // Static method scope - belongs to class, not instance
    // JVM calls this method to start program execution
    public static void main(String[] args) {
        // Method scope - all variables declared here are local to main method
        // Stack frame created for main method execution

        System.out.println("=== BASIC PEN FUNCTIONALITY ===");

        // Local variable scope - 'myPen' exists only within main method
        // Object creation - new Pen object allocated on HEAP
        Pen myPen = new Pen(100, "green", "ballpoint");

        // Method calls - each creates new stack frame
        myPen.write("Hello");           // First write attempt
        myPen.write("Hello World!");    // Second write attempt
        myPen.checkInkLevel();          // Check current ink level
        myPen.changeColor("red");       // Change pen color
        myPen.write("Writing in new color"); // Write with new color
        myPen.checkInkLevel();          // Final ink level check

        // Additional object creation - demonstrates static variable sharing
        Pen secondPen = new Pen(100, "blue", "ballpoint");  // Local scope
        Pen thirdPen = new Pen(100, "black", "ballpoint");  // Local scope

        // Static method call - accessing class-level data
        // No object reference needed for static method
        System.out.println("Total Number of Pens created: " + Pen.getPenCount());



        // When main method ends, local variables go out of scope
        // Objects may become eligible for garbage collection if no references remain
    }
}


/**
 * INHERITANCE EXAMPLE (Currently commented out)
 *
 * This would demonstrate inheritance if Pen class wasn't final
 *
 class FountainPen extends Pen {
 // Subclass scope - inherits all non-private members from Pen
 private int nibSize; // Additional instance variable - subclass specific

 // Constructor scope - must call parent constructor
 public FountainPen(int inkLevel, String inkColor, String tipType, int nibSize) {
 super(inkLevel, inkColor, tipType); // Parent constructor call - must be first statement
 this.nibSize = nibSize; // Initialize subclass-specific variable
 }

 // Method scope - subclass can add new behaviors
 // Could override parent methods if they weren't final
 }
 **/

/**
 * SCOPE SUMMARY FOR LEARNING:
 *
 * 1. CLASS SCOPE: Variables/methods accessible throughout the class
 * 2. INSTANCE SCOPE: Variables belong to specific object instances
 * 3. METHOD SCOPE: Parameters and local variables within method boundaries
 * 4. BLOCK SCOPE: Variables within { } blocks (if, for, while, etc.)
 * 5. STATIC SCOPE: Belongs to class, shared among all instances
 * 6. PRIVATE SCOPE: Accessible only within the same class
 * 7. PUBLIC SCOPE: Accessible from anywhere
 *
 * MEMORY ALLOCATION:
 * - Objects: Stored on HEAP (dynamic memory)
 * - Method calls: Stack frames on STACK
 * - Static variables: Method Area (part of heap in modern JVMs)
 * - Local variables: Stack (within method stack frames)
 */
