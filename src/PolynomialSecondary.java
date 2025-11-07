public abstract class PolynomialSecondary implements Polynomial {
    @Override
    public Polynomial add(Polynomial p) {
        assert p != null : "Violation of: p is not null";
        Polynomial result = this.newInstance();
        result.clear();
        for (int i = 0; i <= this.getDegree(); i++) {

        }
        return result;
    }
}
