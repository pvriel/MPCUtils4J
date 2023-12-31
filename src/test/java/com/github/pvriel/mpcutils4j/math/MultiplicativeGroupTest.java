package com.github.pvriel.mpcutils4j.math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicativeGroupTest {

    private final static BigInteger p = new BigInteger("32317006071311007300714876688669951960444102669715484032130345427524655138867890893197201411522913463688717960921898019494119559150490921095088152386448283120630877367300996091750197750389652106796057638384067568276792218642619756161838094338476170470581645852036305042887575891541065808607552399123930385521914333389668342420684974786564569494856176035326322058077805659331026192708460314150258592864177116725943603718461857357598351152301645904403697613233287231227125684710820209725157101726931323469678542580656697935045997268352998638215525193403303896028543209689578721838988682461578457274025662014413066681559");
    private final static BigInteger q = new BigInteger("26959946667150639794667015087019630673637144422540572481103610249951");
    private final static MultiplicativeGroup group = new MultiplicativeGroup(p);

    @DisplayName("Try to generate random elements from the group.")
    @Test
    void getRandomElements() {
        BigInteger[] randomElements = group.getRandomElements(1000);
        assertEquals(1000, randomElements.length);
        for (BigInteger element : randomElements) {
            assertTrue(element.compareTo(BigInteger.ONE) > 0);
            assertTrue(element.compareTo(p) < 0);
        }

        for (int i = 0; i < randomElements.length; i ++) {
            for (int j = i + 1; j < randomElements.length; j ++) {
                assertNotEquals(randomElements[i], randomElements[j]);
            }
        }
    }

    @DisplayName("Try to generate elements with a specific order from the group.")
    @Test
    void getElementOfOrder() {
        BigInteger elementOfOrder = group.getElementOfOrder(q);
        assertEquals(BigInteger.ONE, elementOfOrder.modPow(q, p));
    }
}