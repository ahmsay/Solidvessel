package com.solidvessel.payment.configuration;

import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<OperationResult> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new OperationResult(exception.getMessage(), ResultType.ERROR));
    }

    @ExceptionHandler(PaymentDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<OperationResult> handleDomainExceptions(PaymentDomainException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new OperationResult(exception.getMessage(), ResultType.ERROR));
    }
}
