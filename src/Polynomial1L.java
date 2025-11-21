import java.util.Map;

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

    private Map<Integer, Integer> rep;

    /**
     * Creates a new empty polynomial.
     *
     * @ensures this = 0
     */
    public Polynomial1L() {
        // initialize representation
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

    @Override
    public void newPolynomial() {
        // TODO Auto-generated method stub
    }

    @Override
    public void setCoefficient(int degree, double value) {
        // TODO Auto-generated method stub
    }

    @Override
    public double getCoefficient(int degree) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDegree() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Polynomial newInstance() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void transferFrom(Polynomial source) {
        // TODO Auto-generated method stub
    }
}
