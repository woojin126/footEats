package com.sparta.fooreats.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequestDto {

    private Long restaurantId;
    private List<FoodsDtos> foods;


    @Data
    public static class FoodsDtos{
        private Long id;
        private int quantity;
    }

}
