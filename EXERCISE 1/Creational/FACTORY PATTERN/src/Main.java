// 1. Color Interface: Defines the Color behavior
interface Color {
    void fill();  // Method to apply the color
}

// 2. Concrete Color Classes: Implementing the Color interface
class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with Red color.");
    }
}

class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with Green color.");
    }
}

class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with Blue color.");
    }
}

// 3. Color Factory: Creates Color objects based on specified type
class ColorFactory {
    // Factory method to create color based on the given type
    public static Color getColor(String colorType) {
        if (colorType == null) {
            return null;
        }
        switch (colorType.toLowerCase()) {
            case "red":
                return new Red();  // Create and return Red color
            case "green":
                return new Green(); // Create and return Green color
            case "blue":
                return new Blue();  // Create and return Blue color
            default:
                return null; // Unknown color type
        }
    }
}

// 4. Main Program: Demonstrates the Color Factory
public class Main {
    public static void main(String[] args) {
        // Using the ColorFactory to create different color objects
        Color red = ColorFactory.getColor("red");
        red.fill();  // Filling with Red color

        Color green = ColorFactory.getColor("green");
        green.fill(); // Filling with Green color

        Color blue = ColorFactory.getColor("blue");
        blue.fill();  // Filling with Blue color

        // Attempting to create an unknown color
        Color unknown = ColorFactory.getColor("yellow");
        if (unknown == null) {
            System.out.println("Unknown color type.");
        }
    }
}
