package com.liberbox.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ErrorValidationHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Details> handleConflict(RuntimeException exception) {

        return new ResponseEntity<>(
                Details.builder().status(HttpStatus.BAD_REQUEST.value()).title("Bad Request Exception")
                        .developerMessage(exception.getClass().getName() + " " + exception.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Details> handleConflict(DataIntegrityViolationException exception) {

        return new ResponseEntity<>(Details.builder().status(HttpStatus.BAD_REQUEST.value())
                .title("Check the registered data, there may be unique records already registered in the system.")
                .developerMessage(exception.getClass().getName() + " " + exception.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        toLog(ex, request);
        var error = Details.builder().status(HttpStatus.BAD_REQUEST.value()).title("Bad Request Exception")
                .developerMessage(ex.getClass().getName()).developerMessage(ex.getMessage().getClass().getName())
                .build();

        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        toLog(ex, request);
        List<Name> errors = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(new Name(name, message));
        }
        var error = Details.builder().status(HttpStatus.BAD_REQUEST.value()).title("Bad Request Exception")
                .developerMessage(ex.getClass().getName()).time(LocalDateTime.now()).errors(errors).build();

        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    private void toLog(Exception exception, WebRequest webRequest) {

        if (webRequest instanceof ServletWebRequest) {
            ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
            HttpServletRequest request = servletWebRequest.getRequest();

            String userId = request.getHeader("X-UserID");

            log.error("Method: " + request.getMethod() +
                    ", path: " + request.getServletPath() +
                    ", userId: " + userId +
                    ", EXCEPTION: " + exception.getMessage());
        } else {
            log.error("Exception: " + exception.getMessage());
        }
    }
}
