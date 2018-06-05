package indi.pc.sc.web;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import indi.pc.sc.base.SpringContextHolder;

public class SCJettyServer {
    private static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";


    public void run() throws Exception {
        Server server = new Server(DEFAULT_PORT);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
        context.setParent(applicationContext);
        context.register(WebConfiguration.class);

        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath(CONTEXT_PATH);
        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        handler.addEventListener(new ContextLoaderListener(context));

        server.setHandler(handler);
        server.start();
        server.join();
    }
}