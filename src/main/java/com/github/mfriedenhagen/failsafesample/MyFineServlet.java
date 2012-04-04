/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mfriedenhagen.failsafesample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mirko
 */
public class MyFineServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(MyFineServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("{}", req);
        final String name = req.getParameter("name") == null ? "Mirko" : req.getParameter("name");
        resp.getWriter().print("Hallo " + name);
    }
}
