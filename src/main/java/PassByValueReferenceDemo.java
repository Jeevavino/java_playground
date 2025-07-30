 // ========== NEW CLASS FOR PASS BY VALUE AND PASS BY REFERENCE DEMONSTRATIONS ==========


public class PassByValueReferenceDemo {

    // ========== PASS BY VALUE EXAMPLES (Primitives) ==========

    /**
     * Demonstrates pass by value with primitive int
     * Parameter scope - 'number' is a local copy of the passed value
     * Changes to parameter don't affect original variable
     */
    public static void tryToChangeInt(int number) {
        // Method scope - 'number' is a copy of the original value
        System.out.println("  Inside method - received: " + number);
        number = 999; // This only changes the local copy on the stack
        System.out.println("  Inside method - changed to: " + number);
        // When method ends, local copy is destroyed
    }

    /**
     * Demonstrates pass by value with primitive boolean
     * Parameter scope - boolean parameter is copied by value
     */
    public static void tryToChangeBoolean(boolean flag) {
        System.out.println("  Inside method - received: " + flag);
        flag = !flag; // This only changes the local copy
        System.out.println("  Inside method - changed to: " + flag);
    }



    // ========== PASS BY REFERENCE EXAMPLES (Objects) ==========

    /**
     * Demonstrates pass by reference - modifying object state
     * Parameter scope - 'pen' is a copy of the reference, but points to same object
     * The original object IS modified because we're working with the same object reference
     */
    public static void modifyPenState(Pen pen) {
        System.out.println("  Inside method - received pen: " + pen);
        pen.setInkLevel(25); // This WILL change the original object on the heap
        pen.changeColor("purple"); // This WILL change the original object
        System.out.println("  Inside method - modified pen: " + pen);
        // Reference copy is destroyed when method ends, but object changes persist
    }

    /**
     * Demonstrates modifying objects through array reference
     * Arrays are objects, so array reference is passed by value
     * But we can modify array contents and the objects they reference
     */
    public static void modifyPenArray(Pen[] pens) {
        System.out.println("  Inside method - received array with " + pens.length + " pens");
        if (pens.length > 0) {
            pens[0].setInkLevel(75); // This WILL modify the original object
            // This WILL change the array element reference
            pens[0] = new Pen(90, "orange", "gel");
        }
        // Array reference copy is destroyed, but changes to array contents persist
    }

    /**
     * Demonstrates String immutability - behaves like pass by value
     * Strings are objects but immutable, so changes create new objects
     */
    public static void tryToModifyString(String text) {
        System.out.println("  Inside method - received: " + text);
        text = text + " MODIFIED"; // Creates new String object, doesn't modify original
        System.out.println("  Inside method - changed to: " + text);
        // Original String object remains unchanged due to immutability
    }

    public static void main(String[] args) {

        // ========== PASS BY VALUE DEMONSTRATIONS ==========
        System.out.println("\n=== PASS BY VALUE DEMONSTRATIONS (Primitives) ===");

        // Pass by Value - Primitives are copied to method stack frame
        System.out.println("\n1. Pass by Value with int:");
        int originalNumber = 42; // Local variable on main's stack frame
        System.out.println("Before method call: " + originalNumber);
        PassByValueReferenceDemo.tryToChangeInt(originalNumber); // Copy passed to method
        System.out.println("After method call: " + originalNumber); // Still 42! Original unchanged

        System.out.println("\n2. Pass by Value with boolean:");
        boolean originalFlag = true; // Local variable
        System.out.println("Before method call: " + originalFlag);
        PassByValueReferenceDemo.tryToChangeBoolean(originalFlag); // Copy passed
        System.out.println("After method call: " + originalFlag); // Still true! Original unchanged

        // ========== PASS BY REFERENCE DEMONSTRATIONS ==========
        System.out.println("\n=== PASS BY REFERENCE DEMONSTRATIONS (Objects) ===");

        // Pass by Reference - Object references are copied, but point to same heap object
        System.out.println("\n3. Pass by Reference - Modifying Object State:");
        Pen testPen = new Pen(100, "green", "ballpoint"); // Object on heap, reference on stack
        System.out.println("Before method call: " + testPen);
        PassByValueReferenceDemo.modifyPenState(testPen); // Reference copy passed
        System.out.println("After method call: " + testPen); // Object IS modified! Same heap object


        System.out.println("\n7. Pass by Reference - Array of Objects:");
        Pen[] penArray = {new Pen(40, "purple", "ballpoint"), new Pen(60, "orange", "ballpoint")};
        System.out.println("Before method - penArray[0]: " + penArray[0]);
        PassByValueReferenceDemo.modifyPenArray(penArray); // Array reference copy passed
        System.out.println("After method - penArray[0]: " + penArray[0]); // Changed! Array contents modified

        System.out.println("\n8. String Immutability - Behaves like Pass by Value:");
        String originalText = "Hello"; // String object on heap (string pool)
        System.out.println("Before method call: " + originalText);
        PassByValueReferenceDemo.tryToModifyString(originalText); // Reference copy passed
        System.out.println("After method call: " + originalText); // Unchanged! Strings are immutable

        // ========== KEY TAKEAWAYS ==========
        System.out.println("\n=== KEY TAKEAWAYS ===");
        System.out.println("• PRIMITIVES: Pass by Value - copies made on method stack frame");
        System.out.println("• OBJECTS: Pass by Reference Value - reference copies point to same heap object");
        System.out.println("• You CAN modify object state through the reference copy");
        System.out.println("• You CANNOT change the original reference variable itself");
        System.out.println("• Arrays are objects - contents can be modified through reference copy");
        System.out.println("• Strings are immutable - modifications create new objects");
        System.out.println("• Method parameters exist in method scope - destroyed when method ends");
        System.out.println("• Object modifications persist because they affect heap memory");
    }
}