public final class Employee {
    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name != null ? name : "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

record EmployeeRecord(int id, String name) { }

class EmployeeDemo {
    public static void main(String[] args) {
        // Demo for Employee class
        System.out.println("--- Employee Class Demo ---");
        Employee emp = new Employee(1, "Alice");
        System.out.println("Original: id=" + emp.getId() + ", name=" + emp.getName());

        // Show immutability
        String modifiedName = emp.getName().toUpperCase();
        System.out.println("Original name after toUpperCase: " + emp.getName()); // Unchanged
        System.out.println("Modified name: " + modifiedName);

        // Demo for EmployeeRecord
        System.out.println("\n--- EmployeeRecord Demo ---");
        EmployeeRecord empRecord = new EmployeeRecord(2, "Bob");
        System.out.println("Original: id=" + empRecord.id() + ", name=" + empRecord.name());

        // Show immutability
        modifiedName = empRecord.name().toUpperCase();
        System.out.println("Original name after toUpperCase: " + empRecord.name()); // Unchanged
        System.out.println("Modified name: " + modifiedName);
    }
}