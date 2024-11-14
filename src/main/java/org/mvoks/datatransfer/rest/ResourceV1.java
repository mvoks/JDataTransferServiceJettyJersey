package org.mvoks.datatransfer.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.mvoks.datatransfer.config.yaml.YamlService;

@Path("/datatransfer/v1")
public class ResourceV1 {

    @Inject
    private YamlService yamlService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Jetty & Jersey example.";
    }
}