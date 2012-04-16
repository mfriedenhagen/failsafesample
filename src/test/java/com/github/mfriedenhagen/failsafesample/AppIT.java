/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mirko
 */
public class AppIT {
    
    private final static Logger LOG = LoggerFactory.getLogger(AppIT.class);

    @ClassRule
    public static EmbeddedTomcat embeddedTomcat = new EmbeddedTomcat("helloworld", new MyFineServlet());
    
    @Test
    public void testApp() {
        assertTrue(new App().isFooUnitTest());
    }

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

    @Test
    public void checkHelloWorld() throws MalformedURLException, IOException {
        URI uri = embeddedTomcat.getURI();
        final String expected = "Hallo Mirko";
        checkHelloWorld(uri, expected);
    }

    @Test
    public void checkHelloWorldWithDifferentName() throws MalformedURLException, IOException {
        URI uri = embeddedTomcat.getURI().resolve("?name=Peter");
        final String expected = "Hallo Peter";
        checkHelloWorld(uri, expected);
    }

    void checkHelloWorld(URI uri, final String expected) throws IOException {
        LOG.info("uri = '{}'", uri);
        final InputStream openStream = uri.toURL().openStream();
        final String content;
        try {
            content = CharStreams.toString(new InputStreamReader(openStream));
        } finally {
            openStream.close();
        }
        LOG.info("content='{}'", content);
        assertEquals(expected, content);
    }
}
