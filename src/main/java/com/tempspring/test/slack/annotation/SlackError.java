package com.tempspring.test.slack.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 슬랙 알림 어노테이션
 *
 * @see com.tempspring.test.slack.aspect.SlackAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SlackError {
}