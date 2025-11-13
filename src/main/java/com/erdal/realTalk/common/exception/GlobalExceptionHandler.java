package com.erdal.realTalk.common.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		ErrorResponse errorResponse=new ErrorResponse( 
	            Response.Status.BAD_REQUEST.getStatusCode(),
	            exception.getMessage());
		return Response.status(Response.Status.BAD_REQUEST)
                .entity( errorResponse)
                .build();
	}

}
