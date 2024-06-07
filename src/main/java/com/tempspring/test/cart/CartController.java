package com.tempspring.test.cart;

import com.tempspring.test.cart.dto.CartResponse;
import com.tempspring.test.cart.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 쿠키, 세션 학습 - 임시 장바구니 구현
 */
@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    /**
     * Cookie Version
     * */
    @PostMapping("/create/cookie")
    public ResponseEntity<CartResponse> createCart_Cookie(
            HttpServletResponse response,
            @RequestParam Long itemId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.createCart(response, itemId, quantity));
    }

    @GetMapping("/{cartId}/cookie")
    public ResponseEntity<CartResponse> viewCart_Cookie(
            HttpServletRequest request,
            @PathVariable Long cartId
    ) {
        return ResponseEntity.ok(cartService.getCart(request, cartId));
    }

    /**
     * Session Version
     * */
    @PostMapping("/create/session")
    public ResponseEntity<CartResponse> createCart_Session(
            @RequestParam Long itemId,
            @RequestParam int quantity) {
        CartResponse response = cartService.createCart_Session(itemId, quantity);
        return ResponseEntity.ok(cartService.createCart_Session(itemId, quantity));
    }

    @GetMapping("/{cartId}/session")
    public ResponseEntity<CartResponse> viewCart_Session(
            HttpServletRequest request,
            @PathVariable Long cartId
    ) {
        return ResponseEntity.ok(cartService.getCart_Session(request, cartId));
    }
}
