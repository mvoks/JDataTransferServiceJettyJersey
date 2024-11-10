package org.mvoks.datatransfer.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/datatransfer/v1")
public class ResourceV1 {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Jetty & Jersey example.";
    }
}