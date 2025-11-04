public interface Polynomial1 extends PolynomialKernel {
    /**
     * add two polynomial together.
     *
     * @param p
     *            another polynomial
     * @return the sum of this and p
     * @ensure this = this+p
     */
    Polynomial add(Polynomial p);

    /**
     * multiply this with p.
     * 
     * @param p
     *            another polynomial
     * @return the multiplication of p and this
     * @ensure this = this*p
     */
    Polynomial multiply(Polynomial p);

    /**
     * Returns the derivative of this polynomial.
     * 
     * @return the derivative polynomial
     * @ensures result = derivative(this)
     */
    Polynomial derivative();

    /**
     * Returns the indefinite integral of this polynomial (without constant).
     * 
     * @return the integral polynomial
     * @ensures result = ∫this dx
     */
    Polynomial integrate();
}