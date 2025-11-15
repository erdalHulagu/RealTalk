package com.erdal.realTalk.server;

import java.net.URI;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

public class ServerInitializer {

    private static final String BASE_URI = "http://localhost:8080/";

    public static void startServer() {
        URI uri = URI.create(BASE_URI);
        JettyHttpContainerFactory.createServer(uri, new ServerConfig());
        System.out.println("Server started at " + uri);
    }
}
