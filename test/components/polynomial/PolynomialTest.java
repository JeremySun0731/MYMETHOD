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
     * Creates a polynomial from degree–coefficient pairs. Arguments: degree1,
     * coef1, degree2, coef2, ...
     *
     * Example: createPoly(3, 2.5, 1, -4, 0, 3) = 2.5x^3 - 4x + 3
     *
     * Coefficients may be integer or double. Zero coefficients are ignored.
     */
    private Polynomial createPoly(Object... args) {
        assert args.length
                % 2 == 0 : "Arguments must be degree-coefficient pairs";

        Polynomial p = this.constructorTest();
        p.clear();

        for (int i = 0; i < args.length; i += 2) {
            int degree = ((Number) args[i]).intValue();

            // Allow int, float, double coefficients
            double coef = ((Number) args[i + 1]).doubleValue();

            // Skip zero — OSU Component requires no zero-terms
            if (coef != 0.0) {
                p.setCoefficient(degree, coef);
            }
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
        pTest.removeCoefficient(3);

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
        pTest.removeCoefficient(3); // Remove 2.5x^3
        pTest.removeCoefficient(4); // Remove 3x^4
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
        pTest.removeCoefficient(2);

        // Full polynomial equality check
        assertEquals(pRef, pTest);

        // Coefficient for degree 2 should now be 0
        assertEquals(0.0, pTest.getCoefficient(2), 0.0001);

        // Degree of zero polynomial must be 0
        assertEquals(0, pTest.getDegree());
    }
    // --------------------------------------
    // Test 4: getCoefficient()
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
    // Test 5: removeCoefficient()
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
    // Test 6: getDegree()
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
        pTest.removeCoefficient(3);

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

    // --------------------------------------
    // Test 7: TransferFrom()
    // --------------------------------------
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

    // --------------------------------------
    // Secondary Test Cases
    // --------------------------------------
    // --------------------------------------
    // Test 1: add()
    // --------------------------------------
    /**
     * Test add: normal case with overlapping degrees.
     */
    @Test
    public void testAddNormal1() {

        // p1 = 3x^3 + 2x^2 + x
        Polynomial pTest1 = this.createPoly(3, 3, 2, 2, 1, 1);

        // p2 = x^2 + 4
        Polynomial pTest2 = this.createPoly(2, 1, 0, 4);

        // Expected = 3x^3 + 3x^2 + x + 4
        Polynomial pRef = this.createPoly(3, 3, 2, 3, 1, 1, 0, 4);

        assertEquals(pRef, pTest1.add(pTest2));
    }

    /**
     * Test add: non-overlapping degrees.
     */
    @Test
    public void testAddDisjoint() {

        // p1 = 5x^4
        Polynomial pTest1 = this.createPoly(4, 5);

        // p2 = 3x + 2
        Polynomial pTest2 = this.createPoly(1, 3, 0, 2);

        // Expected = 5x^4 + 3x + 2
        Polynomial pRef = this.createPoly(4, 5, 1, 3, 0, 2);

        assertEquals(pRef, pTest1.add(pTest2));
    }

    /**
     * Test add: includes negative degrees.
     */
    @Test
    public void testAddNegativeDegrees() {

        // p1 = x^-2 + 4x^2
        Polynomial pTest1 = this.createPoly(-2, 1, 2, 4);

        // p2 = 3x^-2 + x^-1
        Polynomial pTest2 = this.createPoly(-2, 3, -1, 1);

        // Expected = 4x^-2 + x^-1 + 4x^2
        Polynomial pRef = this.createPoly(-2, 4, -1, 1, 2, 4);

        assertEquals(pRef, pTest1.add(pTest2));
    }

    /**
     * Test add: adding zero polynomial.
     */
    @Test
    public void testAddZeroPolynomial() {

        // p1 = 2x^2 + 1
        Polynomial pTest1 = this.createPoly(2, 2, 0, 1);

        // p2 = 0
        Polynomial pTest2 = this.createPoly();

        // Expected = p1
        Polynomial pRef = this.createPoly(2, 2, 0, 1);

        assertEquals(pRef, pTest1.add(pTest2));
    }

    /**
     * Test add: both are zero polynomials.
     */
    @Test
    public void testAddBothZero() {

        Polynomial pTest1 = this.createPoly();
        Polynomial pTest2 = this.createPoly();

        Polynomial pRef = this.createPoly();

        assertEquals(pRef, pTest1.add(pTest2));
    }

    /**
     * Test add: term cancellation to zero.
     */
    @Test
    public void testAddCancelToZero() {

        // p1 = 3x^2
        Polynomial pTest1 = this.createPoly(2, 3);

        // p2 = -3x^2
        Polynomial pTest2 = this.createPoly(2, -3);

        // Expected = 0
        Polynomial pRef = this.createPoly();

        assertEquals(pRef, pTest1.add(pTest2));
    }

    /**
     * Test add: degree increases after addition.
     */
    @Test
    public void testAddIncreaseDegree() {

        // p1 = 5x^2 + 1
        Polynomial pTest1 = this.createPoly(2, 5, 0, 1);

        // p2 = 3x^5
        Polynomial pTest2 = this.createPoly(5, 3);

        // Expected = 3x^5 + 5x^2 + 1
        Polynomial pRef = this.createPoly(5, 3, 2, 5, 0, 1);

        assertEquals(pRef, pTest1.add(pTest2));
    }

    /**
     * Test add: minDegree decreases after addition.
     */
    @Test
    public void testAddDecreaseMinDegree() {

        // p1 = x + 1
        Polynomial pTest1 = this.createPoly(1, 1, 0, 1);

        // p2 = 4x^-3
        Polynomial pTest2 = this.createPoly(-3, 4);

        // Expected = 4x^-3 + x + 1
        Polynomial pRef = this.createPoly(-3, 4, 1, 1, 0, 1);

        assertEquals(pRef, pTest1.add(pTest2));
    }

    // --------------------------------------
    // Test 2: subtract()
    // --------------------------------------
    @Test
    public void testSubtractNormalOverlap() {
        Polynomial p1 = this.createPoly(3, 3, 2, 2, 1, 1);
        Polynomial p2 = this.createPoly(2, 1, 1, 4);
        Polynomial pRef = this.createPoly(3, 3, 2, 1, 1, -3);
        assertEquals(pRef, p1.subtract(p2));
    }

    /**
     * Test subtract: no overlapping terms between p1 and p2.
     */
    @Test
    public void testSubtractNoOverlap() {
        Polynomial p1 = this.createPoly(4, 2, 1, 3);
        Polynomial p2 = this.createPoly(3, 7, 0, 5);
        Polynomial pRef = this.createPoly(4, 2, 3, -7, 1, 3, 0, -5);
        assertEquals(pRef, p1.subtract(p2));
    }

    /**
     * Test subtract: subtraction results in negative coefficients.
     */
    @Test
    public void testSubtractProducesNegatives() {
        Polynomial p1 = this.createPoly(2, 1);
        Polynomial p2 = this.createPoly(2, 5, 1, 3);
        Polynomial pRef = this.createPoly(2, -4, 1, -3);
        assertEquals(pRef, p1.subtract(p2));
    }

    /**
     * Test subtract: one term becomes zero and must be removed.
     */
    @Test
    public void testSubtractTermBecomesZero() {
        Polynomial p1 = this.createPoly(3, 2, 1, 4);
        Polynomial p2 = this.createPoly(3, 2);
        Polynomial pRef = this.createPoly(1, 4);
        assertEquals(pRef, p1.subtract(p2));
    }

    /**
     * Test subtract: subtracting zero polynomial returns the same polynomial.
     */
    @Test
    public void testSubtractZeroPolynomial() {
        Polynomial p1 = this.createPoly(3, 5, 0, 2);
        Polynomial p2 = this.createPoly();
        Polynomial pRef = this.createPoly(3, 5, 0, 2);
        assertEquals(pRef, p1.subtract(p2));
    }

    /**
     * Test subtract: zero polynomial minus p equals the negation of p.
     */
    @Test
    public void testZeroMinusP() {
        Polynomial p1 = this.createPoly();
        Polynomial p2 = this.createPoly(2, 3, 0, 1);
        Polynomial pRef = this.createPoly(2, -3, 0, -1);
        assertEquals(pRef, p1.subtract(p2));
    }

    /**
     * Test subtract: highest-degree term removed causing degree to decrease.
     */

    @Test
    public void testSubtractDegreeDrops() {

        // p1 = x^4 + 3x^2
        Polynomial p1 = this.createPoly(4, 1, 2, 3);

        // p2 = x^4
        Polynomial p2 = this.createPoly(4, 1);

        // Expected = 3x^2
        Polynomial pRef = this.createPoly(2, 3);

        assertEquals(pRef, p1.subtract(p2));
    }

    // --------------------------------------
    // Test 3: multiply()
    // --------------------------------------
    /**
     * Test multiply: normal case with overlapping exponents.
     */
    @Test
    public void testMultiplyNormal() {
        Polynomial p1 = this.createPoly(1, 2, 2, 3); // 2x + 3x^2
        Polynomial p2 = this.createPoly(0, 4, 1, 1); // 4 + x

        Polynomial pRef = this.createPoly(3, 3, // 3x^3
                2, 14, // 14x^2
                1, 8 // 8x
        );

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: multiplying by zero polynomial gives zero.
     */
    @Test
    public void testMultiplyByZeroPolynomial() {
        Polynomial p1 = this.createPoly(2, 5, 0, 1); // 5x^2 + 1
        Polynomial p2 = this.createPoly(); // 0

        Polynomial pRef = this.createPoly(); // 0

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: zero polynomial times non-zero gives zero.
     */
    @Test
    public void testMultiplyZeroByNonZero() {
        Polynomial p1 = this.createPoly(); // 0
        Polynomial p2 = this.createPoly(3, 2, 1, 7); // 2x^3 + 7x

        Polynomial pRef = this.createPoly(); // 0

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: each polynomial has one term.
     */
    @Test
    public void testMultiplySingleTerm() {
        Polynomial p1 = this.createPoly(3, 2); // 2x^3
        Polynomial p2 = this.createPoly(4, 5); // 5x^4
        // Expected: 10x^7
        Polynomial pRef = this.createPoly(7, 10); // 10x^7

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: some resulting terms cancel out.
     */
    @Test
    public void testMultiplyCreatesCancellation() {
        Polynomial p1 = this.createPoly(1, 2, 0, 3); // 2x + 3
        Polynomial p2 = this.createPoly(1, -2, 0, 2); // -2x + 2

        // Expansion:
        // (2x + 3)(-2x + 2)
        // = -4x^2 + 4x -6x + 6
        // = -4x^2 -2x + 6
        Polynomial pRef = this.createPoly(2, -4, 1, -2, 0, 6);

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: multiplication with negative degrees.
     */
    @Test
    public void testMultiplyNegativeDegrees() {
        Polynomial p1 = this.createPoly(-1, 2); // 2x^-1
        Polynomial p2 = this.createPoly(-2, 3); // 3x^-2

        Polynomial pRef = this.createPoly(-3, 6); // 6x^-3

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: polynomials with large gaps in degrees.
     */
    @Test
    public void testMultiplyLargeGap() {
        Polynomial p1 = this.createPoly(8, 2); // 2x^8
        Polynomial p2 = this.createPoly(3, 3); // 3x^3

        Polynomial pRef = this.createPoly(11, 6); // 6x^11

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: mixture of positive and negative coefficients.
     */
    @Test
    public void testMultiplyMixedSigns() {
        Polynomial p1 = this.createPoly(2, -3, 0, 4); // -3x^2 + 4
        Polynomial p2 = this.createPoly(1, 5, 0, -2); // 5x - 2

        // Expansion:
        // (-3x^2 + 4)(5x - 2)
        // = -15x^3 + 6x + 20x - 8
        // = -15x^3 + 26x - 8
        Polynomial pRef = this.createPoly(3, -15, 2, 6, 1, 20, 0, -8);

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: simple polynomial with decimal coefficients.
     */
    @Test
    public void testMultiplyDecimalSimple() {
        Polynomial p1 = this.createPoly(1, 1.5, 0, 2.2); // 1.5x + 2.2
        Polynomial p2 = this.createPoly(0, 3.0); // 3.0

        Polynomial pRef = this.createPoly(1, 1.5 * 3.0, 0, 2.2 * 3.0);

        assertEquals(pRef, p1.multiply(p2));
    }

    /**
     * Test multiply: decimal coefficients with mixed signs.
     */
    @Test
    public void testMultiplyDecimalMixedSigns() {

        // p1 = -1.5x^2 + 3.25
        Polynomial p1 = this.createPoly(2, -1.5, 0, 3.25);

        // p2 = 2.4x - 0.5
        Polynomial p2 = this.createPoly(1, 2.4, 0, -0.5);

        /*
         * Expansion: (-1.5x^2)(2.4x) = -3.6x^3 (-1.5x^2)(-0.5) = +0.75x^2
         * (3.25)(2.4x) = +7.8x (3.25)(-0.5) = -1.625
         *
         * Result = -3.6x^3 + 0.75x^2 + 7.8x - 1.625
         */
        Polynomial pRef = this.createPoly(3, -3.6, 2, 0.75, 1, 7.8, 0, -1.625);

        assertEquals(pRef, p1.multiply(p2));
    }

    // --------------------------------------
    // Test 4: derivative()
    // --------------------------------------
    /**
     * Test derivative: normal case.
     */
    @Test
    public void testDerivativeNormal() {
        // p = 5x^3 + 2x^1 + 1
        Polynomial p = this.createPoly(3, 5, 1, 2, 0, 1);

        // p' = 15x^2 + 2
        Polynomial pRef = this.createPoly(2, 15, 0, 2);

        assertEquals(pRef, p.derivative());
    }

    /**
     * Test derivative: constant becomes zero.
     */
    @Test
    public void testDerivativeConstant() {
        Polynomial p = this.createPoly(0, 7); // p = 7
        Polynomial pRef = this.createPoly(); // p' = 0
        assertEquals(pRef, p.derivative());
    }

    @Test
    public void testDerivativeNegativeExponent() {
        // p = 4x^-2 + 3x + 2
        Polynomial p = this.createPoly(-2, 4, 1, 3, 0, 2);

        // p' = -8x^-3 + 3
        Polynomial pRef = this.createPoly(-3, -8, 0, 3);

        assertEquals(pRef, p.derivative());
    }

    /**
     * Test derivative: mix of positive and negative exponents.
     */
    @Test
    public void testDerivativeMixedSigns() {

        // p = -3x^4 + 2x^-1
        Polynomial p = this.createPoly(4, -3, -1, 2);

        // p' = -12x^3 - 2x^-2
        Polynomial pRef = this.createPoly(3, -12, -2, -2);

        assertEquals(pRef, p.derivative());
    }

    // --------------------------------------
    // Test 5: integrate()
    // --------------------------------------
    /**
     * Test integrate: zero stays zero (constant = 0).
     */
    @Test
    public void testIntegrateZero() {
        Polynomial p = this.createPoly();
        Polynomial pRef = this.createPoly();
        assertEquals(pRef, p.integrate());
    }

    /**
     * Test integrate: sparse polynomial with gaps.
     */
    @Test
    public void testIntegrateSparse() {
        // p = 10x^5 + 3
        Polynomial p = this.createPoly(5, 10, 0, 3);

        // ∫p dx = (10/6)x^6 + 3x = 1.6666x^6 + 3x
        Polynomial pRef = this.createPoly(6, 10.0 / 6.0, 1, 3);

        assertEquals(pRef, p.integrate());
    }

    /**
     * Test derivative: decimal coefficients with positive exponents.
     */
    @Test
    public void testDerivativeDecimalPositive() {
        Polynomial p = this.createPoly(3, 2.5, 1, -0.75, 0, 1.2); // 2.5x^3 − 0.75x + 1.2

        Polynomial pRef = this.createPoly(2, 7.5, 0, -0.75); // 7.5x^2 − 0.75

        assertEquals(pRef, p.derivative());
    }

    /**
     * Test derivative: decimal coefficients with negative exponents.
     */
    @Test
    public void testDerivativeDecimalNegativeExponent() {
        Polynomial p = this.createPoly(-2, -1.5, // -1.5x^-2
                0, 3.25 // + 3.25
        );

        Polynomial pRef = this.createPoly(-3, 3.0 // 3.0x^-3
        );

        assertEquals(pRef, p.derivative());
    }

    // --------------------------------------
    // Test 5: integrate()
    // --------------------------------------
    /**
     * Test integrate: constant becomes linear term.
     */
    @Test
    public void testIntegrateConstant() {
        Polynomial p = this.createPoly(0, 7.0 // 7
        );

        Polynomial pRef = this.createPoly(1, 7.0 // 7x
        );

        assertEquals(pRef, p.integrate());
    }

    /**
     * Test integrate: decimal coefficients, positive exponents.
     */
    @Test
    public void testIntegrateDecimalPositive() {
        Polynomial p = this.createPoly(2, 3.0, 1, -0.8, 0, 1.5);

        Polynomial pRef = this.createPoly(3, 1.0, // 3 / 3 = 1
                2, -0.4, // -0.8 / 2
                1, 1.5 // 1.5 / 1
        );

        assertEquals(pRef, p.integrate());
    }

    /**
     * Test integrate: supports negative exponents.
     */
    @Test
    public void testIntegrateNegativeExponent() {
        Polynomial p = this.createPoly(-2, 4.0 // 4x^-2
        );

        Polynomial pRef = this.createPoly(-1, -4.0 // -4x^-1
        );

        assertEquals(pRef, p.integrate());
    }

    /**
     * Test integrate: mix of positive and negative exponents (no -1).
     */
    @Test
    public void testIntegrateMixedExponents() {
        Polynomial p = this.createPoly(3, -1.5, -2, 2.0, 0, -0.75);

        Polynomial pRef = this.createPoly(4, -0.375, -1, -2.0, 1, -0.75);

        assertEquals(pRef, p.integrate());
    }

    // --------------------------------------
    // Test 6: evaluate()
    // --------------------------------------
    /**
     * Test evaluate: constant polynomial.
     */
    @Test
    public void testEvaluateConstant() {
        Polynomial p = this.createPoly(0, 7.0);

        assertEquals(7.0, p.evaluate(10.0), 0.0001);
    }

    /**
     * Test evaluate: positive exponents.
     */
    @Test
    public void testEvaluatePositive() {
        Polynomial p = this.createPoly(3, 2.0, 1, -1.0, 0, 4.0);

        assertEquals(18.0, p.evaluate(2.0), 0.0001);
    }

    /**
     * Test evaluate: negative exponents.
     */
    @Test
    public void testEvaluateNegativeExponents() {
        Polynomial p = this.createPoly(-2, 3.0, -1, 2.0);

        assertEquals(1.75, p.evaluate(2.0), 0.0001);
    }

    /**
     * Test evaluate: mixed exponents with decimals.
     */
    @Test
    public void testEvaluateMixed() {
        Polynomial p = this.createPoly(2, 1.5, -1, -0.5, 0, 3.0);

        assertEquals(8.75, p.evaluate(2.0), 0.0001);
    }

    /**
     * Test evaluate: evaluate at x = 0 (no negative exponents allowed).
     */
    @Test
    public void testEvaluateAtZero() {
        Polynomial p = this.createPoly(3, 4.0, 1, -2.0, 0, 1.0);

        assertEquals(1.0, p.evaluate(0.0), 0.0001);
    }

    // --------------------------------------
    // Test 7: isZero()
    // --------------------------------------
    /**
     * Test isZero: true for zero polynomial.
     */
    @Test
    public void testIsZeroTrue() {
        Polynomial p = this.createPoly(); // empty => zero polynomial
        assertTrue(p.isZero());
    }

    /**
     * Test isZero: false for non-zero polynomial.
     */
    @Test
    public void testIsZeroFalse() {
        Polynomial p = this.createPoly(0, 5.0);

        assertFalse(p.isZero());
    }

    /**
     * Test isZero: negative exponent non-zero.
     */
    @Test
    public void testIsZeroNegativeExponent() {
        Polynomial p = this.createPoly(-1, 3.0);

        assertFalse(p.isZero());
    }

    /**
     * Test isZero: becomes zero after clear().
     */
    @Test
    public void testIsZeroAfterClear() {
        Polynomial p = this.createPoly(2, 2.0, 0, 1.0);

        p.clear(); // remove all terms

        assertTrue(p.isZero());
    }

    /**
     * Test isZero: all coefficients explicitly zero.
     */
    @Test
    public void testIsZeroExplicitZeroCoefficients() {
        Polynomial p = this.createPoly(2, 0.0, 1, 0.0, 0, 0.0);

        assertTrue(p.isZero());
    }

    /**
     * Test scale: normal polynomial with positive scale factor.
     */
    @Test
    public void testScaleNormal() {
        Polynomial p = this.createPoly(3, 2.0, 1, -4.0, 0, 3.0);

        Polynomial pRef = this.createPoly(3, 6.0, 1, -12.0, 0, 9.0);

        assertEquals(pRef, p.scale(3.0));
    }

    /**
     * Test scale: constant polynomial.
     */
    @Test
    public void testScaleConstant() {
        Polynomial p = this.createPoly(0, 5.0); // 5

        Polynomial pRef = this.createPoly(0, -15.0); // 5 × (-3)

        assertEquals(pRef, p.scale(-3.0));
    }

    /**
     * Test scale: polynomial becomes zero after scaling.
     */
    @Test
    public void testScaleToZero() {
        Polynomial p = this.createPoly(2, 1.5, 0, -0.5);

        Polynomial pRef = this.createPoly(); // expected zero polynomial

        assertEquals(pRef, p.scale(0.0));
    }

    /**
     * Test scale: supports negative exponents.
     */
    @Test
    public void testScaleNegativeExponent() {
        Polynomial p = this.createPoly(-2, 4.0, // 4x^-2
                -1, -3.0 // -3x^-1
        );

        Polynomial pRef = this.createPoly(-2, -8.0, // 4 × (-2)
                -1, 6.0 // -3 × (-2)
        );

        assertEquals(pRef, p.scale(-2.0));
    }

    /**
     * Test scale: mixed positive & negative coefficients with decimal factor.
     */
    @Test
    public void testScaleDecimal() {
        Polynomial p = this.createPoly(2, -1.5, 1, 4.0, 0, 0.25);

        Polynomial pRef = this.createPoly(2, -0.75, // -1.5 × 0.5
                1, 2.0, // 4 × 0.5
                0, 0.125 // 0.25 × 0.5
        );

        assertEquals(pRef, p.scale(0.5));
    }

    // --------------------------------------
    // Test 7: equals()
    // --------------------------------------
    /**
     * Test equals: same object.
     */
    @Test
    public void testEqualsSameObject() {
        Polynomial p = this.createPoly(2, 3.0, 0, -1.0);
        assertEquals(true, p.equals(p));
    }

    /**
     * Test equals: equal polynomials with same terms.
     */
    @Test
    public void testEqualsSamePolynomial() {
        Polynomial p1 = this.createPoly(3, 2.0, 1, -1.0, 0, 4.0);

        Polynomial p2 = this.createPoly(3, 2.0, 1, -1.0, 0, 4.0);

        assertEquals(true, p1.equals(p2));
    }

    /**
     * Test equals: different degree range -> false.
     */
    @Test
    public void testEqualsDifferentDegrees() {
        Polynomial p1 = this.createPoly(3, 1.0); // highest degree = 3
        Polynomial p2 = this.createPoly(2, 1.0); // highest degree = 2

        assertEquals(false, p1.equals(p2));
    }

    /**
     * Test equals: coefficients differ -> false.
     */
    @Test
    public void testEqualsDifferentCoefficient() {
        Polynomial p1 = this.createPoly(2, 3.0, 1, 1.0);

        Polynomial p2 = this.createPoly(2, 3.0, 1, 2.0 // coefficient differs
        );

        assertEquals(false, p1.equals(p2));
    }

    /**
     * Test equals: compare with non-polynomial object -> false.
     */
    @Test
    public void testEqualsDifferentType() {
        Polynomial p = this.createPoly(0, 1.0);
        String other = "not a polynomial";

        assertEquals(false, p.equals(other));
    }

    /**
     * Test equals: coefficients equal within EPS tolerance.
     */
    @Test
    public void testEqualsEpsTolerance() {
        Polynomial p1 = this.createPoly(1, 1.000000001);
        Polynomial p2 = this.createPoly(1, 1.000000002);

        assertEquals(true, p1.equals(p2)); // within EPS
    }

}