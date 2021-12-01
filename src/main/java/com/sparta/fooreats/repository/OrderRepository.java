package com.sparta.fooreats.repository;

import com.sparta.fooreats.domain.Ordered;
import com.sparta.fooreats.dto.OrderFood;
import com.sparta.fooreats.dto.ResponseOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Ordered,Long> {

/*

    //총가격
    @Query(value = "select sum(o.totalPrice) from OrderFoods o where o.orders.id in :id ")
    int findAllMoney(List<Long> id);
*/


    @Query(value = " select new com.sparta.fooreats.dto.OrderFood" +
            "                         (o.orders.id ,f.name, o.quantity , o.totalPrice ) " +
            "                         from OrderFoods o " +
            "                         join o.foods f " +
            "                         where o.orders.id in :id ")
    List<OrderFood> findFoodInfo(List<Long> id);

    @Query(value = "select o.id from Ordered o where o.restaurant.id = :id" )
    List<Long> findOrderIdCollect(@Param("id") Long id);

/*
    @Query()
    ResponseOrdered findResNameAndDeliveryFeeAndTotalPrice();*/

}
