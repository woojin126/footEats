package com.sparta.fooreats.service;

import com.sparta.fooreats.domain.Foods;
import com.sparta.fooreats.domain.Restaurants;
import com.sparta.fooreats.dto.FoodsDto;
import com.sparta.fooreats.repository.FoodsRepository;
import com.sparta.fooreats.repository.RestaurantRepository;
import com.sparta.fooreats.validate.FoodsValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FoodsService {

    private final FoodsRepository foodsRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void save(Long restaurantId, List<FoodsDto> foodsDto) {
        restaurantRepository.findAll();
        Restaurants restaurants = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("레스토랑 아이디가 없습니다"));
        for (FoodsDto dto : foodsDto) {
            Boolean existsMenu = foodsRepository.existsByNameAndRestaurantId(dto.getName(), restaurantId);
            FoodsValidate.MenuExistValid(existsMenu, dto.getPrice());
            Foods build = Foods.builder()
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .restaurant(restaurants)
                    .build();
            foodsRepository.save(build);
        }
    }
}
