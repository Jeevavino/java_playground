// ========================================
// REGULAR CLASS (ALLOWS MULTIPLE INSTANCES)
// ========================================

class DatabaseConnection {
    private String connectionId;
    private boolean isConnected;

    public DatabaseConnection() {
        this.connectionId = "DB_" + (int)(Math.random() * 1000);
        this.isConnected = false;
        System.out.println("❌ New DatabaseConnection created: " + connectionId);
    }

    public void connect() {
        this.isConnected = true;
        System.out.println("Connected: " + connectionId);
    }

    public String getStatus() {
        return connectionId + " - " + (isConnected ? "CONNECTED" : "DISCONNECTED");
    }
}

// ========================================
// SINGLETON CLASS (SINGLE INSTANCE ONLY)
// ========================================

class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private String connectionId;
    private boolean isConnected;

    // Private constructor prevents external instantiation
    private DatabaseSingleton() {
        this.connectionId = "SINGLETON_DB_" + (int)(Math.random() * 1000);
        this.isConnected = false;
        System.out.println("✅ Singleton DatabaseConnection created: " + connectionId);
    }

    // Public static method to get the single instance
    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public void connect() {
        this.isConnected = true;
        System.out.println("Singleton connected: " + connectionId);
    }

    public String getStatus() {
        return connectionId + " - " + (isConnected ? "CONNECTED" : "DISCONNECTED");
    }
}

// ========================================
// DEMONSTRATION
// ========================================

public class SingletonDemo {
    public static void main(String[] args) {
        System.out.println("=== SINGLETON PATTERN DEMO ===\n");

        // Problem: Regular class creates multiple instances
        System.out.println("❌ PROBLEM - Regular Class:");
        DatabaseConnection db1 = new DatabaseConnection();
        DatabaseConnection db2 = new DatabaseConnection();
        DatabaseConnection db3 = new DatabaseConnection();

        System.out.println("db1 == db2: " + (db1 == db2)); // false
        System.out.println("Created 3 different objects\n");

        // Solution: Singleton returns same instance
        System.out.println("✅ SOLUTION - Singleton:");
        DatabaseSingleton single1 = DatabaseSingleton.getInstance();
        DatabaseSingleton single2 = DatabaseSingleton.getInstance();
        DatabaseSingleton single3 = DatabaseSingleton.getInstance();

        System.out.println("single1 == single2: " + (single1 == single2)); // true
        System.out.println("single2 == single3: " + (single2 == single3)); // true
        System.out.println("All references point to same object\n");

        // Demonstrate shared state
        System.out.println("🔄 SHARED STATE:");
        single1.connect(); // Connect using first reference
        System.out.println("After single1.connect():");
        System.out.println("single1 status: " + single1.getStatus());
        System.out.println("single2 status: " + single2.getStatus()); // Same status!
        System.out.println("single3 status: " + single3.getStatus()); // Same status!

        System.out.println("\n📊 SUMMARY:");
        System.out.println("Regular class: 3 objects created");
        System.out.println("Singleton: 1 object, 3 references");
        System.out.println("Memory saved: " + (3-1) + " objects");
    }
}