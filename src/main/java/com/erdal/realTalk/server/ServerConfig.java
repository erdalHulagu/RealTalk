package com.erdal.realTalk.server;

import org.glassfish.jersey.server.ResourceConfig;

public class ServerConfig extends ResourceConfig {

	 public ServerConfig() {
	        packages(
	            "com.erdal.realTalk.user.controller",
	            "com.erdal.realTalk.common.exception"
	        );
	 }
}

