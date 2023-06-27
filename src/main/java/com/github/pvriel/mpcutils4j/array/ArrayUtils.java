package com.github.pvriel.mpcutils4j.array;

import java.math.BigInteger;

/**
 * Abstract class, containing some generic util methods to work with arrays.
 */
public abstract class ArrayUtils {

    /**
     * Method to transpose a 2D char array.
     * @param   original
     *          The original 2D array. The assumption is that the array is rectangular (but is allowed to contain null values).
     * @return  The transposed array.
     */
    public static char[][] transpose(char[][] original) {
        char[][] returnValue = new char[original[0].length][original.length];
        for (int i = 0; i < returnValue.length; i ++) {
            for (int j = 0; j < returnValue[0].length; j ++) {
                returnValue[i][j] = original[j][i];
            }
        }
        return returnValue;
    }

    /**
     * Method to convert a boolean array to a {@link BigInteger} instance.
     * @param   choices
     *          The bit values (IN REVERSE ORDER) for the resulting {@link BigInteger}. Null value is not allowed here.
     * @return  The resulting {@link BigInteger} instance.
     */
    public static BigInteger convertToBigInteger(boolean[] choices) {
        BigInteger returnValue = BigInteger.ZERO;
        for (int i = 0; i < choices.length; i ++) if (choices[choices.length - 1 - i]) returnValue = returnValue.setBit(i);
        return returnValue;
    }

    /**
     * Method to convert a {@link BigInteger} instance to a boolean array.
     * @param   bigInteger
     *          The {@link BigInteger} instance.
     * @param   length
     *          The amount of booleans to return.
     * @return  The resulting boolean array.
     */
    public static boolean[] convertFromBigInteger(BigInteger bigInteger, int length) {
        boolean[] returnValue = new boolean[length];
        for (int i = 0; i < length; i ++) if (bigInteger.testBit(length - i - 1)) returnValue[i] = true;
        return returnValue;
    }

    /*public static String toString(Object[][] array) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < array.length; i ++) {
            stringBuilder.append("[");
            for (int j = 0; j < array[i].length; j ++) {
                stringBuilder.append(array[i][j]).append(", ");
            }
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "], ");
        }
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");
        return stringBuilder.toString();
    }*/
}
