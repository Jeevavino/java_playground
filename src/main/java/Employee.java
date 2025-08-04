import java.util.Objects;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Immutable Employee class demonstrating proper equals/hashCode implementation
 *
 * KEY CONCEPTS:
 * - hashCode() enables O(1) performance in hash collections
 * - Equal objects MUST have same hashCode (hashCode contract)
 * - HashSet uses hashCode() for bucketing, equals() for collision handling
 */
public final class Employee {
    private final String name;
    private final int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    // Two employees equal if same id and name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    // Must include all fields used in equals()
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + "}";
    }

    public static void main(String[] args) {
        Employee emp1 = new Employee("John", 123);
        Employee emp2 = new Employee("John", 123);  // Same as emp1
        Employee emp3 = new Employee("Jane", 456);

        System.out.println("=== EQUALS & HASHCODE ===");
        System.out.println("emp1.equals(emp2): " + emp1.equals(emp2)); // true
        System.out.println("Same hashCode: " + (emp1.hashCode() == emp2.hashCode())); // true

        System.out.println("\n=== HASHSET DEMO ===");
        HashSet<Employee> set = new HashSet<>();
        set.add(emp1);
        set.add(emp2); // Duplicate - won't be added
        set.add(emp3);
        System.out.println("Set size: " + set.size()); // 2, not 3

        // Fast O(1) lookup using hashCode
        System.out.println("Contains John(123): " + set.contains(new Employee("John", 123)));

        System.out.println("\n=== HASHMAP DEMO ===");
        Map<Employee, String> roles = new HashMap<>();
        roles.put(emp1, "Engineer");
        roles.put(emp3, "Manager");

        // Fast O(1) lookup
        System.out.println("John's role: " + roles.get(new Employee("John", 123)));

        System.out.println("\n=== KEY POINTS ===");
        System.out.println("• hashCode() enables fast hash collection operations");
        System.out.println("• Equal objects must have equal hash codes");
        System.out.println("• Include all equals() fields in hashCode()");
    }
}