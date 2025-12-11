package components.polynomial;

/**
 * An abstract implementation of Polynomial that provides secondary operations
 * such as addition, multiplication, differentiation, and integration.
 */
public abstract class PolynomialSecondary implements Polynomial {
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

    @Override
    public Polynomial derivative() {
        Polynomial result = this.newInstance();
        result.clear();
        // differentiate each term
        for (int degree = this.getMinDegree(); degree <= this
                .getDegree(); degree++) {
            double coef = this.getCoefficient(degree);
            // only differentiate non-zero terms with degree != 0
            if (coef != 0 && degree != 0) {
                double newCoef = coef * degree;
                // only set if newCoef is not zero
                if (newCoef != 0) {
                    result.setCoefficient(degree - 1, newCoef);
                }
            }
        }

        return result;
    }

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

    @Override
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

    @Override
    public boolean isZero() {
        boolean zero = true;
        for (int i = this.getMinDegree(); i <= this.getDegree(); i++) {
            if (this.getCoefficient(i) != 0.0) {
                zero = false;
            }

        }
        return zero;
    }

    @Override
    public Polynomial scale(double c) {
        Polynomial result = this.newInstance();
        result.clear();
        // scale each term
        if (c != 0.0) {
            for (int degree = this.getMinDegree(); degree <= this
                    .getDegree(); degree++) {
                double coef = this.getCoefficient(degree);

                if (coef != 0.0) {
                    double newCoef = coef * c;

                    if (newCoef != 0.0) { // should always be true unless c is 0
                        result.setCoefficient(degree, newCoef);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Returns a string representation of this polynomial.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for (int deg = this.getDegree(); deg >= this.getMinDegree(); deg--) {
            double coef = this.getCoefficient(deg);

            if (coef != 0.0) {

                if (isFirst) {
                    if (coef < 0) {
                        sb.append("-");
                    }
                } else {
                    if (coef < 0) {
                        sb.append(" - ");
                    } else {
                        sb.append(" + ");
                    }
                }

                double absCoef = Math.abs(coef);

                if (deg == 0) {
                    sb.append(absCoef);
                } else {
                    if (absCoef != 1.0) {
                        sb.append(absCoef);
                    }

                    sb.append("x");

                    if (deg > 1) {
                        sb.append("^");
                        sb.append(deg);
                    }
                }

                isFirst = false;
            }
        }
        if (sb.length() == 0) {
            sb.append("0");
        }
        return sb.toString();
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
