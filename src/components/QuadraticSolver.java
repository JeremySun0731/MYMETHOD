package components;

import components.polynomial.Polynomial;
import components.polynomial.Polynomial1L;

public class QuadraticSolver {

    /**
     * Main method to demonstrate solving a quadratic polynomial using ONLY the
     * PolynomialKernel interface methods.
     * 
     * @param args
     *            command line arguments (not used)
     *
     */
    public static void main(String[] args) {

        // Step 1: create polynomial using constructor (kernel: newInstance)
        Polynomial p = new Polynomial1L();

        // Step 2: set coefficients (kernel)
        p.setCoefficient(2, 2.0); // 2x^2
        p.setCoefficient(1, -3.0); // -3x
        p.setCoefficient(0, 1.0); // +1

        // Step 3: print polynomial using ONLY kernel
        System.out.println("Polynomial:");
        printPolynomial(p);

        // Step 4: extract coefficients using kernel
        double a = p.getCoefficient(2);
        double b = p.getCoefficient(1);
        double c = p.getCoefficient(0);

        // Step 5: compute discriminant
        double discriminant = b * b - 4 * a * c;
        System.out.println("\nDiscriminant = " + discriminant);

        // Step 6: solve roots (math only, allowed)
        if (discriminant < 0) {
            System.out.println("No real roots.");
        } else {
            double sqrt = Math.sqrt(discriminant);
            double root1 = (-b + sqrt) / (2 * a);
            double root2 = (-b - sqrt) / (2 * a);
            System.out.println("Root 1 = " + root1);
            System.out.println("Root 2 = " + root2);
        }

        // Step 7 (bonus): demonstrate removeCoefficient() and clear()
        p.removeCoefficient(1); // remove x term
        System.out.println("\nAfter removing x term:");
        printPolynomial(p);

        p.clear(); // clear polynomial
        System.out.println("\nAfter clearing polynomial:");
        printPolynomial(p);
    }

    /**
     * Prints a polynomial using ONLY kernel methods.
     */
    private static void printPolynomial(Polynomial p) {

        int degree = p.getDegree();
        boolean printedAny = false;

        for (int d = degree; d >= 0; d--) {
            double coef = p.getCoefficient(d);

            if (coef != 0) {
                printedAny = true;

                // Sign
                if (d != degree) {
                    System.out.print(coef >= 0 ? " + " : " - ");
                } else if (coef < 0) {
                    System.out.print("-");
                }

                coef = Math.abs(coef);

                // Term printing
                if (d == 0) {
                    System.out.print(coef);
                } else if (d == 1) {
                    System.out.print(coef + "x");
                } else {
                    System.out.print(coef + "x^" + d);
                }
            }
        }

        if (!printedAny) {
            System.out.print("0");
        }

        System.out.println();
    }
}
