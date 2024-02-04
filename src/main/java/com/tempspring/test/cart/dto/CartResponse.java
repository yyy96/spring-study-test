package com.tempspring.test.cart.dto;

import com.tempspring.test.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private Long cartId;
    private List<ItemResponse> items;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemResponse {
        Long itemId;
        int quantity;

        public static ItemResponse of(Long itemId, int quantity) {
            return new ItemResponse(itemId, quantity);
        }
    }

    public static CartResponse of(Long cartId) {
        return CartResponse.builder()
                .cartId(cartId)
                .build();
    }

    public static CartResponse of(Cart cart) {
        List<ItemResponse> itemResponses = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : cart.getItems().entrySet()) {
            Long key = entry.getKey();
            int value = entry.getValue();
            itemResponses.add(ItemResponse.of(key, value));
        }

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(itemResponses)
                .build();
    }

    public static CartResponse of(Long cartId, List<ItemResponse> items) {
        return new CartResponse(cartId, items);
    }
}
