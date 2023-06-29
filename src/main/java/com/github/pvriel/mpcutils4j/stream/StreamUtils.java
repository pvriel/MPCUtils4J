package com.github.pvriel.mpcutils4j.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Abstract class, introducing help methods to write/read objects to/from {@link OutputStream}s/{@link InputStream}s.
 * <br>This class does not introduce the same communication overhead as the {@link java.io.ObjectOutputStream}s/{@link java.io.ObjectInputStream}s do.
 */
public abstract class StreamUtils {

    /**
     * Method to write a {@link BigInteger} instance to an {@link OutputStream}.
     * This method converts the {@link BigInteger} to a byte array, and a 4-byte long length prefix at the beginning of the resulting array.
     * <br>This method is compatible with the readFromInputStream method.
     * @param   bigInteger
     *          The {@link BigInteger} instance to write. No null values are allowed.
     * @param   outputStream
     *          The {@link OutputStream} to write the instance to. No null values are allowed.
     * @throws  IOException
     *          If the {@link OutputStream} threw an exception while writing the data.
     */
    public static void writeBigIntegerToOutputStream(BigInteger bigInteger, OutputStream outputStream) throws IOException {
        outputStream.write(addLengthPrefix(bigInteger.toByteArray()));
    }

    /**
     * Method to read a {@link BigInteger} instance from an {@link InputStream}.
     * <br>This method is compatible with the writeToOutputStream method.
     * @param   inputStream
     *          The {@link InputStream} to read the data from. No null values are allowed.
     * @return  The resulting {@link BigInteger}.
     * @throws  IOException
     *          If the {@link InputStream} threw an exception while reading the data.
     */
    public static BigInteger readBigIntegerFromInputStream(InputStream inputStream) throws IOException {
        return new BigInteger(receiveInputWithLengthPrefix(inputStream));
    }

    /**
     * Method to write a {@link BigInteger} array to an {@link OutputStream}.
     * @param   bigIntegers
     *          The {@link BigInteger} array to write. No null values are allowed.
     * @param   outputStream
     *          The {@link OutputStream} to write the instance to. No null values are allowed.
     * @throws  IOException
     *          If the {@link OutputStream} threw an exception while writing the data.
     */
    public static void writeArrayOfBigIntegersToOutputStream(BigInteger[] bigIntegers, OutputStream outputStream) throws IOException {
        outputStream.write(ByteBuffer.allocate(4).putInt(bigIntegers.length).array());
        for (BigInteger bigInteger : bigIntegers) {
            writeBigIntegerToOutputStream(bigInteger, outputStream);
        }
    }

    /**
     * Method to read a {@link BigInteger} array from an {@link OutputStream}.
     * @param   inputStream
     *          The {@link InputStream} to read the instance from. No null values are allowed.
     * @return  The resulting {@link BigInteger} array.
     * @throws  IOException
     *          If the {@link OutputStream} threw an exception while reading the data.
     */
    public static BigInteger[] readArrayOfBigIntegersFromInputStream(InputStream inputStream) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(inputStream.readNBytes(4));
        int length = byteBuffer.getInt();
        byteBuffer.clear();

        BigInteger[] bigIntegers = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            bigIntegers[i] = readBigIntegerFromInputStream(inputStream);
        }
        return bigIntegers;
    }

/**
     * Method to read a byte array from an {@link OutputStream}.
     * This method first reads a 4-byte long length prefix at the beginning of the resulting array.
     * <br>This method is compatible with the readFromInputStream method.
     * @param   inputStream
     *          The {@link InputStream} to read the instance from. No null values are allowed.
     * @throws  IOException
     *          If the {@link OutputStream} threw an exception while reading the data.
     * @return  The resulting byte array.
     */
    public static byte[] readByteArrayFromInputStream(InputStream inputStream) throws IOException {
        return receiveInputWithLengthPrefix(inputStream);
    }

    /**
     * Method to write a byte array to an {@link OutputStream}.
     * This method adds a 4-byte long length prefix at the beginning of the resulting array.
     * <br>This method is compatible with the readFromInputStream method.
     * @param   array
     *          The byte array to write. No null values are allowed.
     * @param   outputStream
     *          The {@link OutputStream} to write the instance to. No null values are allowed.
     * @throws  IOException
     *          If the {@link OutputStream} threw an exception while writing the data.
     */
    public static void writeByteArrayToOutputStream(byte[] array, OutputStream outputStream) throws IOException {
        outputStream.write(addLengthPrefix(array));
    }

    private static byte[] addLengthPrefix(byte[] array) {
        return ByteBuffer.allocate(array.length + 4)
                .putInt(array.length)
                .put(array)
                .array();
    }

    private static byte[] receiveInputWithLengthPrefix(InputStream inputStream) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(inputStream.readNBytes(4));
        int length = byteBuffer.getInt();
        byteBuffer.clear();

        return inputStream.readNBytes(length);
    }
}
