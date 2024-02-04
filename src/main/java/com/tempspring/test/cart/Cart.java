package com.tempspring.test.cart;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Cart {
    private Long id;
    private Map<Long, Integer> items;

    public static final String CART_COOKIE_KEY = "CART_KEY";

    public static Cart of() {
        return new Cart(0L, new HashMap<>());
    }

    public void addItemsToCart(Long itemId, int quantity) {
        items.put(itemId, items.getOrDefault(itemId, 0) + quantity);
    }

    public void createCartCookie(HttpServletResponse response, Cart cart) {
        Cookie cookie = new Cookie(CART_COOKIE_KEY, cart.getItems().toString());
        cookie.setMaxAge(3600); // 쿠키의 유효 시간을 설정 (초 단위)
        cookie.setPath("/"); // 쿠키의 경로 설정
        response.addCookie(cookie);
    }
}
