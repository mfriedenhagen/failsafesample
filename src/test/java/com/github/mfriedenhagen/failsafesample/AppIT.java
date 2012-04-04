/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertTrue;
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
    public void checkHelloWorldServlet() throws MalformedURLException, IOException {
        URI uri = embeddedTomcat.getURI();
        LOG.info("uri = '{}'", uri);
        final InputStream openStream = uri.toURL().openStream();
        final String content;
        try {
            content = IOUtils.toString(openStream);
        } finally {
            openStream.close();
        }
        LOG.info("content='{}'", content);
    }
}
