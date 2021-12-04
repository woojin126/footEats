package com.sparta.fooreats.controller;

import com.sparta.fooreats.domain.Foods;
import com.sparta.fooreats.domain.Restaurants;
import com.sparta.fooreats.dto.FoodsDto;
import com.sparta.fooreats.dto.RestaurantRequestDto;
import com.sparta.fooreats.repository.RestaurantRepository;
import com.sparta.fooreats.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant/register")
    public Restaurants registerRestaurantGet(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.save(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurants> registerRestaurantPost() {
        return restaurantRepository.findAll();
    }


}
