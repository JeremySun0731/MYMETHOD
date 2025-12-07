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
        //add this to the result
        for (int i = 0; i <= this.getDegree(); i++) {
            double coefic = this.getCoefficient(i);
            if (coefic != 0) {
                result.setCoefficient(i, coefic);
            }
        }

        for (int i = 0; i <= p.getDegree(); i++) {
            double coefic = p.getCoefficient(i);
            if (coefic != 0) {
                double sum = result.getCoefficient(i) + coefic;
                result.setCoefficient(i, sum);
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
        //subtract p from this
        for (int i = 0; i <= p.getDegree(); i++) {
            double coefic = this.getCoefficient(i);
            if (coefic != 0) {
                result.setCoefficient(i, coefic);
            }
        }

        for (int i = 0; i <= p.getDegree(); i++) {
            double coefic = p.getCoefficient(i);
            if (coefic != 0) {
                double difference = result.getCoefficient(i) - coefic;
                result.setCoefficient(i, difference);
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
            double coefic = this.getCoefficient(i);
            if (coefic != 0) {
                for (int j = 0; j <= p.getDegree(); j++) {
                    double coef1 = p.getCoefficient(j);
                    if (coef1 != 0) {
                        int degree = i + j;
                        double newCoefficient = result.getCoefficient(degree)
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
        Polynomial result = this.newInstance();
        result.clear();
        for (int i = 1; i <= this.getDegree(); i++) {
            double coeffi = this.getCoefficient(i);
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
        for (int i = 0; i <= this.getDegree(); i++) {
            double coeffi = this.getCoefficient(i);
            if (coeffi != 0) {
                result.setCoefficient(i + 1, coeffi / (i + 1));
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

        for (int degree = 0; degree <= this.getDegree(); degree++) {
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
        for (int i = 0; i <= this.getDegree(); i++) {
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

        for (int degree = 0; degree <= this.getDegree(); degree++) {
            double coef = this.getCoefficient(degree);
            if (coef != 0.0) {
                result.setCoefficient(degree, coef * c);
            }
        }

        return result;
    }

    /**
     * Creates and returns a new instance of the polynomial type.
     *
     * @return a new instance of the polynomial type
     */
    @Override
    public abstract Polynomial newInstance();

    /**
     * Returns a string representation of the polynomial.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean firstTerm = true;
        for (int i = this.getDegree(); i >= 0; i--) {
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
            // compare degrees
            if (this.getDegree() != other.getDegree()) {
                result = false;
            } else {
                // compare coefficients
                for (int i = 0; i <= this.getDegree() && result; i++) {
                    if (Double.compare(this.getCoefficient(i),
                            other.getCoefficient(i)) != 0) {
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

        for (int i = 0; i <= this.getDegree(); i++) {
            double coeff = this.getCoefficient(i);
            hash = 31 * hash + Double.hashCode(coeff);
        }

        return hash;
    }
}
