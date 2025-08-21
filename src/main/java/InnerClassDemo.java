/**
 * Inner Class Demo - Demonstrates the relationship between outer and inner classes
 *
 * Key Inner Class Concepts:
 * 1. Inner classes have access to all members of the outer class (including private)
 * 2. Inner class instances are tied to an outer class instance
 * 3. Inner classes can be instantiated from within or outside the outer class
 */

// Outer class - represents the container/parent class
class University {
    // Private field - only accessible within University class
    // BUT inner classes can also access this!
    private String universityName;

    // Outer class constructor
    public University(String name) {
        this.universityName = name;
        System.out.println("University created: " + name);
    }

    // Inner class - belongs to University instance
    // Note: Inner class is NOT static, so it needs an outer class instance
    class Department {
        private String deptName;

        // Inner class constructor
        public Department(String name) {
            this.deptName = name;
            System.out.println("Department created: " + name);
        }

        public void displayInfo() {
            // KEY FEATURE: Inner class can directly access outer class private members
            // No need for getters or special access - direct access to 'universityName'
            System.out.println("Department: " + deptName + " | University: " + universityName);
        }

        // Inner class method that demonstrates access to outer class
        public void showRelationship() {
            System.out.println("I am " + deptName + " department of " + universityName);
            // Could also access other outer class methods if needed
        }
    }

    // Method in outer class that creates inner class instance
    public void createDepartment() {
        // Creating inner class instance from WITHIN outer class
        // Simple syntax: just 'new Department()'
        Department cs = new Department("Computer Science");
        cs.displayInfo();
        cs.showRelationship();
    }

    // Another method to demonstrate outer class functionality
    public void getUniversityInfo() {
        System.out.println("This is " + universityName);
    }
}

public class InnerClassDemo {
    public static void main(String[] args) {
        // Step 1: Create outer class instance first
        University myUniversity = new University("Tech University");

        // Step 2: Creating inner class instance from OUTSIDE the outer class
        // Special syntax required: outerInstance.new InnerClass()
        University.Department math = myUniversity.new Department("Mathematics");
        math.displayInfo();
        math.showRelationship();

        System.out.println("--- Separator ---");

        // Step 3: Creating inner class from outer class method
        // This demonstrates inner class creation from within outer class
        myUniversity.createDepartment();

        System.out.println("--- Separator ---");

        // Step 4: Create another university with its own departments
        University anotherUniversity = new University("Science Institute");
        University.Department physics = anotherUniversity.new Department("Physics");
        physics.displayInfo(); // Notice it shows "Science Institute" not "Tech University"

        /*
         * IMPORTANT NOTES ABOUT INNER CLASSES:
         *
         * 1. INSTANCE BINDING: Each Department is tied to a specific University instance
         * 2. MEMORY: Inner class holds implicit reference to outer class instance
         * 3. ACCESS: Inner class can access ALL outer class members (private, protected, public)
         * 4. INSTANTIATION: Two ways to create inner class:
         *    - From within outer class: new InnerClass()
         *    - From outside: outerInstance.new InnerClass()
         * 5. SCOPE: Inner class cannot exist without outer class instance
         */
    }
}