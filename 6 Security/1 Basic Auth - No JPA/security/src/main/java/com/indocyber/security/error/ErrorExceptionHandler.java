package com.indocyber.security.error;
import com.indocyber.security.error.dto.ErrorMessageResponse;
import com.indocyber.security.shared.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
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

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthException(AuthenticationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Authentication failed");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Access denied");
        error.put("message", "You don't have permission to access this resource");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
