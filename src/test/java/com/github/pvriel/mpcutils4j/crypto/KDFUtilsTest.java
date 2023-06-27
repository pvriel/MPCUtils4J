package com.github.pvriel.mpcutils4j.crypto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class KDFUtilsTest {

    @Test
    @DisplayName("Check if the KDF function works as expected.")
    void KDF() {
        BigInteger test = BigInteger.valueOf(123456789);
        BigInteger result = KDFUtils.KDF(test, test.bitLength());
        assertEquals(BigInteger.valueOf(130445852), result);
    }
}