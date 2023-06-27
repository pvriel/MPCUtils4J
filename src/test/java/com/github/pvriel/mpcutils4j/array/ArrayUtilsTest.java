package com.github.pvriel.mpcutils4j.array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {

    private final static Random random = new Random();

    @Test
    @DisplayName("Check if a boolean array can be properly converted to a BigInteger array.")
    void convertToBigInteger() {
        boolean[] booleanArray = new boolean[]{true, false, true, false};
        BigInteger convertedAsBigInteger = BigInteger.valueOf(10);
        assertEquals(convertedAsBigInteger, ArrayUtils.convertToBigInteger(booleanArray));
    }

    @Test
    @DisplayName("Check if a random BigInteger can be properly converted to a BigInteger and vice versa.")
    void conversionTest() {
        int bitLength = 1000;
        int amountOfRandomSamples = 1000;
        for (int i = 0; i < amountOfRandomSamples; i ++) {
            BigInteger value = new BigInteger(bitLength, random);
            boolean[] converted = ArrayUtils.convertFromBigInteger(value, bitLength);
            BigInteger convertedBack = ArrayUtils.convertToBigInteger(converted);
            assertEquals(value, convertedBack);
        }
    }
}