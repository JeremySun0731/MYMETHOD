package components;

import components.polynomial.Polynomial;
import components.polynomial.Polynomial1L;

/**
 * A simple quadratic polynomial solver using the Polynomial component. This
 * class demonstrates the use of kernel operations to create and manipulate a
 * quadratic polynomial.
 * <p>
 * Note: This class is for demonstration purposes only and does not include
 * comprehensive error handling or user input.
 * </p>
 */
public final class QuadraticSolver {
    /**
     * Private constructor to prevent instantiation.
     */
    private QuadraticSolver() {
        // private constructor to prevent instantiation
    }

    /**
     * Main method to demonstrate solving a quadratic polynomial.
     *
     * @param args
     */
    public static void main(String[] args) {

        // Create polynomial using OSU-style component construction
        Polynomial p = new Polynomial1L();

        // Kernel operations: build 2x^2 - 3x + 1
        p.setCoefficient(2, 2.0); // 2x^2
        p.setCoefficient(1, -3.0); // -3x
        p.setCoefficient(0, 1.0); // +1

        // secondary operation toString() for display
        System.out.println("Polynomial: " + p);

        // extract coefficients
        double a = p.getCoefficient(2);
        double b = p.getCoefficient(1);
        double c = p.getCoefficient(0);
        // Solve quadratic
        double disc = b * b - 4 * a * c;
        System.out.println("Discriminant = " + disc);

        if (disc < 0) {
            System.out.println("No real roots.");
        } else {
            double sqrt = Math.sqrt(disc);
            double root1 = (-b + sqrt) / (2 * a);
            double root2 = (-b - sqrt) / (2 * a);

            System.out.println("Root 1 = " + root1);
            System.out.println("Root 2 = " + root2);
        }

        // remove one term
        p.removeCoefficient(1);
        System.out.println("\nAfter removing x term: " + p);

        // clear polynomial
        p.clear();
        System.out.println("After clear: " + p);
    }

}