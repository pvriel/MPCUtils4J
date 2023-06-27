package com.github.pvriel.mpcutils4j.math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigIntegerMatrixTest {

    @Test
    @DisplayName("Test 1 if a matrix can be properly transposed.")
    void transposeTestOne() {
        BigInteger columnOne = BigInteger.TWO;
        BigInteger columnTwo = BigInteger.ONE;
        BigIntegerMatrix matrix = new BigIntegerMatrix(new BigInteger[]{columnOne, columnTwo}, 2);
        /**
         * 1 0   ==>  The same thing!
         * 0 1
         */
        BigIntegerMatrix transposed = matrix.transpose();
        assertEquals(transposed, matrix);
    }

    @Test
    @DisplayName("Test 2 if a matrix can be properly transposed.")
    void transposeTestTwo() {
        BigInteger columOne = BigInteger.valueOf(1);
        BigInteger columnTwo = BigInteger.valueOf(2);
        BigInteger columnThree = BigInteger.valueOf(3);
        BigIntegerMatrix matrix = new BigIntegerMatrix(new BigInteger[]{columOne, columnTwo, columnThree}, 2);
        /*
        0   1   1   ==> 0   1
        1   0   1       1   0
                        1   1
         */
        BigIntegerMatrix transposed = matrix.transpose();
        BigInteger columnFour = BigInteger.valueOf(3);
        BigInteger columnFive = BigInteger.valueOf(5);
        BigIntegerMatrix transposedEquivalent = new BigIntegerMatrix(new BigInteger[]{columnFour, columnFive}, 3);
        assertEquals(transposedEquivalent, transposed);
    }

    @Test
    @DisplayName("Test if a matrix can be properly constructed.")
    void BigIntegerMatrixConstructorTest() {
        BigInteger columOne = BigInteger.valueOf(1);
        BigInteger columnTwo = BigInteger.valueOf(2);
        BigInteger columnThree = BigInteger.valueOf(3);
        BigIntegerMatrix rowMatrix = new BigIntegerMatrix(3, new BigInteger[]{columOne, columnTwo, columnThree});

        BigInteger columnFour = BigInteger.ZERO;
        BigInteger columnFive = BigInteger.valueOf(3);
        BigInteger columnSix = BigInteger.valueOf(5);
        BigIntegerMatrix columnMatrix = new BigIntegerMatrix(new BigInteger[]{columnFour, columnFive, columnSix}, 3);

        assertEquals(columnMatrix, rowMatrix);
    }
}