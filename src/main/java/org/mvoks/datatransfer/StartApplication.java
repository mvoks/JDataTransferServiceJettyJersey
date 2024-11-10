package org.mvoks.datatransfer;

import java.net.URI;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.mvoks.datatransfer.rest.ResourceV1;

@Log4j2
public class StartApplication {

    static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) {
        final StartApplication startApplication = new StartApplication();
        startApplication.startServer();
        startApplication.join();
    }

    private void startServer() {
        final ResourceConfig resourceConfig = new ResourceConfig(ResourceV1.class);
        final Server server = JettyHttpContainerFactory.createServer(URI.create(BASE_URI), resourceConfig);
        log.info("Application started");
        log.info("Stop the application using CTRL+C");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                log.info("Shutting down the application...");
                server.stop();
                log.info("Done, exit.");
            } catch (Exception ex) {
                log.error(ex);
            }
        }));
    }

    private void join() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            log.error(ex);
        }
    }
}