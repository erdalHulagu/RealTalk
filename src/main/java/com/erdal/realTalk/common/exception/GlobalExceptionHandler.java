package com.erdal.realTalk.common.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.erdal.realTalk.common.response.ErrorResponse;


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
