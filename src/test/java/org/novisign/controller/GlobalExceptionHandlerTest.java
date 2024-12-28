package org.novisign.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.novisign.controller.result.ErrorResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleDuplicateKeyException() {
        DuplicateKeyException exception = new DuplicateKeyException("Duplicate key error");
        WebRequest request = Mockito.mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleDuplicateKeyException(exception, request);

        assertEquals(409, response.getStatusCodeValue());
        assertEquals("CONFLICT", ((ErrorResponse) response.getBody()).getCode());
    }

}