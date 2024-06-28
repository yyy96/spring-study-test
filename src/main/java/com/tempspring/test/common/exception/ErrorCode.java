package com.tempspring.test.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BUSINESS_ERROR(HttpStatus.BAD_REQUEST, "요청에 실패했습니다."),
    NOT_FOUND_DATA_ERROR(HttpStatus.NOT_FOUND, "정보가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}