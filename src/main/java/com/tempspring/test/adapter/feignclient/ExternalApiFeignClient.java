package com.tempspring.test.adapter.feignclient;

import com.tempspring.test.adapter.config.FeignConfig;
import com.tempspring.test.adapter.dto.ExternalApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "externalApiFeignClient", url = "${api.url.external-api}", configuration = FeignConfig.class)
public interface ExternalApiFeignClient {

    /**
     * 외부 api 연동
     */
    @GetMapping(value = "/api/{id}")
    ResponseEntity<ExternalApiResponse> getExternalApiInfo(@PathVariable String id);

}