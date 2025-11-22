package com.erdal.realTalk.server;

import java.net.URI;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

public class ServerInitializer {

    public static void startServer() {
        String host = System.getenv().getOrDefault("SERVER_HOST", "0.0.0.0");
        String port = System.getenv().getOrDefault("SERVER_PORT", "8080");
        String baseUri = "http://" + host + ":" + port + "/";

        URI uri = URI.create(baseUri);
        JettyHttpContainerFactory.createServer(uri, new ServerConfig());

        System.out.println("Server started at " + uri);
    }
}
