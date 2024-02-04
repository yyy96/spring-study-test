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

    @PostMapping("/create")
    public ResponseEntity<CartResponse> createCart(
            HttpServletResponse response,
            @RequestParam Long itemId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.createCart(response, itemId, quantity));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> viewCart(
            HttpServletRequest request,
            @PathVariable Long cartId
    ) {
        return ResponseEntity.ok(cartService.getCart(request, cartId));
    }
}
