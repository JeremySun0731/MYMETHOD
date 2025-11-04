import components.standard.Standard;

public interface PolynomialKernel extends Standard<Polynomial> {
    /**
     * Creates a new (empty) polynomial.
     *
     * @ensures this = 0
     */
    void newPolynomial();

    /**
     * Inserts a term in this polynomial.
     *
     * <p>
     * a new term is added.
     * </p>
     *
     * @param degree
     *            the degree (exponent) of the term to insert or update
     * @param value
     *            the coefficient of the term
     */
    public void put(int degree, int value);

    /**
     * change the known coefficient of the specific degree
     *
     * @param degree
     *            the target degree of polynomial
     * @param value
     *            the new number of the target polynomial
     * @ensures coefficient(degree) = value
     */
    void setCoefficient(int degree, int value);

    /**
     * get the efficient of the known polynomial
     *
     * @param degree
     *            the specific degree
     * @return the value of specific degree's coefficient
     * @ensures result = coefficient(degree)
     */
    int getCoefficient(int degree);

    /**
     * return the highest degree of polynomial
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
