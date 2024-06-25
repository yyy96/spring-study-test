package com.tempspring.test.adapter.feignclient;

import com.tempspring.test.adapter.dto.OpenApiResponse;
import com.tempspring.test.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "externalApiFeignClient", url = "${api.url.my-open-api}", configuration = FeignConfig.class)
public interface OpenApiFeignClient {

    /**
     * 외부 api 연동
     */
    @GetMapping(value = "/open-api/{version}")
    ResponseEntity<OpenApiResponse> getOpenApiResponse(@PathVariable String version);

}