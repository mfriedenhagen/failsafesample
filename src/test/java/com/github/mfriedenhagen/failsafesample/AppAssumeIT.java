/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mirko
 */
public class AppAssumeIT {

    public AppAssumeIT() {
    }

    @Before
    public void setUp() {
        Assume.assumeNoException(new IllegalStateException("Illegal state"));
    }

    @Test
    public void checkFailing() {
        assertTrue("Should fail", false);
    }

    @Test
    public void checkSuccess() {
        assertTrue("Should not fail", true);
    }
}
