Polynomial Component — Portfolio Project

This project implements a complete Polynomial software component following the OSU Software Component methodology. The goal is to design a reusable mathematical data type that supports both kernel operations and higher-level algebraic and calculus operations.

This repository includes:

A fully implemented Polynomial component (Polynomial, PolynomialKernel, PolynomialSecondary, Polynomial1L)

A comprehensive JUnit test suite

Two qualitatively different use-case programs

A changelog documenting updates

A written reflection about the development process

Component Overview

This component models a mathematical polynomial of the form:

P(x) = Σ (a_d * x^d)


It supports sparse representation, meaning only non-zero coefficients are stored. Degrees may be positive or negative integers.

Features

Set, get, and remove coefficients

Retrieve maximum and minimum degree

Create new instances and clear the polynomial

Algebraic operations: addition, subtraction, multiplication, scaling

Calculus operations: derivative and indefinite integral

Polynomial evaluation at any real number

String formatting

Equality checking with floating-point tolerance

Hash code generation

Component Architecture

The implementation follows the OSU layered component design:

1. PolynomialKernel

Defines the core operations and contracts:

setCoefficient

getCoefficient

removeCoefficient

getDegree

getMinDegree

newInstance

clear

2. PolynomialSecondary

Provides secondary operations built entirely from kernel operations:

Addition, subtraction, multiplication

Derivative, integrate

Evaluation

Scaling

toString, equals, hashCode

3. Polynomial1L

Provides the concrete representation:

Map<Integer, Double>


Only non-zero terms are stored, producing an efficient and sparse implementation.

Testing

The JUnit test suite (Polynomial1LTest) verifies all kernel and secondary methods.
Tests cover:

Setting, replacing, and removing coefficients

Behavior with positive, zero, and negative degrees

Degree and minimum degree behavior for empty and non-empty polynomials

Algebra operations for normal, mixed, and edge cases

Derivative and integral correctness (including the undefined integral of x^-1)

Evaluation at multiple input values

String formatting across signs, degrees, and coefficient types

Equality with floating-point tolerance

Hash code consistency

This test suite satisfies the requirement for a thorough test plan covering the entire codebase.

Use Cases

Two use cases are included to demonstrate the range of possible applications.

Use Case 1: QuadraticSolver

File: components/QuadraticSolver.java

This example uses only kernel operations to:

Construct a quadratic polynomial

Extract coefficients

Compute the discriminant

Solve for real roots

Remove terms and clear the polynomial

Convert the polynomial to a readable format

This demonstrates numerical computation using the kernel interface alone.

Use Case 2: FunctionAnalysis

File: components/FunctionAnalysis.java

This example uses secondary operations to:

Display a polynomial

·Compute its derivative

·Compute its indefinite integral

·Evaluate the polynomial at several inputs

·Add, subtract, and multiply two polynomials

This demonstrates symbolic manipulation, calculus operations, and combined polynomial algebra.

How to Run

Compile and run from the project root:

javac src/components/polynomial/*.java
javac src/components/*.java
java components.QuadraticSolver
java components.FunctionAnalysis


To run the test suite, open the test directory in VS Code or IntelliJ and run Polynomial1LTest.java.

Reflection Summary

·Through this project, I gained practical experience in software component design, including:

·Designing abstractions and selecting appropriate representations

·Identifying edge cases such as negative degrees and undefined integrals

·Applying design-by-contract through preconditions and postconditions

·Writing comprehensive unit tests

·Debugging and revising code based on test feedback

·This project reinforced my interest in backend development and improved my ability to think about structure, contracts, and component reliability.

·A full reflection is included in the repository as required.

Changelog

A complete development history is documented in:

CHANGELOG.md


It includes entries for testing, use-case creation, bug fixing, documentation improvements, and structural updates.

Repository Structure
src/
  components/
    polynomial/
      Polynomial.java
      PolynomialKernel.java
      PolynomialSecondary.java
      Polynomial1L.java
    QuadraticSolver.java
    FunctionAnalysis.java
test/
  components/
    polynomial/
      Polynomial1LTest.java
doc/
  06-component-finishing-touches/
CHANGELOG.md
README.md
LICENSE