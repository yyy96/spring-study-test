package com.tempspring.test.cart.service;

import com.tempspring.test.cart.Cart;
import com.tempspring.test.cart.dto.CartResponse;
import com.tempspring.test.cart.dto.CartResponse.ItemResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    public CartResponse createCart(HttpServletResponse response, Long itemId, int quantity) {
        Cart cart = Cart.of();
        cart.addItemsToCart(itemId, quantity);
        cart.createCartCookie(response, cart); // response 에 대한 다른 return 은 없음. 단지 설정으로 끝
        return CartResponse.of(cart);
    }

    /**
     * 디비와 캐시를 전혀 사용하지 않아도, 클라이언트에게서 전달되온 쿠키를 통해
     * 쿠키에 저장되어 있는 것으로 확인하는 것.
     */
    public CartResponse getCart(HttpServletRequest request, Long cartId) {
        List<ItemResponse> itemResponses = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        // 현재 디비가 아니라 쿠키에 저장되어 있는 것으로 확인하는 것.
        for (Cookie cookie : cookies) {
            if (Cart.CART_COOKIE_KEY.equals(cookie.getName())) {
                // 현재 쿠키는 예시) {81231=1, 81999=12, 121233=32} 이렇게 담겨있음. {key=value, key=value, .. }
                String[] itemsInCart = cookie.getValue().replaceAll("[{}]", "").split(",\\s");
                String[] item = itemsInCart[0].split("=");

                System.out.println("key : " + item[0]);
                System.out.println("value : " + item[1]);

                itemResponses.add(ItemResponse.of(Long.parseLong(item[0]), Integer.parseInt(item[1])));
            }
        }
        return CartResponse.of(cartId, itemResponses);
    }
}
