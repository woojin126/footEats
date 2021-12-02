package com.sparta.fooreats.controller;

import com.sparta.fooreats.dto.OrderRequestDto;
import com.sparta.fooreats.dto.ResponseOrdered;
import com.sparta.fooreats.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public ResponseOrdered postOrdered(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.saveOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<ResponseOrdered> findAllByOrdered(){
        return  orderService.findAllByOrderInfo();
    }
}
