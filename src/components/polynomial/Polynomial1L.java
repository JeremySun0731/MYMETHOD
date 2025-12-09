package components.polynomial;

import components.map.Map;
import components.map.Map1L;

/**
 * Kernel implementation for the Polynomial component using a Map-based
 * representation. The keys represent degrees (non-negative integers), and
 * values represent coefficients (real numbers). Duplicate degrees are not
 * allowed, and only non-zero terms are stored.
 *
 * @mathdefinitions <pre>
 * maxDegree(p: polynomial of real numbers) =
 *  if p = 0 then 0
 * else max({ d in Z | coefficient(p, d) ≠ 0 })
 * </pre>
 * @fields rep map from degrees to coefficients
 * @structure Map<Integer, Double>
 * @domain rep.keys = {d in Z | coefficient(this, d) ≠ 0} rep.values =
 *         {coefficient(this, d) | d in rep.keys}
 * @bounds |rep| <= number of non-zero terms in the polynomial
 * @initially this = 0
 * @constraints mutable, finite
 * @strengths Representation is efficient for sparse polynomials with
 *            non-contiguous degrees.
 * @weaknesses Operations that require iterating through all degrees may be less
 *             efficient due to the map structure.
 * @convention Keys (degrees) may be any integer, including negative;
 *             coefficients are non-zero real numbers.
 *
 * @author Jeremy Sun
 *
 * @correspondence this = Σ (rep.get(d) * x^d) for all degrees d in rep.
 */
public class Polynomial1L extends PolynomialSecondary {
    /**
     * Representation of the polynomial.
     */
    private Map<Integer, Double> rep;

    /**
     * Creates a new empty polynomial.
     *
     * @ensures this = 0
     */
    public Polynomial1L() {
        // initialize representation
        this.rep = new Map1L<>();
    }

    @Override
    public final void setCoefficient(int degree, double value) {
        if (value == 0.0) {
            if (this.rep.hasKey(degree)) {
                this.rep.remove(degree);
            }
        } else {
            if (this.rep.hasKey(degree)) {
                this.rep.replaceValue(degree, value);
            } else {
                this.rep.add(degree, value);
            }
        }
    }

    @Override
    public final double getCoefficient(int degree) {
        // default coefficient is 0.0
        double result = 0.0;
        // if the degree exists in the map, get its coefficient
        if (this.rep.hasKey(degree)) {
            result = this.rep.value(degree);
        }
        return result;
    }

    @Override
    public final void removeCoefficient(int degree) {
        // set the coefficient of the specific degree to 0.0
        this.setCoefficient(degree, 0.0);
    }

    @Override
    public final int getDegree() {

        int result = 0; // default for empty polynomial

        boolean first = true;
        for (Map.Pair<Integer, Double> pair : this.rep) {

            if (first) {
                result = pair.key();
                first = false;
            } else {
                int degree = pair.key();
                if (degree > result) {
                    result = degree;
                }
            }
        }

        return result;
    }

    @Override
    public final void clear() {
        this.rep.clear();
    }

    @Override
    public final Polynomial newInstance() {
        // create and return a new Polynomial1L instance
        return new Polynomial1L();
    }

    @Override
    public final void transferFrom(Polynomial source) {
        assert source instanceof Polynomial1L : "Violation of: source is of dynamic type Polynomial1L";

        Polynomial1L local = (Polynomial1L) source;

        // Correct OSU-component transfer of the representation
        this.rep.transferFrom(local.rep);
    }

}
