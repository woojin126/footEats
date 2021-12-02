package com.sparta.fooreats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseOrdered {
    private Long orderId;
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
    private List<OrderFood> foods;


    public ResponseOrdered(Long orderId, String restaurantName, int deliveryFee, int totalPrice) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
