class User {
    private final String name;
    private final String email;

    // Private constructor - only the builder can create instances
    private User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters only - no setters to maintain immutability
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }

    // Static nested Builder class
    public static class Builder {
        private String name;
        private String email;

        // Builder methods - return 'this' for method chaining
        public Builder setName(String name) {
            this.name = name;
            return this;  // Returns the same Builder instance for chaining
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;  // Returns the same Builder instance for chaining
        }

        // Build method creates and returns the final object
        public User build() {
            // Validation - ensures object consistency
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Valid email is required");
            }

            // All validation passed - create the immutable User object
            return new User(name, email);
        }
    }
}
// ====
// DEMONSTRATION CLASS
// ====
public class BuilderPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== BUILDER PATTERN DEMONSTRATION ===\n");

        // 1. PROBLEM WITHOUT BUILDER
        System.out.println("1. Problems WITHOUT Builder Pattern:");
        System.out.println("   - Constructor: new User(\"John Doe\", \"john@email.com\")");
        System.out.println("     Which parameter is name? Which is email? Not clear!");
        System.out.println("   - Easy to swap parameters: new User(\"john@email.com\", \"John Doe\") ❌");
        System.out.println("   - No validation until object is used");
        System.out.println("   - If we add more fields later, constructor becomes unwieldy\n");

        // 2. SOLUTION WITH BUILDER
        System.out.println("2. Solution WITH Builder Pattern:\n");

        // Example 1: Clear, readable construction
        System.out.println("Example 1 - Clear and readable:");
        User user1 = new User.Builder()
                .setName("John Doe")
                .setEmail("john.doe@example.com")
                .build();
        System.out.println("   " + user1);
        System.out.println("   Crystal clear what each value represents!\n");

        // Example 2: Flexible order
        System.out.println("Example 2 - Flexible field order:");
        User user2 = new User.Builder()
                .setEmail("jane.smith@example.com")  // Email first
                .setName("Jane Smith")               // Name second
                .build();
        System.out.println("   " + user2);
        System.out.println("   Order doesn't matter - very flexible!\n");

        // 3. METHOD CHAINING EXPLANATION
        System.out.println("3. How 'return this;' enables method chaining:\n");
        System.out.println("   Step-by-step breakdown:");
        System.out.println("   1. new User.Builder()           → Creates Builder instance (call it 'builderX')");
        System.out.println("   2. .setName(\"John\")             → Called on builderX, returns builderX");
        System.out.println("   3. .setEmail(\"john@email.com\")  → Called on builderX (returned from step 2), returns builderX");
        System.out.println("   4. .build()                     → Called on builderX (returned from step 3), returns User");
        System.out.println("   Each method works on the SAME builder instance!\n");

        // 4. VALIDATION IN ACTION
        System.out.println("4. Built-in validation prevents bad objects:\n");

        try {
            User invalidUser1 = new User.Builder()
                    .setName("")  // Invalid: empty name
                    .setEmail("john@example.com")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("   ❌ Empty name caught: " + e.getMessage());
        }

        try {
            User invalidUser2 = new User.Builder()
                    .setName("John")
                    .setEmail("invalid-email")  // Invalid: no @
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("   ❌ Invalid email caught: " + e.getMessage());
        }

        try {
            User invalidUser3 = new User.Builder()
                    .setEmail("john@example.com")
                    // Missing name entirely
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("   ❌ Missing name caught: " + e.getMessage());
        }

        // 5. IMMUTABILITY DEMONSTRATION
        System.out.println("\n5. Object immutability:");
        User user3 = new User.Builder()
                .setName("Bob Wilson")
                .setEmail("bob@company.com")
                .build();
        System.out.println("   Created: " + user3);
        System.out.println("   ✅ Object cannot be modified after creation (no setters available)");
        System.out.println("   ✅ Thread-safe and predictable behavior\n");

        // 6. KEY BENEFITS SUMMARY
        System.out.println("6. Builder Pattern Benefits:");
        System.out.println("   ✅ Readable: .setName(\"John\") vs constructor parameter #1");
        System.out.println("   ✅ Flexible: Set fields in any order");
        System.out.println("   ✅ Validation: Catches errors before object creation");
        System.out.println("   ✅ Immutable: Objects can't be changed after creation");
        System.out.println("   ✅ Method chaining: Fluent, natural syntax");
        System.out.println("   ✅ Future-proof: Easy to add more fields later without breaking existing code");
    }
}