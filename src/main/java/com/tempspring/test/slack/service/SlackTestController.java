package com.tempspring.test.slack.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController("/slack")
public class SlackTestController {

    private final SlackTestService slackService;

    @GetMapping("/error-message")
    public void testSendErrorMessage() {
        slackService.testSendErrorMessage();
    }
}