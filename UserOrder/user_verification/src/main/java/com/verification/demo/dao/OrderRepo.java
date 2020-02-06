package com.verification.demo.dao;

import com.verification.demo.dao.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<OrderEntity,Integer> {
}
