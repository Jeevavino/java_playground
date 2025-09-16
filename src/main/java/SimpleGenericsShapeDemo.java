// Simple Shape classes
abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double getArea();
    public String getColor() { return color; }
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width, height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

// Generic class with bounded type parameter
class ShapeBox<T extends Shape> {
    private T shape;

    public void put(T shape) {
        this.shape = shape;
    }

    public T get() {
        return shape;
    }

    // Can call Shape methods because T extends Shape
    public double getShapeArea() {
        return shape.getArea();
    }

    public String getShapeColor() {
        return shape.getColor();
    }
}

// Generic utility methods
class ShapeUtils {

    // Generic method with bounded type
    public static <T extends Shape> T getLargerShape(T shape1, T shape2) {
        return shape1.getArea() > shape2.getArea() ? shape1 : shape2;
    }

    public static <T extends Shape> void printShapeInfo(T shape) {
        System.out.println("Color: " + shape.getColor() +
                ", Area: " + String.format("%.2f", shape.getArea()));
    }
}

public class SimpleGenericsShapeDemo {

    public static void main(String[] args) {
        System.out.println("=== Simple Generics with T extends Shape ===\n");

        // Create shapes
        Circle circle = new Circle("Red", 5.0);
        Rectangle rectangle = new Rectangle("Blue", 4.0, 6.0);

        // Create shape boxes
        ShapeBox<Circle> circleBox = new ShapeBox<>();
        ShapeBox<Rectangle> rectangleBox = new ShapeBox<>();
        ShapeBox<Shape> shapeBox = new ShapeBox<>();

        // Put shapes in boxes
        circleBox.put(circle);
        rectangleBox.put(rectangle);
        shapeBox.put(circle); // Can put any shape

        // Get shapes and their properties
        System.out.println("Circle Box:");
        System.out.println("Area: " + circleBox.getShapeArea());
        System.out.println("Color: " + circleBox.getShapeColor());

        System.out.println("\nRectangle Box:");
        System.out.println("Area: " + rectangleBox.getShapeArea());
        System.out.println("Color: " + rectangleBox.getShapeColor());

        // Use generic utility methods
        System.out.println("\nComparing shapes:");
        Shape larger = ShapeUtils.getLargerShape(circle, rectangle);
        ShapeUtils.printShapeInfo(larger);

        System.out.println("\n=== Key Benefits ===");
        System.out.println("✓ Type Safety: Only Shape objects allowed");
        System.out.println("✓ Can call Shape methods on T");
        System.out.println("✓ Works with any Shape subclass");
    }
}
