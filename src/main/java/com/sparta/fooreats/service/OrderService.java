package com.sparta.fooreats.service;


import com.sparta.fooreats.domain.Foods;
import com.sparta.fooreats.domain.OrderFoods;
import com.sparta.fooreats.domain.Ordered;
import com.sparta.fooreats.domain.Restaurants;
import com.sparta.fooreats.dto.OrderFood;
import com.sparta.fooreats.dto.OrderRequestDto;
import com.sparta.fooreats.dto.ResponseOrdered;
import com.sparta.fooreats.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodsRepository foodsRepository;
    private final RestaurantRepository restaurantRepository;


    //주문 상품 저장
    @Transactional
    public ResponseOrdered saveOrder(OrderRequestDto orderRequestDto) {
        Restaurants restaurants = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(() -> new IllegalArgumentException("레스토랑 아이디가 없습니다."));
        List<OrderRequestDto.FoodsDtos> foods = orderRequestDto.getFoods();
        List<OrderFoods> orderFoods = new ArrayList<>();
        Ordered ordered = Ordered.orders(restaurants, orderFoods);
        for (OrderRequestDto.FoodsDtos food : foods) {
            Foods foodOne = foodsRepository.findByIdAndRestaurant(food.getId(), restaurants).orElseThrow(() -> new IllegalArgumentException("레스토랑 아이디가 존재하지 않습니다."));
            //주문 상품 생성
            OrderFoods orderFood = OrderFoods.createOrderFood(foodOne, food.getQuantity(), ordered);
            //주문 생성
            orderFoods.add(orderFood);
        }
        //주문한 메뉴 총 가격
        int allMoney = orderFoods.stream().mapToInt(OrderFoods::getTotalPrice).sum();

        //주문한 메뉴와 배달비를 포함한 총 가격
        int deliveryAndFoodPrice = allMoney + (restaurants.getDeliveryFee());
        if (restaurants.getMinOrderPrice() > allMoney)
            throw new IllegalArgumentException("주문 음식 가격들의 총 합 이 주문 음식점의 최소주문 가격 을 넘지 안습니다");

        ordered.addTotalPrice(deliveryAndFoodPrice);
        orderRepository.save(ordered);
        //레스토랑 아이디에 해당하는 모든 주문 아이디 가져오기
        List<Long> orderIdCollect = orderRepository.findOrderIdCollect(restaurants.getId());
        // 주문 아이디를 사용해서 모든 주문 정보 가져오기
        List<OrderFood> orderFoodInfo = orderRepository.findFoodInfo(orderIdCollect);

        return ResponseOrdered.builder()
                .deliveryFee(restaurants.getDeliveryFee())
                .restaurantName(restaurants.getName())
                .totalPrice(deliveryAndFoodPrice)
                .foods(orderFoodInfo)
                .build();
    }

    //전체 주문 상품 조회
    public List<ResponseOrdered> findAllByOrderInfo() {
        List<ResponseOrdered> responseOrdereds = orderRepository.selectAll();
        List<Long> orderIds = responseOrdereds.stream().map(ResponseOrdered::getOrderId).collect(Collectors.toList());
        Map<Long, List<OrderFood>> orderFoodsMap = orderRepository.findFoodInfo(orderIds).stream().collect(Collectors.groupingBy(OrderFood::getOrderId));
        responseOrdereds.forEach(f -> f.setFoods(orderFoodsMap.get(f.getOrderId())));
        return responseOrdereds;
    }
}
