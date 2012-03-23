/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.BeforeClass;

/**
 *
 * @author mirko
 */
public class AppAssumeIT {

    public AppAssumeIT() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Assume.assumeNoException(new IllegalStateException("Illegal state"));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
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
