package com.tempspring.test.cart.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tempspring.test.cart.Cart;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionUtil {

    private final ObjectMapper objectMapper;
    private final HttpSession httpSession;

    private static final String CART_ATTRIBUTE = "CART";

    public void saveCartSession(Cart cart) {
        httpSession.setAttribute(CART_ATTRIBUTE, parseObjectToString(cart));
    }

    public Cart retrieveCartSession() {
        String value = (String) httpSession.getAttribute(CART_ATTRIBUTE);

        try {
            return objectMapper.readValue(value, Cart.class);
        } catch (IOException e) {
            log.error("Fail to deserialize cart session : {}", e, value);
        }
        return null;
    }

    private String parseObjectToString(final Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("Fail to parse cart to String : {}", e);
        }
        return "";
    }
}
