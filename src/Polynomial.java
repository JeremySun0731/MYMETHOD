import components.map.Map;
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
     *
     * @param args
     */
    public int getCoefficient(int degree) {
        int coefficient = 0;
        if (this.polynomial.hasKey(degree)) {
            coefficient = this.polynomial.value(degree);
        }
        return coefficient;
    }

    /**
     * Main method that excute all of method.
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}