package com.sparta.fooreats.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
public class Foods {
//푸드에 김우진
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_Id", nullable = false)
    private Restaurants restaurant;

    @Builder
    public Foods(Long id,String name, int price, Restaurants restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }
}

