package com.status.refund.service;


import com.status.refund.dao.OrderRepo;
import com.status.refund.dao.UserRepo;
import com.status.refund.dao.entity.OrderEntity;
import com.status.refund.producer.Producer;
import com.status.refund.vo.Orders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetStatus {

    @Autowired
    Producer producer;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;

    public String OrderStatus(Orders orders){
       Optional<OrderEntity> orderEntity = orderRepo.findById(orders.getOrder_id());
       OrderEntity orderEntity1 = new OrderEntity();
       if(orderEntity.isPresent()){
           BeanUtils.copyProperties(orderEntity.get(),orderEntity1);
           if(orderEntity.get().getCode()!=203)
           {
               orderEntity1.setStatus("Order Refunded");
               orderEntity1.setCode(203);
               orderRepo.save(orderEntity1);
           }
            producer.produceMessage("Refund will be credited to user");
           return orderEntity.get().getStatus();
       }
        producer.produceMessage("No such order are placed");
         return "No such order are placed";
    }
}
