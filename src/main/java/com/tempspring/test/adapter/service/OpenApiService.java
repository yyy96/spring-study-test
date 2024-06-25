package com.tempspring.test.adapter.service;

import com.tempspring.test.adapter.dto.OpenApiResponse;
import com.tempspring.test.adapter.feignclient.OpenApiFeignClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OpenApiService {

    private final OpenApiFeignClient openApiFeignClient;

    public String getOpenApiData() {
        try {
            ResponseEntity<OpenApiResponse> response = openApiFeignClient.getOpenApiResponse("v2");

            if (response.getStatusCode().is5xxServerError()) {
                log.error("OpenApiResponse statusCode is5xxServerError");
                return null;
            }

            log.info("response.getBody() : {}", response.getBody().id());
            return response.getBody().data();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return null;
    }
}