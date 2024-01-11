package com.tempspring.test.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PayLogAspect {

    @Pointcut("execution(* com.tempspring.test.pay.service.*.*(..))")
    private void services() {}

    @Before("execution(* com.tempspring.test..*(..))")
    public void defaultLog() {
        //System.out.println("");
    }

    //@Before("services()")
    @Before("execution(* com.tempspring.test..*(..))")
    public void serviceLog(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println(className + " 실행");
    }
}
