package components.polynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test fixture for Polynomial1L.
 */
public abstract class PolynomialTest {
    /**
     * Creates and returns a new polynomial instance of the class being tested.
     *
     * @return new polynomial instance of the class being tested
     */
    protected abstract Polynomial constructorTest();

    /**
     * Creates and returns a new polynomial instance of the reference class.
     *
     * @return new polynomial instance of the reference class
     */
    protected abstract Polynomial constructorRef();

    // --------------------------------------
    // Test 1: Constructor
    // --------------------------------------
    /**
     * Test the constructor.
     */
    @Test
    public void testConstructor() {
        Polynomial p = this.constructorTest();

        // Newly constructed polynomial should be zero polynomial
        assertEquals(0, p.getDegree());
        assertEquals(0.0, p.getCoefficient(0), 0.0001);
    }

    /**
     * Ensure constructorTest() and constructorRef() match.
     */
    @Test
    public final void testConstructorEqualsReference() {
        // Ensure constructorTest() and constructorRef() produce equal polynomials
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.constructorRef();
        // Newly constructed polynomials should be equal
        assertEquals(pRef, pTest);
        assertEquals(0, pTest.getDegree());
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
    }

    // --------------------------------------
    // Test 2: clear()
    // --------------------------------------
    /**
     * Helper to create a polynomial given degree-coefficient pairs. Example:
     * createPoly(0, 5, 2, 3) creates 5 + 3x^2
     */
    private Polynomial createPoly(Object... args) {
        assert args.length
                % 2 == 0 : "Arguments must be degree-coefficient pairs";

        Polynomial p = this.constructorTest();

        for (int i = 0; i < args.length; i += 2) {
            int degree = (int) args[i];
            double coef = ((Number) args[i + 1]).doubleValue();
            p.setCoefficient(degree, coef);
        }
        return p;
    }

    /**
     * Test clear on empty polynomial.
     */
    @Test
    public final void testClearEmpty() {
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.constructorRef();

        pTest.clear();
        assertEquals(pRef, pTest);
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertTrue(pTest.getDegree() == 0);
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
        assertTrue(pTest.getCoefficient(0) == 0.0);
    }

    /**
     * Test clear on non-empty polynomial.
     */
    @Test
    public final void testClearNonEmpty() {
        // Create empty polynomial
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.constructorRef();
        // Set some coefficients in pTest
        pTest.setCoefficient(2, 3.0);
        pTest.setCoefficient(0, 5.0);
        pTest.clear();
        assertEquals(pRef, pTest);
    }

    /**
     * Test newPolynomial without using setCoefficient.
     */
    @Test
    public final void testNewPolynomialDefault() {
        // Create empty polynomial
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.constructorRef();

        // Both are already empty, but call newPolynomial to reset again
        pTest.newPolynomial();
        pRef.newPolynomial();
        // clear pTest to ensure they are zero polynomials
        pTest.clear();
        assertEquals(pRef, pTest);
    }

    /**
     * Test getDegree on zero polynomial.
     */
    @Test
    public final void testGetDegreeMultipleTerms() {
        // Create polynomial 5 + 3x^2
        Polynomial pTest = this.createPoly(0, 5, 2, 3); // 5 + 3x^2
        Polynomial pRef = this.constructorRef();
        pTest.clear();
        assertEquals(pRef, pTest);
        // Both should be zero polynomial
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertTrue(pRef.getDegree() == 0);
        // Degree should be 0
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
    }

    // --------------------------------------
    // Test 3: newPolynomial()
    // --------------------------------------
    /**
     * Test newPolynomial on a non-empty polynomial.
     */
    @Test
    public final void testNewPolynomialNonEmpty() {
        // Create polynomial 5 + 3x^2
        Polynomial pTest = this.createPoly(0, 5, 2, 3); // 5 + 3x^2
        Polynomial pRef = this.constructorRef();

        // Create empty polynomial for reference
        pTest.newPolynomial();
        // Both should be zero polynomial
        assertEquals(pRef, pTest);
        // Degree should be equal
        assertEquals(pRef.getDegree(), pTest.getDegree());
        // Degree should be 0
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
        assertEquals(pRef.getCoefficient(0) == 0.0,
                pTest.getCoefficient(0) == 0.0);
        // Verify equality of degrees
        assertTrue(pTest.getDegree() == 0);
    }

    /**
     * Test newPolynomial on an already empty polynomial.
     */
    @Test
    public final void testNewPolynomialEmpty() {
        // Create empty polynomial
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.constructorRef();
        // Both are already empty, but call newPolynomial to reset again
        pTest.newPolynomial();
        // Both should be zero polynomial
        assertEquals(pRef, pTest);
        // Degree should be 0
        assertEquals(pRef.getDegree(), pTest.getDegree());
        // Coefficient of degree 0 should be equal
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
        assertEquals(pRef.getCoefficient(0) == 0.0,
                pTest.getCoefficient(0) == 0.0);
        // verify equality of degrees
        assertTrue(pTest.getDegree() == 0);
    }

    // --------------------------------------
    // Test 3: setCoefficient()
    // --------------------------------------
    /**
     * Test setCoefficient: add positive single new term.
     */
    @Test
    public final void testSetCoefficientAddSingle() {
        // Create empty polynomial
        Polynomial pTest = this.constructorTest();
        // Create reference polynomial 3.1x^4
        Polynomial pRef = this.createPoly(4, 3.1);

        // Add new term: 3x^4
        pTest.setCoefficient(4, 3.1);
        // Test whole polynomial are equal
        assertEquals(pRef, pTest);
        // Degree should be 4
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertTrue(pTest.getDegree() == 4);
        // Check coefficient
        assertEquals(pRef.getCoefficient(4), pTest.getCoefficient(4), 0.0001);
        assertTrue(pTest.getCoefficient(4) == 3.1);
    }

    /**
     * Test setCoefficient: add Negative single new term.
     */
    @Test
    public final void testSetCoefficientAddNegativeSingle() {
        // Create empty polynomial
        Polynomial pTest = this.constructorTest();
        // Create reference polynomial -3.1x^4
        Polynomial pRef = this.createPoly(4, -3.1);

        // Add new term: -3.1x^4
        pTest.setCoefficient(4, -3.1);
        // Test whole polynomial are equal
        assertEquals(pRef, pTest);
        // Degree should be 4
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertTrue(pTest.getDegree() == 4);
        // Check coefficient
        assertEquals(pRef.getCoefficient(4), pTest.getCoefficient(4), 0.0001);
        assertTrue(pTest.getCoefficient(4) == -3.1);
    }

    /**
     * Test setCoefficient: add multiple new terms.
     */
    @Test
    public final void testSetCoefficientAddMore() {
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.createPoly(4, 3.1, 3, 2.5, 2, 9.1);

        // Add new term: 3x^4
        pTest.setCoefficient(4, 3.1);
        // Add new term: 2.5x^3
        pTest.setCoefficient(3, 2.5);
        // Add new term: 9.1x^2
        pTest.setCoefficient(2, 9.1);
        assertEquals(pRef, pTest);
        // Degree should be 4
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertTrue(pTest.getDegree() == 4);
        // Check coefficients
        assertEquals(pRef.getCoefficient(4), pTest.getCoefficient(4), 0.0001);
        assertEquals(pRef.getCoefficient(3), pTest.getCoefficient(3), 0.0001);
        assertEquals(pRef.getCoefficient(2), pTest.getCoefficient(2), 0.0001);
        assertTrue(pTest.getCoefficient(4) == 3.1);
        assertTrue(pTest.getCoefficient(3) == 2.5);
        assertTrue(pTest.getCoefficient(2) == 9.1);
    }

    /**
     * Test setCoefficient: add negative multiple new terms.
     */
    @Test
    public final void testSetCoefficientNagetiveAddMore() {
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.createPoly(4, -3.1, 3, -2.5, 2, -9.1);

        // Add new term: -3.1x^4
        pTest.setCoefficient(4, -3.1);
        // Add new term: -2.5x^3
        pTest.setCoefficient(3, -2.5);
        // Add new term: -9.1x^2
        pTest.setCoefficient(2, -9.1);
        assertEquals(pRef, pTest);
        // Degree should be 4
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertTrue(pTest.getDegree() == 4);
        // Check coefficients
        assertEquals(pRef.getCoefficient(4), pTest.getCoefficient(4), 0.0001);
        assertEquals(pRef.getCoefficient(3), pTest.getCoefficient(3), 0.0001);
        assertEquals(pRef.getCoefficient(2), pTest.getCoefficient(2), 0.0001);
        assertTrue(pTest.getCoefficient(4) == -3.1);
        assertTrue(pTest.getCoefficient(3) == -2.5);
        assertTrue(pTest.getCoefficient(2) == -9.1);
    }

    /**
     * Test setCoefficient: replace existing single term.
     */
    @Test
    public final void testSetCoefficientReplaceSingle() {
        // Create initial polynomial: 5x^2
        Polynomial pTest = this.createPoly(2, 5.0);
        Polynomial pRef = this.createPoly(2, 9.0); // After replacing, should be 9x^2

        // Replace coef of x^2: 5 -> 9
        pTest.setCoefficient(2, 9.0);

        // Test whole polynomial
        assertEquals(pRef, pTest);

        // Degree stays 2
        assertEquals(2, pTest.getDegree());
        assertEquals(pRef.getDegree(), pTest.getDegree());
        // Check coefficient
        assertEquals(pRef.getCoefficient(2), pTest.getCoefficient(2), 0.0001);
        // Extra: double-check with assertTrue
        assertTrue(pTest.getCoefficient(2) == 9.0);
    }

    /**
     * Test setCoefficient: replace existing single term.
     */
    @Test
    public final void testSetCoefficientReplaceNegativeSingle() {
        // Create initial polynomial: 5x^2
        Polynomial pTest = this.createPoly(2, 5.0);
        Polynomial pRef = this.createPoly(2, -9.0); // After replacing, should be 9x^2

        // Replace coef of x^2: 5 -> 9
        pTest.setCoefficient(2, -9.0);

        // Test whole polynomial
        assertEquals(pRef, pTest);

        // Degree stays 2
        assertEquals(2, pTest.getDegree());
        assertEquals(pRef.getDegree(), pTest.getDegree());
        // Check coefficient
        assertEquals(pRef.getCoefficient(2), pTest.getCoefficient(2), 0.0001);
        // Extra: double-check with assertTrue
        assertTrue(pTest.getCoefficient(2) == -9.0);
    }

    /**
     * Test setCoefficient: replace highest degree term.
     */
    @Test
    public final void testSetCoefficientReplaceHighest() {
        // Initial: 3x^4 + 2x^1
        Polynomial pTest = this.createPoly(4, 3.0, 1, 2.0);
        // Expected after change: 7x^4 + 2x^1
        Polynomial pRef = this.createPoly(4, 7.0, 1, 2.0);

        // Replace highest degree coefficient
        pTest.setCoefficient(4, 7.0);

        // Compare complete polynomial
        assertEquals(pRef, pTest);

        // Degree must remain 4
        assertEquals(4, pTest.getDegree());
        assertEquals(pRef.getDegree(), pTest.getDegree());
        // Check coefficients
        assertEquals(pRef.getCoefficient(4), pTest.getCoefficient(4), 0.0001);
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertTrue(pTest.getCoefficient(4) == 7.0);
        assertTrue(pTest.getCoefficient(1) == 2.0);
    }

    /**
     * Test setCoefficient: delete a term when value = 0.
     */
    @Test
    public final void testSetCoefficientDeleteTerm() {

        // Create polynomial: 3x^4 + 2.5x^3 + 9.1x^2
        Polynomial pTest = this.createPoly(4, 3.1, 3, 2.5, 2, 9.1);

        // Reference polynomial after deleting x^3 term
        Polynomial pRef = this.createPoly(4, 3.1, 2, 9.1); // Removed degree=3

        // Remove term: 2.5x^3 -> 0
        pTest.setCoefficient(3, 0.0);

        // Compare entire polynomial
        assertEquals(pRef, pTest);

        // Degree should now be 4 (unchanged)
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(4, pTest.getDegree());

        // Check remaining coefficients
        assertEquals(pRef.getCoefficient(4), pTest.getCoefficient(4), 0.0001);
        assertEquals(pRef.getCoefficient(2), pTest.getCoefficient(2), 0.0001);

        // Ensure removed coefficient is 0
        assertEquals(pRef.getCoefficient(3), pTest.getCoefficient(3), 0.0001);
        assertTrue(pTest.getCoefficient(3) == 0.0);
    }

}