package components.polynomial;

/**
 * JUnit test fixture for Polynomial1L.
 */
public class Polynomial1LTest extends PolynomialTest {

    @Override
    protected final Polynomial constructorTest() {
        return new Polynomial1L();
    }

    @Override
    protected final Polynomial constructorRef() {

        return new Polynomial1L();
    }
}
