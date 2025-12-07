package components.polynomial;

import components.map.Map;
import components.map.Map1L;

/**
 * Kernel implementation for the Polynomial component using a Map-based
 * representation. The keys represent degrees (non-negative integers), and
 * values represent coefficients (real numbers). Duplicate degrees are not
 * allowed, and only non-zero terms are stored.
 *
 * @convention All keys must be non-negative integers; no duplicate keys. All
 *             coefficients must be valid (non-null) real numbers.
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

    /**
     * Clears this polynomial to zero.
     *
     * @ensures this = 0
     *
     */
    @Override
    public final void clear() {
        this.rep.clear();
    }

    /**
     * Creates a new (empty) polynomial.
     *
     * @ensures this = 0
     */
    @Override
    public final void newPolynomial() {
        this.rep = new Map1L<>();
    }

    /**
     * change the known coefficient of the specific degree.
     *
     * @param degree
     *            the target degree of polynomial
     * @param value
     *            the new number of the target polynomial
     */
    @Override
    public final void setCoefficient(int degree, double value) {
        assert degree >= 0 : "Violation of: degree must be non-negative";
        if (value == 0.0) {
            this.rep.remove(degree);
        } else if (this.rep.hasKey(degree)) {
            this.rep.replaceValue(degree, value);
        } else {
            this.rep.add(degree, value);
        }
    }

    /**
     * get the efficient of the known polynomial.
     *
     * @param degree
     *            the specific degree
     * @return the value of specific degree's coefficient
     */
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

    /**
     * remove the coefficient of specific degree.
     *
     * @param degree
     *            the specific degree
     */
    @Override
    public final void removeCoefficient(int degree) {
        // only remove if the degree exists
        if (this.rep.hasKey(degree)) {
            this.rep.remove(degree);
        }
    }

    /**
     * return the highest degree of polynomial.
     *
     * @return the maximum degree of the polynomial
     * @ensures result = maxDegree(this)
     */
    @Override
    public final int getDegree() {
        // initialize maxDegree to -1 (indicating no terms)
        int maxDegree = -1;
        // iterate through all pairs in the map to find the maximum degree
        for (Map.Pair<Integer, Double> pair : this.rep) {
            int degree = pair.key();
            // update maxDegree if current degree is greater
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }

    /**
     * Creates and returns a new instance of Polynomial1L.
     *
     * @return new instance of Polynomial1L
     * @ensures newInstance = 0
     *
     */
    @Override
    public final Polynomial newInstance() {
        // create and return a new Polynomial1L instance
        return new Polynomial1L();
    }

    /**
     * Transfers the contents of the source polynomial to this polynomial.
     *
     * @param source
     *            the source polynomial
     * @ensures this = #source and source = 0
     *
     */
    @Override
    public final void transferFrom(Polynomial source) {
        assert source instanceof Polynomial1L : "Violation of: source is of dynamic type Polynomial1L";

        Polynomial1L local = (Polynomial1L) source;

        // Correct OSU-component transfer of the representation
        this.rep.transferFrom(local.rep);
    }
}
