package com.sparta.fooreats.dto;

import com.sparta.fooreats.domain.Foods;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class RestaurantRequestDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}
