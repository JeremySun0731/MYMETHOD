package components.polynomial;

/**
 * The {@code Polynomial} interface represents a mutable mathematical polynomial
 * supporting standard algebraic operations such as addition, subtraction,
 * multiplication, differentiation, and integration.
 *
 * <p>
 * Implementations follow the design-by-contract methodology. Each operation
 * specifies appropriate {@code @requires} and {@code @ensures} clauses.
 * </p>
 */
public interface Polynomial extends PolynomialKernel {

    /**
     * Returns a new polynomial equal to the sum of this polynomial and p.
     *
     * @param p
     *            the polynomial to add
     * @return result = this + p
     * @requires p != null
     * @ensures result = this + p
     */
    Polynomial add(Polynomial p);

    /**
     * Returns a new polynomial equal to the difference of this polynomial and
     * p.
     *
     * @param p
     *            the polynomial to subtract
     * @return result = this - p
     * @requires p != null
     * @ensures result = this - p
     */
    Polynomial subtract(Polynomial p);

    /**
     * Returns a new polynomial equal to the product of this polynomial and p.
     *
     * @param p
     *            the polynomial to multiply
     * @return result = this * p
     * @requires p != null
     * @ensures result = this * p
     */
    Polynomial multiply(Polynomial p);

    /**
     * Returns the derivative of this polynomial.
     *
     * @return result = derivative(this)
     * @ensures result = derivative(this)
     */
    Polynomial derivative();

    /**
     * Returns the indefinite integral of this polynomial (constant = 0).
     *
     * @return result = ∫ this dx
     * @ensures result = integral(this)
     */
    Polynomial integrate();

    /**
     * Evaluates this polynomial at x.
     *
     * @param x
     * @return f(x)
     */
    double evaluate(double x);

    /**
     * Returns whether this polynomial is the zero polynomial.
     *
     * @return true iff all coefficients = 0
     */
    boolean isZero();

    /**
     * Returns a new polynomial equal to this polynomial scaled by c.
     *
     * @param c
     * @return result = c * this
     * @ensures result = c * this
     */
    Polynomial scale(double c);
}
