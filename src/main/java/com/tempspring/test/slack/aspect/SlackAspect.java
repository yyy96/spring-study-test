package com.tempspring.test.slack.aspect;

import com.tempspring.test.slack.dto.ErrorAlertMessage;
import com.tempspring.test.slack.sender.SlackSender;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Method;

@Aspect // Aspect 정의, Pointcut 정의
@Component // 빈으로 관리 하므로 @Component 정의
@RequiredArgsConstructor
public class SlackAspect {

    private final SlackSender slackSender;

    /**
     * @param joinPoint Advice 가 적용될 위치
     *                  Spring AOP 프레임워크가 Aspect 메서드를 호출할 때 자동으로 주입 (실행될 데이터의 메타데이터 포함)
     *                  만약 메서드 실행제어가 필요하다면 ProceedingJoinPoint 선언 필요
     * @SlackError 어노테이션이 붙은 메서드엔 슬랙 알림
     * @see com.tempspring.test.common.handler.GlobalExceptionHandler
     */
    // 타켓이 수행한 내용 결과에 관계없이 수행이 끝나면 Advice 가 적용된다.
    @After("@annotation(com.tempspring.test.slack.annotation.SlackError)")
    public void sendSlackErrorMessage(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();

        /*
         * Annotation 이 GlobalExceptionHandler 에 붙기 때문에, Exception 이 실제로 발생한 메서드가 아닌, 해당 메서드에 대한 정보로 내려올 것임.
         * 때문에 GlobalExceptionHandler 의 메서드 arguments 순서대로 정의
         * 참조 : handleBusinessException(HttpServletRequest request, BusinessException e) { }
         * */
        final HttpServletRequest httpServletRequest = (HttpServletRequest) args[0];
        final Exception exception = (Exception) args[1];
        final HttpStatus httpStatus = getHttpStatus(joinPoint);

        slackSender.send(
                ErrorAlertMessage.of(
                        httpStatus,
                        SlackSender.createSlackErrorMessage(httpServletRequest, httpStatus, exception)
                )
        );
    }

    /**
     * 기본 에러코드(500)으로 설정하고 @ResponseStatus에 에러코드가 있으면 해당 에러코드로 세팅
     *
     * @param joinPoint
     * @return
     */
    private HttpStatus getHttpStatus(JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        final ResponseStatus responseStatus = method.getAnnotation(ResponseStatus.class);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (!ObjectUtils.isEmpty(responseStatus)) {
            httpStatus = responseStatus.value();
        }
        return httpStatus;
    }
}