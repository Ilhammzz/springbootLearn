package com.indocyber.validation.error;
import com.indocyber.validation.error.dto.ErrorMessageResponse;
import com.indocyber.validation.shared.exception.ResourceNotFoundException;
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
    public ResponseEntity<ErrorMessageResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorMessageResponse<Object> dto = ErrorMessageResponse.builder()
                .status(httpStatus)
                .message(e.getMessage())
                .errors(e.getCause())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;

        var errorsMap = new HashMap<String, String>();

        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        ErrorMessageResponse<Map<String, String>> dto = ErrorMessageResponse.<Map<String, String>>builder()
                .status(httpStatus)
                .message("Input invalid")
                .errors(errorsMap)
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }
}
