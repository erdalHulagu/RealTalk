package com.erdal.realTalk.server;



import java.net.URI;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerInitializer {
	 private static final String BASE_URI = "http://localhost:8080/";

	 public static void startServer() {
	 ResourceConfig config = new ResourceConfig()
             .packages("com.erdal.realTalk.user.controller"); // your REST packages
	 

     URI baseUri = URI.create(BASE_URI);
     JettyHttpContainerFactory.createServer(baseUri, config);
     System.out.println("Server started at " + baseUri);
	 }
	    
	

}
