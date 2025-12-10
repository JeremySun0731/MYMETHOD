package components.polynomial;

import components.standard.Standard;

/**
 * Kernel interface for mutable polynomials.
 *
 * <p>
 * This interface defines the primary (low-level) operations for manipulating a
 * polynomial, including setting coefficients, inserting new terms, and
 * retrieving coefficient values. These methods are intended to be used by the
 * enhanced {@code Polynomial} interface.
 * </p>
 *
 * <p>
 * All polynomials are assumed to be of the form:
 * </p>
 * <pre>
 *     a_n * x^n + a_{n-1} * x^{n-1} + ... + a_1 * x + a_0
 * </pre> where each coefficient {@code a_i} is an integer, and only
 * non-negative integer degrees are allowed.
 * </p>
 */
public interface PolynomialKernel extends Standard<Polynomial> {

    /**
     * Sets the coefficient of the given degree to the specified non-zero value.
     *
     * @param degree
     *            the degree whose coefficient is to be changed
     * @param value
     *            the new non-zero coefficient for this degree
     *
     * @requires value != 0.0
     * @ensures this = #this with coefficient(degree) replaced by value
     */

    void setCoefficient(int degree, double value);

    /**
     * Returns the coefficient associated with the given degree.
     *
     * @param degree
     *            the target degree
     * @return the coefficient of this polynomial at the given degree; returns
     *         0.0 if no such term exists
     *
     * @ensures getCoefficient = coefficient(this, degree)
     */

    double getCoefficient(int degree);

    /**
     * remove the known coefficient of specific degree.
     *
     * @param degree
     *            the target degree of polynomial
     * @ensures coefficient(degree) = 0
     */
    void removeCoefficient(int degree);

    /**
     * return the highest degree of polynomial.
     *
     * @return the maximum degree of the polynomial
     * @ensure result = maxDegree(this)
     */
    int getDegree();

    /**
     * return the lowest degree of polynomial.
     *
     * @return the minimum degree of the polynomial
     * @ensure result = minDegree(this)
     */
    int getMinDegree();
}
