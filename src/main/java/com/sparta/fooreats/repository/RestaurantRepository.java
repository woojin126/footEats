package com.sparta.fooreats.repository;

import com.sparta.fooreats.domain.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurants,Long> {
}
