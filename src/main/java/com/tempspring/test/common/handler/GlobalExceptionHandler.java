package com.tempspring.test.common.handler;

import com.tempspring.test.common.exception.BusinessException;
import com.tempspring.test.common.exception.dto.ErrorResponse;
import com.tempspring.test.slack.annotation.SlackError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 단순 예제를 위해 커스터마이징 하지 않음
     *
     * @param request : SlackAspect 에서 사용하기 위해 선언 / 평소엔 필요 x
     * @param e       : 발생한 Exception
     */
    @SlackError
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    protected ErrorResponse handleBusinessException(HttpServletRequest request,
                                                    BusinessException e) {
        log.error("[GlobalExceptionHandler] BusinessException : ", e);
        return ErrorResponse.of(e);
    }
}