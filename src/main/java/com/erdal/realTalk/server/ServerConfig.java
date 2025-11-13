package com.erdal.realTalk.server;

import org.glassfish.jersey.server.ResourceConfig;

public class ServerConfig extends ResourceConfig{
	// for scaning packages to find  request endpoints
	  public ServerConfig() {  
	        packages("com.erdal.realTalk.common.exception");
	    }

}
