package com.tempspring.test.adapter.dto;

import java.time.LocalDateTime;

/**
 * FeignClient Test
 * */
public record OpenApiResponse(
        Long id,
        String data,
        LocalDateTime createdTime
) {
}