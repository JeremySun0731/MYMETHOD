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
     * Creates a new (empty) polynomial.
     *
     * @ensures this = 0
     */
    void newPolynomial();

    /**
     * change the known coefficient of the specific degree.
     *
     * @param degree
     *            the target degree of polynomial
     * @param value
     *            the new number of the target polynomial
     * @ensures coefficient(degree) = value
     */
    void setCoefficient(int degree, double value);

    /**
     * get the efficient of the known polynomial.
     *
     * @param degree
     *            the specific degree
     * @return the value of specific degree's coefficient
     * @ensures result = coefficient(degree)
     */
    double getCoefficient(int degree);

    /**
     * return the highest degree of polynomial.
     *
     * @return the maximum degree of the polynomial
     * @ensure result = maxDegree(this)
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
     * Clears the entire polynomial, setting all coefficients to zero.
     *
     * @ensures this = 0
     */
    @Override
    void clear();
}
