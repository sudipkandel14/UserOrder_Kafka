package com.status.refund.dao;


import com.status.refund.dao.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<OrderEntity,Integer> {
}
