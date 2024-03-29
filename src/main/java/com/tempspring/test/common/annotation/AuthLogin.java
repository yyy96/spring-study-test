package com.tempspring.test.common.annotation;

import com.tempspring.test.common.resolver.LoginArgumentResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 해당 annotation이 선언 되어 있는 경우 LoginResolver 를 통해 세션에서 로그인 회원을 반환한다.
 * @see LoginArgumentResolver
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME) // 리플렉션 활용을 위해 런타임까지 애노테이션 정보가 남아있는다.
public @interface AuthLogin {
}