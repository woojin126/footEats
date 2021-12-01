package com.sparta.fooreats.repository;

import com.sparta.fooreats.dto.ResponseOrdered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<ResponseOrdered> findFoodInfo(List<Long> id){
        return em.createQuery(
                " select new com.sparta.fooreats.dto.ResponseOrdered " +
                        " ( f.name, o.quantity , o.totalPrice ) " +
                        " from OrderFoods o " +
                        " join o.foods f " +
                        " where o.orders.id in :id " , ResponseOrdered.class)
                .setParameter("id", id)
                .getResultList();
    }


}
