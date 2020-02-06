package com.order.status.controller;

import com.order.status.service.GetStatus;
import com.order.status.vo.Orders;
import com.order.status.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CheckStatus {

    @Autowired
    GetStatus getStatus;

    @PostMapping("/userorder")
    public String CheckStatus(@RequestBody Orders orders) {
        return getStatus.OrderStatus(orders);
    }

    @PostMapping("/getorders")
    public List<Orders> getOrder(@RequestBody User user ){
        List<Orders>order=getStatus.getAllOrders(user);
        System.out.println(order);
        return order;
    }
}
