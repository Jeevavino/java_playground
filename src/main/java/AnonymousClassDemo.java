
// Interface for anonymous class implementation
interface Greeting {
    void sayHello();
    void sayGoodbye();
}

class Button {
    private String label;

    public Button(String label) {
        this.label = label;
    }

    // Method that accepts anonymous class as parameter
    public void onClick(Greeting greeting) {
        System.out.print("Button '" + label + "' clicked: ");
        greeting.sayHello();
    }
}


class AnonymousClassDemo {
    public static void main(String[] args) {

        // Example 1: Anonymous class implementing interface
        Greeting englishGreeting = new Greeting() {
            // Anonymous class - no name, implements interface
            @Override
            public void sayHello() {
                System.out.println("Hello!");
            }

            @Override
            public void sayGoodbye() {
                System.out.println("Goodbye!");
            }
        };

        englishGreeting.sayHello();
        englishGreeting.sayGoodbye();

        // Example 2: Anonymous class as method parameter
        Button myButton = new Button("Submit");

        myButton.onClick(new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Form submitted successfully!");
            }

            @Override
            public void sayGoodbye() {
                System.out.println("Closing form...");
            }
        });

        // Example 3: Anonymous class with abstract class
        // (Instead of interface, using abstract class)
        abstract class Animal {
            abstract void makeSound();
        }

        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Woof! Woof!");
            }
        };

        dog.makeSound();
    }
}