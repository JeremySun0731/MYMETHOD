package components.polynomial;

import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of the {@code Polynomial} component using a sparse
 * {@code Map<Integer, Double>} representation in which each key represents an
 * integer degree (positive, zero, or negative) and each value is a non-zero
 * real coefficient.
 *
 * <p>
 * Only terms with non-zero coefficients are stored. This representation is
 * efficient for sparse polynomials whose degrees may be far apart.
 * </p>
 *
 * <p>
 * The abstraction of this component is defined in {@code Polynomial},
 * {@code PolynomialKernel}, and {@code PolynomialSecondary}. This class
 * provides the concrete data representation and implements the required kernel
 * and secondary operations.
 * </p>
 *
 * @correspondence <pre>
 * this = Σ (rep.get(d) * x^d), ranging over all keys d in rep
 * </pre>
 *
 * @author Jeremy Sun
 */

public class Polynomial1L extends PolynomialSecondary {
    /**
     * Representation of the polynomial.
     */
    private Map<Integer, Double> rep;

    /**
     * No-argument constructor.
     */
    public Polynomial1L() {
        // initialize representation
        this.rep = new Map1L<>();
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
    public final void setCoefficient(int degree, double value) {
        assert value != 0.0 : "Violation: coefficient cannot be zero";
        // set the coefficient of the specific degree to the given value
        if (this.rep.hasKey(degree)) {
            this.rep.replaceValue(degree, value);
        } else {
            this.rep.add(degree, value);
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
        if (this.rep.hasKey(degree)) {
            this.rep.remove(degree);
        }
    }

    @Override
    public final int getDegree() {

        int result = 0; // default for empty polynomial
        // iterate through the map to find the maximum degree
        boolean first = true;
        for (Map.Pair<Integer, Double> pair : this.rep) {
            // on the first iteration, set result to the first key
            if (first) {
                result = pair.key();
                first = false;
            } else {
                // update result if the current key is greater
                int degree = pair.key();
                if (degree > result) {
                    result = degree;
                }
            }
        }

        return result;
    }

    @Override
    public final int getMinDegree() {
        // iterate through the map to find the minimum degree
        int result = 0;
        boolean first = true;
        // initialize result with the first key
        for (Map.Pair<Integer, Double> pair : this.rep) {
            // on the first iteration, set result to the first key
            if (first) {
                result = pair.key();
                first = false;
            } else {
                int degree = pair.key();
                if (degree < result) {
                    result = degree;
                }
            }
        }
        return result;
    }

    @Override
    public final void transferFrom(Polynomial source) {
        assert source instanceof Polynomial1L : "Violation of: source is of dynamic type Polynomial1L";

        Polynomial1L local = (Polynomial1L) source;

        // Correct OSU-component transfer of the representation
        this.rep.transferFrom(local.rep);
    }

}
