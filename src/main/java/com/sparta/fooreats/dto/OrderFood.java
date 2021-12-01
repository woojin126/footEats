package com.sparta.fooreats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderFood {
    private Long orderId;
    private String name;
    private int quantity;
    private int price;
}
