package com.sparta.fooreats.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class OrderFoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;//주문수량

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_Id", nullable = false)
    private Ordered orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_Id", nullable = false)
    private Foods foods;

    public static OrderFoods createOrderFood(Foods foods, int quantity,Ordered ordered){

        if (quantity < 1 || quantity > 100)
            throw new IllegalArgumentException("수량은 1미만, 100 이상이 될 수 없습니다.");
        OrderFoods orderFoods = new OrderFoods();
        orderFoods.setFoods(foods);
        orderFoods.setOrders(ordered);
        orderFoods.setTotalPrice(orderFoods.foods.priceCarditionQuantity(quantity));
        orderFoods.setQuantity(quantity);
        return orderFoods;
    }


}
