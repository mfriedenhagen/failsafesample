/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.rules.ExternalResource;

/**
 *
 * @author mirko
 */
public class EmbeddedTomcat extends ExternalResource {

    private final Tomcat tomcat = new Tomcat();
    private int tomcatPort;

    public EmbeddedTomcat() {        
        try {
            tomcatPort = Integer.valueOf(System.getenv("TOMCAT_PORT"));
        } catch (IllegalArgumentException e) {
            tomcatPort = 8080;
        }
        tomcat.setBaseDir("target/failsafesample-1.0-SNAPSHOT");
        tomcat.setPort(tomcatPort);
        tomcat.addServlet("", "helloworld", new MyFineServlet());
    }

    @Override
    protected void before() throws Throwable {
        System.err.println("Starting tomcat on port " + tomcatPort);
        tomcat.start();
    }

    @Override
    protected void after() {
        System.err.println("Stopping tomcat on port " + tomcatPort);
        try {
            tomcat.stop();
            tomcat.destroy();
        } catch (LifecycleException ex) {
            throw new RuntimeException(ex);
        }
    }
}
