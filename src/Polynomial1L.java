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

    @Override
    public final void clear() {
        this.rep.clear();
    }

    @Override
    public final void newPolynomial() {
        this.rep = new Map1L<>();
    }

    @Override
    public final void setCoefficient(int degree, double value) {
        if (value == 0.0) {
            this.rep.remove(degree);
        } else if (this.rep.hasKey(degree)) {
            this.rep.replaceValue(degree, value);
        } else {
            this.rep.add(degree, value);
        }
    }

    @Override
    public final double getCoefficient(int degree) {
        double result = 0.0;
        if (this.rep.hasKey(degree)) {
            result = this.rep.value(degree);
        }
        return result;
    }

    @Override
    public final int getDegree() {
        int maxDegree = -1;
        for (Map.Pair<Integer, Double> pair : this.rep) {
            int degree = pair.key();
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }

    @Override
    public final Polynomial newInstance() {
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
