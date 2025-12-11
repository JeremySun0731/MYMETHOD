package components;

import components.polynomial.Polynomial;
import components.polynomial.Polynomial1L;

public final class FunctionAnalysis {
    /**
     * Private constructor to prevent instantiation.
     */
    private FunctionAnalysis() {
        // private constructor to prevent instantiation
    }

    public static void main(String[] args) {
        // Create polynomial using OSU-style component construction
        Polynomial g = new Polynomial1L();
        g.setCoefficient(3, 1.0); // x^3
        g.setCoefficient(2, -6.0); // -6x^2
        g.setCoefficient(1, 11.0); // +11x
        g.setCoefficient(0, -6.0); // -6
        // Display polynomial
        System.out.println("Function: " + g);
        // Compute and display derivative
        Polynomial gDeriv = g.derivative();
        System.out.println("Derivative: " + gDeriv);
        // Compute and display integral
        Polynomial gInteg = g.integrate();
        System.out.println("Indefinite Integral: " + gInteg + " + C");
        // Evaluate at specific points
        double[] testPoints = { 0.0, 1.0, 2.0, 3.0 };
        // Display evaluations
        System.out.println("Function Evaluations:");
        for (double x : testPoints) {
            double value = g.evaluate(x);
            System.out.println("g(" + x + ") = " + value);
        }
        // Another polynomial for further analysis
        Polynomial h = new Polynomial1L();
        h.setCoefficient(2, 1.0); // x^2
        h.setCoefficient(1, -4.0); // -4x
        h.setCoefficient(0, 4.0); // +4
        System.out.println("Another Function: " + h);
        // Sum of g and h
        Polynomial sum = g.add(h);
        System.out.println("Sum (g + h): " + sum);
        // Difference of g and h
        Polynomial diff = g.subtract(h);
        System.out.println("Difference (g - h): " + diff);
        // Product of g and h
        Polynomial prod = g.multiply(h);
        System.out.println("Product (g * h): " + prod);
    }

}
