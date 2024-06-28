package com.tempspring.test.adapter.feignclient;

import com.tempspring.test.adapter.dto.SlackSendRequest;
import com.tempspring.test.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "slackApiFeignClient", url = "${slack.error.webhook.base-url}", configuration = FeignConfig.class)
public interface SlackFeignClient {

    /**
     * 슬랙 전송
     */
    @PostMapping(value = "/${slack.error.webhook.token}")
    ResponseEntity sendMessage(@RequestBody SlackSendRequest slackSendRequest);
}