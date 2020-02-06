package com.verification.demo.controller;

import com.verification.demo.responses.ServiceResponse;
import com.verification.demo.services.UserServe;
import com.verification.demo.vo.Orders;
import com.verification.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServe userServe;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userServe.getUsers();
    }

    @PostMapping("/users")
    public ServiceResponse addUser(@RequestBody User user) {
        userServe.addUser(user);
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setCode(200);
        serviceResponse.setMessage("User Added Successfully");
        return serviceResponse;
    }

    @PostMapping("/validuser")
    public boolean validUser(@RequestBody User user) {
        return userServe.validUser(user)?true:false;
    }

    @PostMapping("/userorder/{uid}")
    public ServiceResponse addOrder(@RequestBody Orders orders, @PathVariable Integer uid) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if(userServe.addOrder(orders, uid)){
        serviceResponse.setCode(200);
        serviceResponse.setMessage("Order Added Successfully");
        }else {
            serviceResponse.setCode(405);
            serviceResponse.setMessage("Invalid User/Order");
        }

        return serviceResponse;
    }

}
