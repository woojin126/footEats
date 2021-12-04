package com.sparta.fooreats.domain;

import com.sparta.fooreats.dto.RestaurantRequestDto;
import com.sparta.fooreats.validate.RestaurantValidate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int minOrderPrice;

    private int deliveryFee;

    public Restaurants(RestaurantRequestDto requestDto){
        RestaurantValidate.RegisterMoneyLimit(requestDto);
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

}
