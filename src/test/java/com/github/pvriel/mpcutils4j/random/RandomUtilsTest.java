package com.github.pvriel.mpcutils4j.random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class RandomUtilsTest {

    @Test
    @DisplayName("Check if a random boolean array of a specific length can be correctly generated.")
    void generateRandomBooleanArrayOfLength() {
        int length = 1000;
        boolean[] randomBooleanArray = RandomUtils.generateRandomBooleanArrayOfLength(length);
        assertEquals(length, randomBooleanArray.length);
        for (int i = 0; i < length; i ++) {
            boolean[] anotherOne = RandomUtils.generateRandomBooleanArrayOfLength(length);
            assertNotEquals(randomBooleanArray, anotherOne);
        }
    }

    @Test
    @DisplayName("Check if a random 2D BigInteger array of specific lengths can be correctly generated.")
    void generateRandomBigInteger2DArrayOfLengths() {
        int length0 = 100;
        int length1 = 100;
        int bitLength = 1000;
        BigInteger[][] randomBigInteger2DArray = RandomUtils.generateRandomBigInteger2DArrayOfLengths(length0, length1, bitLength);
        assertEquals(length0, randomBigInteger2DArray.length);
        for (int i = 0; i < length0; i ++) assertEquals(length1, randomBigInteger2DArray[i].length);

        for (int i = 0; i < length0; i ++) {
            for (int j = 0; j < length1; j ++) {
                for (int k = i + 1; k < length0; k ++) {
                    for (int q = j + 1; q < length1; q ++) {
                        assertNotEquals(randomBigInteger2DArray[i][j], randomBigInteger2DArray[k][q]);
                    }
                }
            }
        }
    }
}