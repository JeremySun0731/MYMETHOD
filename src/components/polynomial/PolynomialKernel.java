package components.polynomial;

import components.standard.Standard;

/**
 *
 * The {@code PolynomialKernel} interface defines the core operations for
 * manipulating the coefficients of a polynomial. It allows setting, getting,
 * and removing coefficients for specific degrees, as well as retrieving the
 * maximum and minimum degrees of the polynomial.
 * <p>
 * This interface follows the design-by-contract approach. Each method includes
 * preconditions and postconditions in the {@code @requires} and
 * {@code @ensures} clauses where applicable.
 * </p>
 *
 * @author Jeremy Sun
 * @date 2025-11-06
 *
 *
 */
public interface PolynomialKernel extends Standard<Polynomial> {

    /**
     * Sets the coefficient of the given degree to the specified non-zero value.
     *
     * @param degree
     *            the (possibly negative) degree whose coefficient is to be
     *            changed
     * @param value
     *            the new non-zero coefficient
     *
     * @requires value != 0.0
     * @ensures this = #this with coefficient(degree) replaced by value
     */
    void setCoefficient(int degree, double value);

    /**
     * Returns the coefficient associated with the given degree.
     *
     * @param degree
     *            the (possibly negative) degree
     * @return the coefficient stored for {@code degree}, or {@code 0.0} if no
     *         such term exists
     *
     * @ensures getCoefficient = coefficient(this, degree)
     */

    double getCoefficient(int degree);

    /**
     * Removes the term associated with the given degree.
     *
     * @param degree
     *            the (possibly negative) degree to remove
     * @ensures coefficient(degree) = 0.0
     */
    void removeCoefficient(int degree);

    /**
     * Returns the largest degree with a non-zero coefficient.
     *
     * @return the maximum stored degree, or {@code -1} if no terms exist
     * @ensures result = maxDegree(this)
     */
    int getDegree();

    /**
     * Returns the smallest degree with a non-zero coefficient.
     *
     * @return the minimum stored degree, or {@code 1} if no terms exist
     * @ensures result = minDegree(this)
     */
    int getMinDegree();
}
