package com.github.mfriedenhagen.failsafesample;

import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testApp() {
        assertTrue(new App().isFooUnitTest());
    }

    @Test
    @Ignore("Ignored unittest.")
    public void testUnittestIgnored() {
        assertTrue(new App().isFooUnitTest());
    }
}
