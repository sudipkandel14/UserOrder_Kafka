package com.order.status.service;

import com.order.status.dao.OrderRepo;
import com.order.status.dao.UserRepo;
import com.order.status.dao.entity.OrderEntity;
import com.order.status.dao.entity.UserEntity;
import com.order.status.producer.Producer;
import com.order.status.vo.Orders;
import com.order.status.vo.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
       if(orderEntity.isPresent()){
           if(orderEntity.get().getCode()!=203) {
               producer.produceMessage(orderEntity.get().getStatus());
               return orderEntity.get().getStatus();
           }
           else
           {
               producer.produceMessage("Order already refunded");
               return orderEntity.get().getStatus();
           }
       }
        producer.produceMessage("No such order are placed");
       return "No such order are placed";
    }

    public List<Orders> getAllOrders(User user) {
        producer.produceMessage("Getting list of order you placed");
        List<Orders>list = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        ArrayList<OrderEntity> orderEntity = orderRepo.findByUserEntity(userEntity);
        for (int i =0;i<orderEntity.size();i++) {
            Orders order = new Orders();
            BeanUtils.copyProperties(orderEntity.get(i), order);
            list.add(order);
        }

        return list;
    }
}
