package com.tempspring.test.adapter;

import com.tempspring.test.adapter.service.OpenApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 기존 Adapter 모듈 대신 임의 테스트 용도
 * */
@RestController
@AllArgsConstructor
@RequestMapping("/adapter")
public class AdapterController {

    private OpenApiService openApiService;

    @GetMapping("/open-api")
    public ResponseEntity<String> getOpenApiData() {
        return ResponseEntity.ok(openApiService.getOpenApiData());
    }
}