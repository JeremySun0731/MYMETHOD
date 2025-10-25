import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;

/**
 *
 */
public class Polynomial {
    /**
     * set a polynomial formula.
     */
    private Map<Integer, Integer> polynomial;

    /**
     * initialize the map.
     */
    public Polynomial() {
        this.polynomial = new Map1L<>();
    }

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
    public void put(int degree, int value) {
        if (!this.polynomial.hasKey(degree)) {
            this.polynomial.add(degree, value);
        }
    }

    /**
     *
     * @param degree
     *            the degree of the formula
     * @param value
     *            the coefficient of the specific degree
     * @update the coefficient degree
     */
    public void setCoefficient(int degree, int value) {
        if (this.polynomial.hasKey(degree)) {
            this.polynomial.replaceValue(degree, value);
        }
    }

    /**
     * Retrieves the coefficient of the term with the specified degree in this
     * polynomial.
     *
     * @param degree
     *            the degree (exponent) of the term to look up, must be a
     *            non-negative integer
     * @return the coefficient of the term with the specified degree, or 0 if
     *         such a term does not exist
     */
    public int getCoefficient(int degree) {
        int coefficient = 0;
        if (this.polynomial.hasKey(degree)) {
            coefficient = this.polynomial.value(degree);
        }
        return coefficient;
    }

    public Polynomial add(Polynomial a) {
        Polynomial result = new Polynomial();
        for (Map.Pair<Integer, Integer> p : this.polynomial) {
            result.put(p.key(), p.value());
        }
        for (Map.Pair<Integer, Integer> p : a.polynomial) {
            int degree = p.key();
            int value = result.getCoefficient(degree);
            result.put(degree, value);
        }
        return result;
    }

    public String toFormulaString() {
        StringBuilder sb = new StringBuilder();
        for (Pair<Integer, Integer> pair : this.polynomial) {
            sb.append(pair.value()).append("x^").append(pair.key()).append(" ");
        }
        return sb.toString();
    }

    /**
     * Main method that excute all of method.
     *
     * @param args
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.put(3, 3);
        p1.put(2, 2);
        System.out.println(p1);
    }
}