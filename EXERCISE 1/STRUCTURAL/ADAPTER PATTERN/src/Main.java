// 1. Target Interface
interface TemperatureConverter {
    double convertToFahrenheit(double celsius);
}

// 2. Adaptee Class
class CelsiusTemperature {
    public double getTemperatureInCelsius() {
        return 25.0; // Sample temperature in Celsius
    }
}

// 3. Adapter Class
class CelsiusToFahrenheitAdapter implements TemperatureConverter {
    private CelsiusTemperature celsiusTemperature;

    public CelsiusToFahrenheitAdapter(CelsiusTemperature celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
    }

    @Override
    public double convertToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32; // Conversion formula
    }

    public double getTemperatureInFahrenheit() {
        // Get temperature in Celsius from the adaptee and convert to Fahrenheit
        double celsius = celsiusTemperature.getTemperatureInCelsius();
        return convertToFahrenheit(celsius);
    }
}

// 4. Client Code
public class Main {
    public static void main(String[] args) {
        // Create an instance of the adaptee
        CelsiusTemperature celsiusTemp = new CelsiusTemperature();

        // Create an instance of the adapter
        TemperatureConverter adapter = new CelsiusToFahrenheitAdapter(celsiusTemp);

        // Convert and print the temperature
        double fahrenheit = adapter.convertToFahrenheit(celsiusTemp.getTemperatureInCelsius());
        System.out.println("Temperature in Fahrenheit: " + fahrenheit);

        // Alternatively, use the method in the adapter to get the temperature directly
        double fahrenheitFromAdapter = ((CelsiusToFahrenheitAdapter) adapter).getTemperatureInFahrenheit();
        System.out.println("Temperature in Fahrenheit (from adapter): " + fahrenheitFromAdapter);
    }
}
