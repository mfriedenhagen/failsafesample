/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author mirko
 */
public class AppIT {

    @Test
    public void checkFailing() {
        assertTrue("Should fail", false);
    }

    @Test
    public void checkSuccess() {
        assertTrue("Should not fail", new App().isFooIntegrationTest());
    }

    @Test
    @Ignore("Ignored Integration test.")
    public void checkIgnore() {
        assertTrue("Should not fail", new App().isFooIntegrationTest());
    }
}
