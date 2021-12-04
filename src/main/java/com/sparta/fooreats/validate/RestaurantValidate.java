package com.sparta.fooreats.validate;

import com.sparta.fooreats.dto.RestaurantRequestDto;


public class RestaurantValidate {
    public static void RegisterMoneyLimit(RestaurantRequestDto requestDto) {
        if (requestDto.getMinOrderPrice() < 1000 || requestDto.getMinOrderPrice() > 100000 || (requestDto.getMinOrderPrice() % 100 != 0))
            throw new IllegalArgumentException("최소주문 가격은 1000~100,000이고 , 100원 단위로 입력해주세요");
        else if (requestDto.getDeliveryFee() < 0 || requestDto.getDeliveryFee() > 10000 || (requestDto.getDeliveryFee() % 500 != 0))
            throw new IllegalArgumentException("기본 배달비는 0 ~ 10000원 이고, 500원 단위로만 입력 해주세요");
    }

}
