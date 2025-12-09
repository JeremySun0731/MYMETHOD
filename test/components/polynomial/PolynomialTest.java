package components.polynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
     * Test clear on polynomial with multiple terms.
     */
    @Test
    public final void testClearMultipleTerms() {
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

    /**
     * Test clear on polynomial with multiple terms.
     */
    @Test
    public final void testClearMultipleTerms1() {
        // Create polynomial 5 + 3x^2
        Polynomial pTest = this.createPoly(0, -5, 2, -+3); // 5 + 3x^2
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
    // Test 4: setCoefficient()
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
        assertFalse(pTest == pRef);
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
        assertFalse(pTest == pRef);
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
     * Test setCoefficient: replace positive single term to negative one.
     */
    @Test
    public final void testSetCoefficientReplacePositiveSingle() {
        // Create initial polynomial: 5x^2
        Polynomial pTest = this.createPoly(2, 5.0);
        Polynomial pRef = this.createPoly(2, -9.0); // After replacing, should be 9x^2

        // Replace coef of x^2: 5 -> -9
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
     * Test setCoefficient: replace negative single term to positive one.
     */
    @Test
    public final void testSetCoefficientReplaceNegativeSingle() {
        // Create initial polynomial: 5x^2
        Polynomial pTest = this.createPoly(2, -5.0);
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
     * Test setCoefficient: delete a term when value = 0 in the polynomial.
     */
    @Test
    public final void testSetCoefficientDeleteTerm() {

        // Create polynomial: 3x^4 + 2.5x^3 + 9.1x^2
        Polynomial pTest = this.createPoly(4, 3.1, 3, 2.5, 2, 9.1);

        // Reference polynomial after deleting x^3 term
        Polynomial pRef = this.createPoly(4, 3.1, 2, 9.1); // Removed degree=3
        assertFalse(pTest == pRef);
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

    /**
     * Test setCoefficient: delete multiple terms when value = 0.
     */
    @Test
    public final void testSetCoefficientDeleteMultipleTerm() {

        // Create original polynomial: 3x^4 + 2.5x^3 + 9.1x^2
        Polynomial pTest = this.createPoly(4, -3.1, 3, -2.5, 2, 9.1);

        // Reference polynomial after deleting x^3 and x^4 terms → left with 9.1x^2
        Polynomial pRef = this.createPoly(2, 9.1);
        assertFalse(pTest == pRef);
        assertTrue(pTest.getCoefficient(2) == 9.1);
        assertTrue(pTest.getCoefficient(3) == -2.5);
        assertTrue(pTest.getCoefficient(4) == -3.1);
        // Remove terms
        pTest.setCoefficient(3, 0.0); // Remove 2.5x^3
        pTest.setCoefficient(4, 0.0); // Remove 3x^4
        // Compare full polynomial
        assertEquals(pRef, pTest);

        // Ensure removed terms are gone
        assertEquals(0.0, pTest.getCoefficient(3), 0.0001);
        assertEquals(0.0, pTest.getCoefficient(4), 0.0001);

        // Ensure remaining term is correct
        assertEquals(9.1, pTest.getCoefficient(2), 0.0001);

        // Ensure removed terms are gone
        assertTrue(pTest.getCoefficient(3) == 0.0);
        assertTrue(pTest.getCoefficient(4) == 0.0);
        assertTrue(pTest.getCoefficient(2) == 9.1);
    }

    /**
     * Test setCoefficient: delete a single existing term when value = 0.
     */
    @Test
    public final void testSetCoefficientDeleteSingle() {

        // Create original polynomial: -9.1x^2
        Polynomial pTest = this.createPoly(2, -9.1);

        // Reference polynomial after deleting x^2 → zero polynomial
        Polynomial pRef = this.createPoly(); // same as constructorRef()

        // Ensure original coefficient is correct
        assertEquals(-9.1, pTest.getCoefficient(2), 0.0001);

        // Delete the term
        pTest.setCoefficient(2, 0.0);

        // Full polynomial equality check
        assertEquals(pRef, pTest);

        // Coefficient for degree 2 should now be 0
        assertEquals(0.0, pTest.getCoefficient(2), 0.0001);

        // Degree of zero polynomial must be 0
        assertEquals(0, pTest.getDegree());
    }
    // --------------------------------------
    // Test 5: getCoefficient()
    // --------------------------------------

    /**
     * Test getCoefficient: existing degrees.
     */
    @Test
    public final void testGetCoefficientExists() {

        // Create polynomial: 2.5x^3 - 4.2x^1 - 1.0x^-1
        Polynomial pTest = this.createPoly(3, 2.5, 1, -4.2, -1, -1.0);
        Polynomial pRef = this.createPoly(3, 2.5, 1, -4.2, -1, -1.0);

        // x^3 term
        assertEquals(pRef.getCoefficient(3), pTest.getCoefficient(3), 0.0001);
        assertTrue(pTest.getCoefficient(3) == 2.5);

        // x^1 term
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertTrue(pTest.getCoefficient(1) == -4.2);

        // x^-1 term
        assertEquals(pRef.getCoefficient(-1), pTest.getCoefficient(-1), 0.0001);
        assertTrue(pTest.getCoefficient(-1) == -1.0);

        // implicit zero constant term
        assertEquals(0.0, pTest.getCoefficient(0), 0.0001);
        assertTrue(pTest.getCoefficient(0) == 0.0);
    }

    /**
     * Test getCoefficient: non-existing degree.
     */
    @Test
    public final void testGetCoefficientNotExists() {

        // Create polynomial: only 2.5x^3 term
        Polynomial pTest = this.createPoly(3, 2.5);
        Polynomial pRef = this.createPoly(3, 2.5);

        // degree 1 does not exist
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertTrue(pTest.getCoefficient(1) == 0.0);

        // degree -2 does not exist
        assertEquals(pRef.getCoefficient(-2), pTest.getCoefficient(-2), 0.0001);
        assertTrue(pTest.getCoefficient(-2) == 0.0);

        // degree 100 does not exist
        assertEquals(pRef.getCoefficient(100), pTest.getCoefficient(100),
                0.0001);
        assertTrue(pTest.getCoefficient(100) == 0.0);

        // degree 0 constant term does not exist
        assertEquals(0.0, pTest.getCoefficient(0), 0.0001);
        assertTrue(pTest.getCoefficient(0) == 0.0);
    }

    /**
     * Test getCoefficient: degree = 0 edge case.
     */
    @Test
    public final void testGetCoefficientDegreeZero() {

        // Case 1: zero polynomial
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.createPoly(0, 5.7);

        // since pTest is zero polynomial, coefficient(0) must be 0
        assertEquals(0.0, pTest.getCoefficient(0), 0.0001);
        assertTrue(pTest.getCoefficient(0) == 0.0);

        // degree for zero polynomial must be 0
        assertEquals(0, pTest.getDegree());
        assertTrue(pTest.getDegree() == 0);

        // Case 2: non-zero constant term (ref = (0, 5.7))
        pTest.setCoefficient(0, 5.7);

        // compare with the *reference polynomial* you fixed above
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
        assertTrue(pTest.getCoefficient(0) == 5.7);

        // degree should remain 0 when only constant term exists
        assertEquals(0, pTest.getDegree());
        assertTrue(pTest.getDegree() == 0);

        // ensure no other degrees exist
        assertTrue(pTest.getCoefficient(1) == 0.0);
        assertTrue(pTest.getCoefficient(-1) == 0.0);
    }

    // --------------------------------------
    // Test 6: removeCoefficient()
    // --------------------------------------
    /**
     * Test removeCoefficient: remove an existing term.
     */
    @Test
    public final void testRemoveCoefficientExists() {

        // Create polynomial: 4.5x^3 + 2.2x^1
        Polynomial pTest = this.createPoly(3, 4.5, 1, 2.2);
        Polynomial pRef = this.createPoly(1, 2.2); // reference after removing x^3

        // remove existing degree 3 term
        pTest.removeCoefficient(3);

        // compare full polynomial
        assertEquals(pRef, pTest);

        // degree should update from 3 -> 1
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertTrue(pTest.getDegree() == 1);

        // removed coefficient should be 0
        assertEquals(0.0, pTest.getCoefficient(3), 0.0001);
        assertTrue(pTest.getCoefficient(3) == 0.0);

        // remaining term should match
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertTrue(pTest.getCoefficient(1) == 2.2);
    }

    /**
     * Test removeCoefficient: remove a non-existing term.
     */
    @Test
    public final void testRemoveCoefficientNotExists() {

        // Create polynomial: 7.7x^4
        Polynomial pTest = this.createPoly(4, 7.7);
        Polynomial pRef = this.createPoly(4, 7.7); // unchanged

        // remove term that does not exist
        pTest.removeCoefficient(3); // degree 3 does not exist

        // polynomial should remain unchanged
        assertEquals(pRef, pTest);

        // degree should remain 4
        assertEquals(4, pTest.getDegree());
        assertTrue(pTest.getDegree() == 4);

        // coefficient(3) should be 0 after remove attempt
        assertEquals(0.0, pTest.getCoefficient(3), 0.0001);
        assertTrue(pTest.getCoefficient(3) == 0.0);

        // coefficient(4) must remain the same
        assertEquals(7.7, pTest.getCoefficient(4), 0.0001);
        assertTrue(pTest.getCoefficient(4) == 7.7);
    }

    // --------------------------------------
    // Test 7: getDegree()
    // --------------------------------------
    /**
     * Test getDegree: empty polynomial.
     */
    @Test
    public final void testGetDegreeEmpty() {

        // Create empty polynomial
        Polynomial pTest = this.constructorTest();
        Polynomial pRef = this.constructorRef();

        // degree should be 0
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(0, pTest.getDegree());
        assertTrue(pTest.getDegree() == 0);
    }

    /**
     * Test getDegree: non-empty polynomial.
     */
    @Test
    public final void testGetDegreeNonEmpty() {
        // Create polynomial: 3x^5 + 2x^2 + 1
        Polynomial pTest = this.createPoly(5, 3.0, 2, 2.0, 0, 1.0);
        Polynomial pRef = this.createPoly(5, 3.0, 2, 2.0, 0, 1.0);

        // degree should be 5
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(5, pTest.getDegree());
        assertTrue(pTest.getDegree() == 5);
    }

    /**
     * Test getDegree: polynomial with mixed positive and negative degrees.
     */
    @Test
    public final void testGetDegreeMixed() {

        // Create polynomial: 7x^5 - 3x^2 + 4 + 2x^-1
        Polynomial pTest = this.createPoly(5, 7.0, 2, -3.0, 0, 4.0, -1, 2.0);
        Polynomial pRef = this.createPoly(5, 7.0, 2, -3.0, 0, 4.0, -1, 2.0);

        // degree should be the max degree = 5
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(5, pTest.getDegree());
        assertTrue(pTest.getDegree() == 5);
    }

    /**
     * Test getDegree: polynomial with all positive degrees.
     */
    @Test
    public final void testGetDegreeAllPositive() {

        // Create polynomial: 4x^7 + 1.2x^3 + 9x^1
        Polynomial pTest = this.createPoly(7, 4.0, 3, 1.2, 1, 9.0);
        Polynomial pRef = this.createPoly(7, 4.0, 3, 1.2, 1, 9.0);

        // degree should be the max degree = 7
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(7, pTest.getDegree());
        assertTrue(pTest.getDegree() == 7);
    }

    /**
     * Test getDegree: polynomial with all negative degrees.
     */
    @Test
    public final void testGetDegreeAllNegative() {

        // Create polynomial: -5x^-3 + 2x^-7 + 1.1x^-1
        Polynomial pTest = this.createPoly(-3, -5.0, -7, 2.0, -1, 1.1);
        Polynomial pRef = this.createPoly(-3, -5.0, -7, 2.0, -1, 1.1);

        // degree should be the max degree = -1 (largest integer)
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(-1, pTest.getDegree());
        assertTrue(pTest.getDegree() == -1);
    }

    /**
     * Test getDegree: polynomial with only a constant term.
     */
    @Test
    public final void testGetDegreeConstantOnly() {

        // Create polynomial: constant = 7.5
        Polynomial pTest = this.createPoly(0, 7.5);
        Polynomial pRef = this.createPoly(0, 7.5); // reference

        // degree should be 0
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(0, pTest.getDegree());
        assertTrue(pTest.getDegree() == 0);

        // constant term must match
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
        assertTrue(pTest.getCoefficient(0) == 7.5);

        // ensure all other degrees are zero
        assertEquals(0.0, pTest.getCoefficient(1), 0.0001);
        assertEquals(0.0, pTest.getCoefficient(5), 0.0001);
        assertEquals(0.0, pTest.getCoefficient(100), 0.0001);
        assertTrue(pTest.getCoefficient(1) == 0.0);
        assertTrue(pTest.getCoefficient(5) == 0.0);
        assertTrue(pTest.getCoefficient(100) == 0.0);

        // compare full polynomial
        assertEquals(pRef, pTest);
    }

    /**
     * Test getDegree: polynomial 2x + 1.
     */
    @Test
    public final void testGetDegreeSmallPositive() {

        // Create polynomial 2x + 1
        Polynomial pTest = this.createPoly(1, 2.0, 0, 1.0);
        Polynomial pRef = this.createPoly(1, 2.0, 0, 1.0);

        // degree should be 1
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(1, pTest.getDegree());
        assertTrue(pTest.getDegree() == 1);

        // check coefficients
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);
        assertTrue(pTest.getCoefficient(1) == 2.0);
        assertTrue(pTest.getCoefficient(0) == 1.0);

        // polynomial equality
        assertEquals(pRef, pTest);
    }

    /**
     * Test getDegree: mixed positive and negative coefficients.
     */
    @Test
    public final void testGetDegreeMixedSigns() {

        // Polynomial: -3x^3 + 2x - 5
        Polynomial pTest = this.createPoly(3, -3.0, 1, 2.0, 0, -5.0);

        Polynomial pRef = this.createPoly(3, -3.0, 1, 2.0, 0, -5.0);

        // degree should be 3
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(3, pTest.getDegree());
        assertTrue(pTest.getDegree() == 3);

        // coefficient checks
        assertEquals(pRef.getCoefficient(3), pTest.getCoefficient(3), 0.0001);
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);

        assertEquals(pRef, pTest);
    }

    /**
     * Test getDegree: all negative coefficients.
     */
    @Test
    public final void testGetDegreeAllNegative1() {

        // Polynomial: -2x^4 - 3x^2 - 1
        Polynomial pTest = this.createPoly(4, -2.0, 2, -3.0, 0, -1.0);

        Polynomial pRef = this.createPoly(4, -2.0, 2, -3.0, 0, -1.0);

        // degree should be 4
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(4, pTest.getDegree());
        assertTrue(pTest.getDegree() == 4);

        // coefficient checks
        assertEquals(pRef.getCoefficient(4), pTest.getCoefficient(4), 0.0001);
        assertEquals(pRef.getCoefficient(2), pTest.getCoefficient(2), 0.0001);
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);

        assertEquals(pRef, pTest);
    }

    /**
     * Test getDegree: after removing highest degree term, degree decreases.
     */
    @Test
    public final void testGetDegreeAfterRemovingHighest() {

        // Create polynomial: 5x^3 + 2x + 1
        Polynomial pTest = this.createPoly(3, 5.0, 1, 2.0, 0, 1.0);

        // Remove highest degree term (x^3)
        pTest.removeCoefficient(3);

        // Reference polynomial: 2x + 1 (degree = 1)
        Polynomial pRef = this.createPoly(1, 2.0, 0, 1.0);

        // degree should drop from 3 → 1
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(1, pTest.getDegree());
        assertTrue(pTest.getDegree() == 1);

        // remaining coefficients must match
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);

        // full polynomial equality
        assertEquals(pRef, pTest);
    }

    /**
     * Test getDegree: polynomial including negative degrees.
     */
    @Test
    public final void testGetDegreeWithNegativeExponent() {

        // Create polynomial: x^-2 + 3x + 5
        Polynomial pTest = this.createPoly(-2, 1.0, 1, 3.0, 0, 5.0);
        Polynomial pRef = this.createPoly(-2, 1.0, 1, 3.0, 0, 5.0);

        // degree should be max among degrees → 1
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(1, pTest.getDegree());
        assertTrue(pTest.getDegree() == 1);

        // verify coefficients
        assertEquals(pRef.getCoefficient(-2), pTest.getCoefficient(-2), 0.0001);
        assertEquals(pRef.getCoefficient(1), pTest.getCoefficient(1), 0.0001);
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);

        // polynomial equality
        assertEquals(pRef, pTest);
    }

    /**
     * Test getDegree: degree changes after modifying a coefficient.
     */
    @Test
    public final void testGetDegreeAfterCoefficientChange() {

        // Create polynomial: 4x^3 + 7x^2 + 1
        Polynomial pTest = this.createPoly(3, 4.0, 2, 7.0, 0, 1.0);

        // Set coefficient of x^3 to 0 → remove highest term
        pTest.setCoefficient(3, 0.0);

        // Reference polynomial: 7x^2 + 1 (degree = 2)
        Polynomial pRef = this.createPoly(2, 7.0, 0, 1.0);

        // degree should change from 3 → 2
        assertEquals(pRef.getDegree(), pTest.getDegree());
        assertEquals(2, pTest.getDegree());
        assertTrue(pTest.getDegree() == 2);

        // verify coefficients
        assertEquals(pRef.getCoefficient(2), pTest.getCoefficient(2), 0.0001);
        assertEquals(pRef.getCoefficient(0), pTest.getCoefficient(0), 0.0001);

        // removed term must be 0
        assertEquals(0.0, pTest.getCoefficient(3), 0.0001);

        // compare full polynomial
        assertEquals(pRef, pTest);
    }

    /**
     * Test transferFrom: normal transfer from non-empty source.
     */
    @Test
    public final void testTransferFromNormal() {

        Polynomial pTarget = this.constructorTest(); // empty
        Polynomial pSource = this.createPoly(3, 2.0, 1, -4.0); // 2x^3 - 4x

        Polynomial pRef = this.createPoly(3, 2.0, 1, -4.0); // expected target

        // transfer
        pTarget.transferFrom(pSource);

        // target == source(previous)
        assertEquals(pRef, pTarget);

        // source must be empty after transfer
        assertEquals(0, pSource.getDegree());
        assertTrue(pSource.getCoefficient(0) == 0.0);
    }

    /**
     * Test transferFrom: source becomes zero polynomial after transfer.
     */
    @Test
    public final void testTransferFromSourceBecomesEmpty() {

        Polynomial pTarget = this.createPoly(1, 5.0); // x term
        Polynomial pSource = this.createPoly(2, 3.0); // x^2 term

        // transfer
        pTarget.transferFrom(pSource);

        // source must be empty
        assertEquals(0, pSource.getDegree());
        assertEquals(0.0, pSource.getCoefficient(0), 0.0001);
    }

    /**
     * Test transferFrom: target is non-empty before transfer.
     */
    @Test
    public final void testTransferFromOverwriteTarget() {

        Polynomial pTarget = this.createPoly(4, 10.0, 1, 2.0); // 10x^4 + 2x
        Polynomial pSource = this.createPoly(2, -3.0, 0, 7.0); // -3x^2 + 7

        Polynomial pRef = this.createPoly(2, -3.0, 0, 7.0); // expected

        // transfer
        pTarget.transferFrom(pSource);

        // target should now equal source
        assertEquals(pRef, pTarget);

        // source must be empty
        assertEquals(0, pSource.getDegree());
        assertTrue(pSource.getCoefficient(0) == 0.0);
    }

}