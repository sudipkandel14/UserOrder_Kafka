package com.order.status.dao;

import com.order.status.dao.entity.OrderEntity;
import com.order.status.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface OrderRepo extends CrudRepository<OrderEntity,Integer> {
    public ArrayList<OrderEntity> findByUserEntity(UserEntity userEntity);
}
