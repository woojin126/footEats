package com.sparta.fooreats.controller;

import com.sparta.fooreats.domain.Foods;
import com.sparta.fooreats.domain.Restaurants;
import com.sparta.fooreats.dto.FoodsDto;
import com.sparta.fooreats.repository.FoodsRepository;
import com.sparta.fooreats.repository.RestaurantRepository;
import com.sparta.fooreats.service.FoodsService;
import com.sparta.fooreats.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FoodsController {

    private final FoodsService foodsService;
    private final FoodsRepository foodsRepository;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFoods(@PathVariable Long restaurantId, @RequestBody List<FoodsDto> foodsDto){
         foodsService.save(restaurantId, foodsDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Foods> SearchFoods(@PathVariable Long restaurantId){
        Restaurants restaurants = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("레스토랑 아이디가 없습니다"));
        return foodsRepository.findAllByRestaurantId(restaurantId);
    }

}
