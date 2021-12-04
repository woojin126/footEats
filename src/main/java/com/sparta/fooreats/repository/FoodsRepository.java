package com.sparta.fooreats.repository;

import com.sparta.fooreats.domain.Foods;
import com.sparta.fooreats.domain.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodsRepository extends JpaRepository<Foods,Long> {

    //이름과, 레스토랑 아이디 존재여부 확인
    Boolean existsByNameAndRestaurantId(@Param("name") String name, @Param("restaurantId") Long restaurantId);

    //해당 레스토랑 키에 대한 모든 음식정보를 가져온다.
    List<Foods> findAllByRestaurantId(@Param("restaurantId") Long restaurantId);

    //해당 레스토랑에 어떤 음식을 찾는지
    Optional<Foods> findByIdAndRestaurant(Long id, Restaurants restaurants);
}
