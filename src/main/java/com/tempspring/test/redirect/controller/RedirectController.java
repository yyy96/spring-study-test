package com.tempspring.test.redirect.controller;

import com.tempspring.test.common.annotation.QueryRequestParam;
import com.tempspring.test.redirect.service.RedirectPageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redirect")
@AllArgsConstructor
public class RedirectController {

    private final RedirectPageService redirectPageService;

    /**
     * 커스텀 어노테이션 & 리졸버 연습
     *
     * @RequestParam 대신 @CustomRequestParam 적용
     * @Test http://localhost:8089/redirect/q?url=https://search.zum.com/search.zum?query=%EC%9E%84%EC%98%81%EC%9B%85&qm=g_real1.top&method=uni
     */
    // 기존 @RequestParam
    // case) RequestParam 내장된 라이브러리에서 쿼리를 자르고 & 한글의 경우 디코딩을 한 상태로 내려줘서 redirect 실패
    // case) redirect url : https://search.zum.com/search.zum?query=임영웅 -> Http11Processor header exception !
    @GetMapping()
    public ResponseEntity redirectUrl(
            @RequestParam(required = false) String url,
            HttpServletResponse httpServletResponse
    ) {
        redirectPageService.redirectUrl(httpServletResponse, url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // QueryString 을 받을 수 있는 @QueryRequestParam
    // case) RequestParam 을 사용하지 않아 디코딩을 따로 해주지 않았으므로 한글의 경우 인코딩 상태로 내려줘서 redirect 성공
    @GetMapping("/q")
    public ResponseEntity redirectQueryStringUrl(
            @QueryRequestParam(required = false, defaultValue = "https://google.com") String url,
            HttpServletResponse httpServletResponse
    ) {
        redirectPageService.redirectUrl(httpServletResponse, url);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}