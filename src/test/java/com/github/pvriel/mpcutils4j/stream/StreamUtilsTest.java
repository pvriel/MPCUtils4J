package com.github.pvriel.mpcutils4j.stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StreamUtilsTest {

    private InputStream inputStream;
    private OutputStream outputStream;
    private final static Random random = new Random();

    @BeforeEach
    void setUp() throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream(100_000);
        PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);

        inputStream = pipedInputStream;
        outputStream = pipedOutputStream;
    }

    @AfterEach
    void breakDown() throws IOException {
        inputStream.close();
        outputStream.close();
    }

    @Test
    @DisplayName("Check if a BigInteger can be correctly written to an OutputStream and read from an InputStream.")
    void writeBigIntegerToOutputStream() throws IOException {
        int amountOfTests = 1000;
        int bitLengthValues = 2048;
        for (int i = 0; i < amountOfTests; i ++) {
            BigInteger randomValue = new BigInteger(bitLengthValues, random);
            StreamUtils.writeBigIntegerToOutputStream(randomValue, outputStream);
            BigInteger readValue = StreamUtils.readBigIntegerFromInputStream(inputStream);
            assertEquals(randomValue, readValue);
        }
    }

    @Test
    @DisplayName("Check if an array of BigIntegers can be correctly written to an OutputStream and read from an InputStream.")
    void writeArrayOfBigIntegersToOutputStream() throws IOException {
        int amountOfValues = 100;
        int bitLengthValues = 2048;

        BigInteger[] randomValues = new BigInteger[amountOfValues];
        for (int j = 0; j < randomValues.length; j++) {
            randomValues[j] = new BigInteger(bitLengthValues, random);
        }
        StreamUtils.writeArrayOfBigIntegersToOutputStream(randomValues, outputStream);
        BigInteger[] readValues = StreamUtils.readArrayOfBigIntegersFromInputStream(inputStream);
        assertArrayEquals(randomValues, readValues);

    }

    @Test
    @DisplayName("Check if a byte array can be correctly written to an OutputStream and read from an InputStream.")
    void writeByteArrayToOutputStream() throws IOException {
        int amountOfTests = 1000;
        int bitLengthValues = 2048;
        for (int i = 0; i < amountOfTests; i ++) {
            byte[] randomValue = new BigInteger(bitLengthValues, random).toByteArray();
            StreamUtils.writeByteArrayToOutputStream(randomValue, outputStream);
            byte[] readValue = StreamUtils.readByteArrayFromInputStream(inputStream);
            assertArrayEquals(randomValue, readValue);
        }
    }
}