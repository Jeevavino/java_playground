// Outer class
class University {
    private String universityName;

    public University(String name) {
        this.universityName = name;
    }

    // Inner class - belongs to University instance
    class Department {
        private String deptName;

        public Department(String name) {
            this.deptName = name;
        }

        public void displayInfo() {
            // Inner class can access outer class private members
            System.out.println("Department: " + deptName + " | University: " + universityName);
        }
    }

    public void createDepartment() {
        // Creating inner class instance from outer class method
        Department cs = new Department("Computer Science");
        cs.displayInfo();
    }
}

public class InnerClassDemo {
    public static void main(String[] args) {
        University myUniversity = new University("Tech University");

        // Creating inner class instance from outside
        University.Department math = myUniversity.new Department("Mathematics");
        math.displayInfo();

        // Creating inner class from outer class method
        myUniversity.createDepartment();
    }
}
