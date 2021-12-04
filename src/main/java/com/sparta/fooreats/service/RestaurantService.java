package com.sparta.fooreats.service;

import com.sparta.fooreats.domain.Restaurants;
import com.sparta.fooreats.dto.RestaurantRequestDto;
import com.sparta.fooreats.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurants save(RestaurantRequestDto restaurantRequestDto) {
        Restaurants restaurants = new Restaurants(restaurantRequestDto);
        return restaurantRepository.save(restaurants);
    }
}
