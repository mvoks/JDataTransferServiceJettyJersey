package org.mvoks.datatransfer;

import java.net.URI;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.config.BindingModule;
import org.mvoks.datatransfer.config.yaml.JettyProperties;
import org.mvoks.datatransfer.config.yaml.YamlService;
import org.mvoks.datatransfer.security.UserRequestFilter;

@Singleton
@Service
@Log4j2
public class DataTransferApplication {

    @Inject
    private YamlService yamlService;

    void perform() {
        final JettyProperties jettyProperties = yamlService.getYamlProperties().getJetty();
        final String baseURI = String.join("",
            jettyProperties.getHost(), ":", Integer.toString(jettyProperties.getPort()), "/"
        );
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("org.mvoks.datatransfer.controller");
        resourceConfig.packages("org.mvoks.datatransfer.exception");
        resourceConfig.register(UserRequestFilter.class);
        resourceConfig.register(new BindingModule());
        final Server server = JettyHttpContainerFactory.createServer(URI.create(baseURI), resourceConfig);
        log.info("The '{}' started", this.getClass().getSimpleName());
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
        waiting();
    }

    private void waiting() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}