package com.tempspring.test.slack.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorAlertMessage {

    private final HttpStatus status;
    private final String message;
    private final String detail;

    public static ErrorAlertMessage of(HttpStatus status, String message) {
        return ErrorAlertMessage.builder()
                .status(status)
                .message(message)
                .build();
    }
}