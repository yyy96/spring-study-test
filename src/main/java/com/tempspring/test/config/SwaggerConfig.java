package com.tempspring.test.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @history springdoc-openapi-ui 의존성이기 때문에 다음과 같은 설정임.
 * [참고 블로그] https://colabear754.tistory.com/99
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Swagger 문서 제목")
                .description("Swagger 문서 설명")
                .version("1.0");
    }

}
