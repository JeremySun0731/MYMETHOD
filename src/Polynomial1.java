/**
 * The {@code Polynomial} interface represents an immutable mathematical
 * polynomial with standard algebraic operations such as addition,
 * multiplication, differentiation, and integration.
 *
 * <p>
 * This interface follows the design-by-contract approach. Each method includes
 * preconditions and postconditions in the {@code @requires} and
 * {@code @ensures} clauses where applicable.
 * </p>
 *
 * @author YourName
 * @date 2025-11-06
 */
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