package com.tempspring.test.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @history 로켓 채팅 의빈님 셋팅 (feat. Kotlin)

@Configuration
class KotlinSwaggerConfig {

    /**
     * Docket을 사용하는 것이 더 효과적이며 일반적인 방법이다.
     * 그러나 간단한 애플리케이션이나 설정만 필요한 경우에는 OpenAPI를 직접 사용할 수도 있다.
     */
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(Components())
        .info(apiInfo())


    private fun apiInfo() =
        Info()
            .title("학습 스터디 Controller API 명세서")
            .description("[설명] Swagger API 명세서 입니다.")
            .version("1.0.0")

}
 * */