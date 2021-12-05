package com.sparta.fooreats.domain;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Ordered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderFoods> orderFoods = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_Id", nullable = false)
    private Restaurants restaurant;

    @Column(nullable = false)
    private int orderTotalPrice;


    public static Ordered orders(Restaurants restaurants, List<OrderFoods> orderFood) {
        int sum = orderFood.stream().mapToInt(OrderFoods::getTotalPrice).sum();
        Ordered ordered = new Ordered();
        ordered.setOrderFoods(orderFood);
        ordered.setRestaurant(restaurants);
        ordered.setOrderTotalPrice(sum);
        return ordered;
    }



}
