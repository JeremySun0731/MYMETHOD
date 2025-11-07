public abstract class PolynomialSecondary implements Polynomial {
    /**
     * Returns a new polynomial that is the sum of this polynomial and p.
     *
     * @param p
     *            the polynomial to add; must not be null
     * @return a new Polynomial that is the result of this + p
     */
    @Override
    public Polynomial add(Polynomial p) {
        assert p != null : "Violation of: p is not null";
        Polynomial result = this.newInstance();
        result.clear();
        //add this to the result
        for (int i = 0; i <= this.getDegree(); i++) {
            int coefic = this.getCoefficient(i);
            if (coefic != 0) {
                result.setCoefficient(i, coefic);
            }
        }

        for (int i = 0; i <= p.getDegree(); i++) {
            int coefic = p.getCoefficient(i);
            if (coefic != 0) {
                int sum = result.getCoefficient(i) + coefic;
                result.setCoefficient(i, sum);
            }
        }
        return result;
    }

    /**
     * Multiplies this polynomial with polynomial p and returns the result.
     *
     * @param p
     *            the polynomial to multiply with (must not be null)
     * @return a new polynomial that is the product of this and p
     */
    @Override
    public Polynomial multiply(Polynomial p) {
        assert p != null : "Violation of: p is not null";
        Polynomial result = this.newInstance();
        result.clear();
        for (int i = 0; i <= this.getDegree(); i++) {
            int coefic = this.getCoefficient(i);
            if (coefic != 0) {
                for (int j = 0; j < p.getCoefficient(j); j++) {
                    int coef1 = p.getCoefficient(j);
                    if (coef1 != 0) {
                        int degree = i + j;
                        int newCoefficient = result.getCoefficient(degree)
                                + coefic * coef1;
                        result.setCoefficient(degree, newCoefficient);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Returns the derivative of this polynomial as a new polynomial.
     *
     * @return a new polynomial that is the derivative of this one
     */
    @Override
    public Polynomial derivative() {
        assert this != null : "Violation of: p is not null";
        Polynomial result = this.newInstance();
        result.clear();
        for (int i = 1; i < this.getDegree(); i++) {
            int coeffi = this.getCoefficient(i);
            if (coeffi != 0) {
                result.setCoefficient(i - 1, i * coeffi);
            }
        }
        return result;
    }

    /**
     * Returns the indefinite integral of this polynomial as a new polynomial.
     * The constant of integration is assumed to be 0.
     *
     * @return a new polynomial that is the integral of this one
     */
    @Override
    public Polynomial integrate() {
        assert this != null : "Violation of: p is not null";
        Polynomial result = this.newInstance();
        result.clear();
        for (int i = 0; i < this.getDegree(); i++) {
            int coeffi = this.getCoefficient(i);
            if (coeffi != 0) {
                result.setCoefficient(i + 1, 1 / (i * coeffi));
            }
        }
        return result;
    }
}
