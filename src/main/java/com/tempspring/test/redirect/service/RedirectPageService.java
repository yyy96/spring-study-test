package com.tempspring.test.redirect.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedirectPageService {

    public void redirectUrl(HttpServletResponse httpServletResponse, String url) {
        log.info("redirect url : {}", url);

        try {
            httpServletResponse.sendRedirect(url);
        } catch (IOException e) {
            log.error("Fail to redirect Login process e : {}", e.getMessage());
        }
    }
}