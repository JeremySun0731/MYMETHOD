package components;

import components.polynomial.Polynomial;
import components.polynomial.Polynomial1L;

public class QuadraticSolver {

    /**
     * Represents ax^2 + bx + c using Polynomial1L and prints the real roots of
     * the equation.
     */
    public static void main(String[] args) {

        // Example: 2x^2 - 3x + 1 = 0
        Polynomial p = new Polynomial1L();
        p.setCoefficient(2, 2); // 2x^2
        p.setCoefficient(1, -3); // -3x
        p.setCoefficient(0, 1); // +1

        System.out.println("Polynomial: " + p);

        double a = p.getCoefficient(2);
        double b = p.getCoefficient(1);
        double c = p.getCoefficient(0);

        double discriminant = b * b - 4 * a * c;

        System.out.println("Discriminant: " + discriminant);

        // Compute real roots when possible
        if (discriminant < 0) {
            System.out.println("No real roots.");
        } else {
            double sqrt = Math.sqrt(discriminant);
            double root1 = (-b + sqrt) / (2 * a);
            double root2 = (-b - sqrt) / (2 * a);

            System.out.println("Root 1: " + root1);
            System.out.println("Root 2: " + root2);
        }
    }
}
