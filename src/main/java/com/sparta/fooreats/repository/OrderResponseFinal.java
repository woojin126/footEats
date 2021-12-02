package com.sparta.fooreats.repository;

import com.sparta.fooreats.dto.OrderFood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseFinal {

    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
    private List<OrderFood> foods;


}
