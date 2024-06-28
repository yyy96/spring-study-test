package com.tempspring.test.common.exception.dto;

import com.tempspring.test.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
        HttpStatus status,
        String message

) {
    public static ErrorResponse of(ErrorCode e) {
        return new ErrorResponse(e.getHttpStatus(), e.getMessage());
    }

    public static ErrorResponse of(Exception e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage());
    }
}