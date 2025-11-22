package com.erdal.realTalk.common.exception;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.erdal.realTalk.common.response.ErrorResponse;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Context
    private HttpServletResponse servletResponse;

    @Override
    public Response toResponse(Exception exception) {
        // Eğer response commit edilmişse, yeni bir response gönderme
        if (servletResponse.isCommitted()) {
            // Commit edilmiş response varsa logla ve null döndür
            System.err.println("Response already committed: " + exception.getMessage());
            return null;
        }

        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(errorResponse)
                       .build();
    }
}
