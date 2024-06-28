package com.tempspring.test.slack.sender;

import com.tempspring.test.adapter.dto.SlackSendRequest;
import com.tempspring.test.adapter.feignclient.SlackFeignClient;
import com.tempspring.test.slack.dto.ErrorAlertMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @see <a href="https://api.slack.com/apis">Slack Notification API 참고자료 </a>
 * slack 으로 message를 전송하기 위해서 사용하는 class
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SlackSender implements ErrorSender {

    // webClient 로 보낼 때는 여기서 관리
//    @Value("${slack.error.webhook.base-url}")
//    private String slackBaseUrl;
//
//    @Value("${slack.error.webhook.token}")
//    private String secretToken;
//
//    @Value("${slack.error.channel}")
//    private String channel;
    private final SlackFeignClient slackFeignClient;

    @Override
    public void send(ErrorAlertMessage message) {
        ResponseEntity result = slackFeignClient.sendMessage(SlackSendRequest.of(message.getMessage()));

        if (result.getStatusCode().is2xxSuccessful()) {
            log.info("Success to send Slack Error Message");
        } else {
            log.error("Fail to send Slack Error Message, result : {}", result.getBody());
        }
    }


    /**
     * @return message
     * - 1. DateTime : 2024-05-31 16:43:41
     * - 2. Request : [GET] http://localhost:8082/ipo/list?year=2021&month=06
     * - 3. Response : [503] 997 인증에 실패 하였습니다.
     * - 4. Exception : com.tempspring.test.common.exception.ApiSystemException: 인증에 실패 하였습니다.
     */
    public static String createSlackErrorMessage(HttpServletRequest httpServletRequest,
                                                 HttpStatus status,
                                                 Exception e) {
        return "1. DateTime: " + LocalDateTime.now() + "\n" +
                "2. Request : [" + httpServletRequest.getMethod() + "] " + getRequestUrl(httpServletRequest) + "\n" +
                "3. Response : [" + status + "] " + e.getMessage() + "\n" +
                "4. Exception : *" + e + "* \n" +
                "```" + getStackTrace(e) + "```";
    }

    private static String getRequestUrl(HttpServletRequest httpServletRequest) {
        final StringBuffer requestURL = httpServletRequest.getRequestURL();
        final String queryString = httpServletRequest.getQueryString();

        return queryString == null ? requestURL.toString() : requestURL.append('?').append(queryString).toString();
    }

    private static StringBuilder getStackTrace(Exception exception) {
        StringBuilder result = new StringBuilder();
        StackTraceElement[] stackTrace = exception.getStackTrace();
        int length = stackTrace.length;

        for (int i = 0; i < Math.min(length, 25); i++) {
            if (i + 1 == length)
                result.append(stackTrace[i].toString());
            else
                result.append(stackTrace[i].toString() + "\n");
        }
        return result;
    }
}