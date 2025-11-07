package com.indocyber.rest.error;

import com.indocyber.rest.shared.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageDto<Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorMessageDto<Object> dto = ErrorMessageDto.builder()
                .status(httpStatus)
                .message(e.getMessage())
                .errors(e.getCause())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;

        var errorsMap = new HashMap<String, String>();

        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        ErrorMessageDto<Map<String, String>> dto = ErrorMessageDto.<Map<String, String>>builder()
                .status(httpStatus)
                .message("Input invalid")
                .errors(errorsMap)
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }
}
