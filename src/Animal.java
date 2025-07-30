// ========== ABSTRACT BASE CLASS ==========
public abstract class Animal {
    protected String name;
    protected int age;
    protected static int totalAnimals = 0;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        totalAnimals++;
        System.out.println("Animal created: " + name);
    }

    // Concrete method
    public void eat() {
        System.out.println(name + " is eating");
    }

    // Abstract methods - must be implemented
    public abstract void makeSound();
    public abstract void move();

    public static int getTotalAnimals() { return totalAnimals; }
}

// ========== INHERITANCE WITH INTERFACES ==========
class Bird extends Animal implements Flyable {
    public Bird(String name, int age) {
        super(name, age); // super() constructor call
    }

    @Override
    public void makeSound() {
        System.out.println(name + " chirps");
    }

    @Override
    public void move() {
        System.out.println(name + " hops");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }

    // Method overriding with super()
    @Override
    public void eat() {
        super.eat(); // Call parent method
        System.out.println(name + " pecks at food");
    }
}

class Fish extends Animal implements Swimmable {
    public Fish(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " bubbles");
    }

    @Override
    public void move() {
        System.out.println(name + " swims");
    }

    @Override
    public void swim() {
        System.out.println(name + " swims gracefully");
    }
}

// Multiple interface implementation
class Duck extends Animal implements Flyable, Swimmable {
    public Duck(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " quacks");
    }

    @Override
    public void move() {
        System.out.println(name + " waddles");
    }

    @Override
    public void fly() {
        System.out.println(name + " flies over water");
    }

    @Override
    public void swim() {
        System.out.println(name + " paddles on water");
    }
}

/**
 * Concise OOP Concepts Demo - Animal Theme
 * Covers: Inheritance, Abstract Classes, super(), Interfaces, Object Coupling
 */

// ========== INTERFACES ==========
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

interface CareService {
    void provideCare(String animalName);
}

 class AnimalTest {
    public static void main(String[] args) {
        System.out.println("=== Creating Animals ===");

        // Create different animals
        Bird sparrow = new Bird("Sparrow", 2);
        Fish goldfish = new Fish("Goldie", 1);
        Duck mallard = new Duck("Mallard", 3);

        System.out.println("Total animals: " + Animal.getTotalAnimals());

        System.out.println("\n=== Testing super() ===");
        sparrow.eat(); // Bird overrides eat() and calls super.eat()

        System.out.println("\n=== Testing Polymorphism ===");
        // Same method call, different behavior for each animal
        Animal[] animals = {sparrow, goldfish, mallard};

        for (Animal animal : animals) {
            System.out.println("\n" + animal.name + ":");
            animal.makeSound(); // Different sound for each animal
            animal.move();      // Different movement for each animal
        }

        System.out.println("\n=== Testing Interfaces ===");

        // Animals that can fly
        System.out.println("Flying animals:");
        sparrow.fly();
        mallard.fly();

        // Animals that can swim
        System.out.println("\nSwimming animals:");
        goldfish.swim();
        mallard.swim();

        System.out.println("\n=== Testing Multiple Interfaces ===");
        System.out.println("Duck can do both:");
        mallard.fly();
        mallard.swim();

        System.out.println("\n=== Testing instanceof ===");
        checkAnimalType(sparrow);
        checkAnimalType(goldfish);
        checkAnimalType(mallard);
    }

    // Simple method to check what type of animal it is
    public static void checkAnimalType(Animal animal) {
        System.out.println("\nChecking " + animal.name + ":");

        if (animal instanceof Bird) {
            System.out.println("This is a Bird");
        }
        if (animal instanceof Fish) {
            System.out.println("This is a Fish");
        }
        if (animal instanceof Duck) {
            System.out.println("This is a Duck");
        }

        // Check what it can do
        if (animal instanceof Flyable) {
            System.out.println("Can fly!");
        }
        if (animal instanceof Swimmable) {
            System.out.println("Can swim!");
        }
    }
}

