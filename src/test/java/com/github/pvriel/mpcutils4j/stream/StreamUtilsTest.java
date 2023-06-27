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
        PipedInputStream pipedInputStream = new PipedInputStream(10_000);
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
}