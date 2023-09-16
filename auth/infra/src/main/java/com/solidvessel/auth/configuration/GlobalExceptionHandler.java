package com.solidvessel.auth.configuration;

import com.solidvessel.auth.common.exception.AuthDomainException;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<OperationResult> handleAllUncaughtException(AuthDomainException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new OperationResult(exception.getMessage(), ResultType.ERROR));
    }
}
