package io.github.eroshenkoam.allure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedVsYellowTest {

    @Test
    public void testRed() {
//        throw new AssertionFailedError(message, expected, actual);
        assertEquals(1, 2);
    }

    @Test
    public void testYellow() {
        throw new RuntimeException("Not found");
    }

}
