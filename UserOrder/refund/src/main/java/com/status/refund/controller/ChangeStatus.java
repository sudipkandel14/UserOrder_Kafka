package com.status.refund.controller;


import com.status.refund.service.GetStatus;
import com.status.refund.vo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangeStatus {

    @Autowired
    GetStatus getStatus;

    @PostMapping("/userorder")
    public String ChangeStatus(@RequestBody Orders orders) {
        return getStatus.OrderStatus(orders);
    }
}
