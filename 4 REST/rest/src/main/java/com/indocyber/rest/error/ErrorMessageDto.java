package com.indocyber.rest.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@Builder
public class ErrorMessageDto<T> {
    private final HttpStatus status;
    private final String message;
    private final T errors;
    private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Z"));
//    private final Throwable throwable;
}
