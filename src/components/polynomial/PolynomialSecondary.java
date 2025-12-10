package components.polynomial;

/**
 * An abstract implementation of Polynomial that provides secondary operations
 * such as addition, multiplication, differentiation, and integration.
 */
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

        //add p to this
        for (int i = this.getMinDegree(); i <= this.getDegree(); i++) {
            double coef = this.getCoefficient(i);
            if (coef != 0.0) {
                result.setCoefficient(i, coef);
            }
        }

        // add p's coefficients
        for (int i = p.getMinDegree(); i <= p.getDegree(); i++) {
            double coef = p.getCoefficient(i);
            if (coef != 0.0) {
                double newCoef = result.getCoefficient(i) + coef;
                if (newCoef == 0.0) {
                    result.removeCoefficient(i);
                } else {
                    result.setCoefficient(i, newCoef);
                }
            }
        }

        return result;
    }

    /**
     * Returns a new polynomial that is the difference of this polynomial and p.
     *
     * @param p
     *            the polynomial to subtract; must not be null
     * @return a new Polynomial that is the result of this - p
     */
    @Override
    public Polynomial subtract(Polynomial p) {
        assert p != null : "Violation of: p is not null";

        Polynomial result = this.newInstance();
        result.clear();

        for (int i = this.getMinDegree(); i <= this.getDegree(); i++) {
            double coef = this.getCoefficient(i);
            if (coef != 0.0) {
                result.setCoefficient(i, coef);
            }
        }

        for (int i = p.getMinDegree(); i <= p.getDegree(); i++) {
            double coefP = p.getCoefficient(i);
            if (coefP != 0.0) {

                double newValue = result.getCoefficient(i) - coefP;

                if (newValue == 0.0) {
                    result.removeCoefficient(i);
                } else {
                    result.setCoefficient(i, newValue);
                }
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

        for (int i = this.getMinDegree(); i <= this.getDegree(); i++) {
            double a = this.getCoefficient(i);
            if (a != 0.0) {

                for (int j = p.getMinDegree(); j <= p.getDegree(); j++) {
                    double b = p.getCoefficient(j);
                    if (b != 0.0) {

                        int degree = i + j;
                        double newValue = result.getCoefficient(degree) + a * b;

                        if (newValue == 0.0) {
                            result.removeCoefficient(degree);
                        } else {
                            result.setCoefficient(degree, newValue);
                        }
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
        Polynomial result = this.newInstance();
        result.clear();
        // differentiate each term
        for (int degree = this.getMinDegree(); degree <= this
                .getDegree(); degree++) {
            double coef = this.getCoefficient(degree);
            // only differentiate non-zero terms with degree > 0
            if (coef != 0 && degree > 0) {
                double newCoef = coef * degree;
                // only set if newCoef is not zero
                if (newCoef != 0) {
                    result.setCoefficient(degree - 1, newCoef);
                }
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
        Polynomial result = this.newInstance();
        result.clear();
        // integrate each term
        for (int degree = this.getMinDegree(); degree <= this
                .getDegree(); degree++) {

            double coef = this.getCoefficient(degree);
            // only integrate non-zero terms
            if (coef != 0.0) {

                // integral undefined for x^-1 term
                if (degree == -1) {
                    throw new ArithmeticException(
                            "Integral undefined for x^-1 term (would require ln|x|).");
                }
                // compute new coefficient
                double newCoef = coef / (degree + 1);
                // only set if newCoef is not zero
                if (newCoef != 0.0) {
                    result.setCoefficient(degree + 1, newCoef);
                }
            }
        }

        return result;
    }

    /**
     * Evaluates this polynomial at the given value of x.
     *
     * @param x
     *            the value at which to evaluate this polynomial
     * @return the result of f(x)
     */
    public double evaluate(double x) {
        double result = 0.0;

        for (int degree = this.getMinDegree(); degree <= this
                .getDegree(); degree++) {
            double coef = this.getCoefficient(degree);
            if (coef != 0.0) {
                result += coef * Math.pow(x, degree);
            }
        }

        return result;
    }

    /**
     * check whether the polynomial is zero polynomial.
     *
     * @return true if the polynomial is zero polynomial, false otherwise
     */
    public boolean isZero() {
        boolean zero = true;
        for (int i = this.getMinDegree(); i <= this.getDegree(); i++) {
            if (this.getCoefficient(i) != 0.0) {
                zero = false;
            }

        }
        return zero;
    }

    /**
     * Returns a new polynomial that is this polynomial scaled by the constant
     * c.
     *
     * @param c
     *            the constant to scale by
     * @return a new Polynomial that is c * this
     */
    public Polynomial scale(double c) {
        Polynomial result = this.newInstance();
        result.clear();

        for (int degree = this.getMinDegree(); degree <= this
                .getDegree(); degree++) {
            double coef = this.getCoefficient(degree);
            if (coef != 0.0) {
                result.setCoefficient(degree, coef * c);
            }
        }

        return result;
    }

    /**
     * Returns a string representation of the polynomial.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean firstTerm = true;
        for (int i = this.getDegree(); i >= this.getMinDegree(); i--) {
            double coeff = this.getCoefficient(i);
            if (coeff != 0) {
                if (!firstTerm) {
                    sb.append(coeff > 0 ? " + " : " - ");
                } else if (coeff < 0) {
                    sb.append("-");
                }
                double absCoeff = Math.abs(coeff);
                if (i == 0 || absCoeff != 1) {
                    sb.append(absCoeff);
                }
                if (i > 0) {
                    sb.append("x");
                    if (i > 1) {
                        sb.append("^").append(i);
                    }
                }
                firstTerm = false;
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * Compares this polynomial to the specified object for equality.
     *
     * @param obj
     *            the object to compare with
     * @return true if the specified object is a polynomial equal to this one,
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = true;

        if (this == obj) {
            result = true;
        } else if (!(obj instanceof Polynomial other)) {
            result = false;
        } else {
            // compare degree ranges
            if (this.getDegree() != other.getDegree()
                    || this.getMinDegree() != other.getMinDegree()) {
                result = false;
            } else {
                final double EPS = 1e-9;

                for (int i = this.getMinDegree(); i <= this.getDegree()
                        && result; i++) {
                    double a = this.getCoefficient(i);
                    double b = other.getCoefficient(i);
                    if (Math.abs(a - b) > EPS) {
                        result = false;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Returns the hash code for this polynomial.
     */
    @Override
    public int hashCode() {
        int hash = 1;

        for (int i = this.getMinDegree(); i <= this.getDegree(); i++) {
            double coeff = this.getCoefficient(i);
            hash = 31 * hash + Double.hashCode(coeff);
        }

        return hash;
    }

}
