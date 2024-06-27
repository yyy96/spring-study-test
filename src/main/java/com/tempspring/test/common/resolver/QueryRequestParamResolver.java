package com.tempspring.test.common.resolver;

import com.tempspring.test.common.annotation.QueryRequestParam;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class QueryRequestParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("CustomRequestParamResolver - supportsParameter start");
        return parameter.hasParameterAnnotation(QueryRequestParam.class);
    }

    /**
     * @CustomRequestParam 커스텀해서 들어올 수 있는 파라미터 값
     * String string = "https://.."
     * String queryString = "{\ "param\" : \"https://...\"}",
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("CustomRequestParamResolver - resolveArgument start");

        final QueryRequestParam annotation = parameter.getParameterAnnotation(QueryRequestParam.class);

        final String paramName = parameter.getParameterName();
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String queryString = request.getQueryString();
        String requestParam = request.getParameter(paramName);
        log.info("HttpServletRequest.getQueryString() : {}", queryString);

        // 해당 어노테이션이 필수 요청값인지 확인 후 & 설정한 default 값 혹은 400 error 반환
        if (requestParam == null || queryString == null) {
            if (!annotation.required()) return annotation.defaultValue();
            throw new RuntimeException();
        }
        requestParam = queryString.split("url=")[1];
        return requestParam;
    }
}