package com.tempspring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
// @EnableAspectJAutoProxy  -> 스프링 부트가 implements spring-boot-starter-aop 통해서 자동으로 처리하기 때문에 미잗성해도 됨.
public class TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
