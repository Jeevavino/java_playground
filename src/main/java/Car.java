class Car {
    private String engine;
    private String wheels;

    // Private constructor - can only be called by Builder
    private Car(Builder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
    }

    // Getter methods to access car properties
    public String getEngine() {
        return engine;
    }

    public String getWheels() {
        return wheels;
    }

    // Override toString for easy display
    @Override
    public String toString() {
        return "Car{engine='" + engine + "', wheels='" + wheels + "'}";
    }

    // Static nested Builder class
    public static class Builder {
        private String engine;
        private String wheels;

        // Builder methods return 'this' for method chaining
        public Builder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Builder setWheels(String wheels) {
            this.wheels = wheels;
            return this;
        }

        // Build method creates and returns the Car instance
        public Car build() {
            return new Car(this);
        }
    }
}

class CarDemo {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demonstration ===\n");

        // Example 1: Building a sports car with method chaining
        Car sportsCar = new Car.Builder()
                .setEngine("V8 Turbo")
                .setWheels("Alloy Racing Wheels")
                .build();

        System.out.println("Sports Car: " + sportsCar);

        // Example 2: Building an electric car
        Car electricCar = new Car.Builder()
                .setEngine("Electric Motor")
                .setWheels("Eco-Friendly Wheels")
                .build();

        System.out.println("Electric Car: " + electricCar);

        // Example 3: Building a car step by step (without method chaining)
        Car.Builder truckBuilder = new Car.Builder();
        truckBuilder.setEngine("Diesel V6");
        truckBuilder.setWheels("Heavy Duty Tires");
        Car truck = truckBuilder.build();

        System.out.println("Truck: " + truck);

        // Example 4: Building a basic car with minimal configuration
        Car basicCar = new Car.Builder()
                .setEngine("4-Cylinder")
                .build(); // wheels will be null in this case

        System.out.println("Basic Car: " + basicCar);

        // Example 5: Demonstrating builder reusability
        Car.Builder reusableBuilder = new Car.Builder()
                .setEngine("Hybrid Engine");

        Car hybridSedan = reusableBuilder
                .setWheels("Standard Wheels")
                .build();

        Car hybridSUV = reusableBuilder
                .setWheels("All-Terrain Wheels")
                .build();

        System.out.println("Hybrid Sedan: " + hybridSedan);
        System.out.println("Hybrid SUV: " + hybridSUV);

        System.out.println("\n=== Builder Pattern Benefits ===");
        System.out.println("✓ Readable object construction");
        System.out.println("✓ Method chaining for fluent API");
        System.out.println("✓ Immutable objects (no setters in Car class)");
        System.out.println("✓ Optional parameters handling");
        System.out.println("✓ Step-by-step object creation");
    }
}