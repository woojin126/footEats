package com.sparta.fooreats.repository;

import com.sparta.fooreats.domain.Ordered;
import com.sparta.fooreats.dto.OrderFood;
import com.sparta.fooreats.dto.ResponseOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Ordered,Long> {


    //주문 전체정보 조회에서 food정보를 제외한 나머지정보부터 조회
    @Query(value = "select new com.sparta.fooreats.dto.ResponseOrdered" +
            " ( o.id, o.restaurant.name, o.restaurant.deliveryFee, o.orderTotalPrice ) " +
            " from Ordered o " )
    List<ResponseOrdered> selectAll();

    //주문정보 전체조회에 사용하는데, 해당 주문아이디를 바인딩변수로 삽입하여 해당 주문에대한 모든 음식정보를 가져온다.
    @Query(value = " select new com.sparta.fooreats.dto.OrderFood" +
            "                         (o.orders.id ,f.name, o.totalPrice  ,o.quantity) " +
            "                         from OrderFoods o " +
            "                         join o.foods f " +
            "                         where o.orders.id in :id ")
    List<OrderFood> findFoodInfo(List<Long> id);

    //레스토랑 아이디에 해당하는 모든 주문 아이디 가져오기
    @Query(value = "select o.id from Ordered o where o.restaurant.id = :id" )
    List<Long> findOrderIdCollect(@Param("id") Long id);

}
