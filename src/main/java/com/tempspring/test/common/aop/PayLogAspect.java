package com.tempspring.test.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PayLogAspect {

    // 어떤 포인트에 부가 가능을 적용할지 안할지 구분하는 필터링 로직
    @Pointcut("execution(* com.tempspring.test.pay.service.*.*(..))")
    private void services() {
//        log.info("Pointcut");
    }

    @Before("execution(* com.tempspring.test..*(..))")
    public void defaultLog() {
        //System.out.println("");
    }

    //@Before("services()")
    @Before("execution(* com.tempspring.test..*(..))")
    public void serviceLog(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        log.info("{} 실행", className);
    }
}
