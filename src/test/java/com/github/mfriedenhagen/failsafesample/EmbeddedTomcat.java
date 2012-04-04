/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import java.net.URI;
import javax.servlet.Servlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mirko
 */
public class EmbeddedTomcat extends ExternalResource {
    
    private final static Logger LOG = LoggerFactory.getLogger(EmbeddedTomcat.class);

    private final static String APP_BASE = "src/main/webapp";

    private final static String HOSTNAME = "localhost";

    private final Tomcat tomcat = new Tomcat();

    private int tomcatPort;

    private final String servletPath;

    public EmbeddedTomcat(final String servletPath, final Servlet servlet) {
        try {
            tomcatPort = Integer.valueOf(System.getenv("TOMCAT_PORT"));
        } catch (IllegalArgumentException e) {
            tomcatPort = 8080;
        }
        this.servletPath = servletPath;
        tomcat.setBaseDir(".");
        tomcat.setPort(tomcatPort);
        tomcat.getHost().setAppBase(APP_BASE);
        tomcat.setHostname(HOSTNAME);
        final Context context = tomcat.addContext("", "");
        tomcat.addServlet(context, servletPath, servlet);
        context.addServletMapping("/*", servletPath);

    }

    @Override
    protected void before() throws Throwable {
        LOG.info("Starting tomcat on port '{}:{}'", HOSTNAME, tomcatPort);
        tomcat.start();
    }

    @Override
    protected void after() {
        LOG.info("Stopping tomcat on port '{}:{}'", HOSTNAME, tomcatPort);
        try {
            tomcat.stop();
            tomcat.destroy();
        } catch (LifecycleException ex) {
            throw new RuntimeException(ex);
        }
    }

    public URI getURI() {
        return URI.create("http://" + HOSTNAME + ":" + tomcatPort + "/");
    }
}
