package com.done.application.services;

import com.done.application.producer.Producer;
import com.done.application.vo.Orders;
import com.done.application.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FullService {
    @Autowired
    Producer producer;

   List<Orders>list= new ArrayList<>();

    public void validUser(User user) {
        final String uri = "http://localhost:8080/validuser";
        String message="Does not exist";
        RestTemplate restTemplate = new RestTemplate();
        boolean result = restTemplate.postForObject(uri,user,boolean.class);
        if (result) {
            message = "Exist";
            checkOrderStatus(user);
        }
        System.out.println("User "+message);
    }

    public void checkOrderStatus(User user){
        final String uri = "http://localhost:8081/getorders";
       // List<Orders>orders = new ArrayList<Orders>();
        Orders os = new Orders();
        String message="Does not exist";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper m = new ObjectMapper();
        Orders[] result = restTemplate.postForObject(uri,user,Orders[].class);
        if(result.length!=0) {
            for (Orders o : result) {
                System.out.println("Your one of the Order is " + o);
                if (o.getCode() != 203 && o.getCode()!=200) {
                    refundOrder(o);
                } else {
                    producer.produceMessage("Your Order " + o.getOrder_id() + "'s status is " + o.getStatus());
                }
            }
        }
        else
        {
            producer.produceMessage("You have not any orders pending");
        }
    }

    public void refundOrder(Orders order) {
        final String uri = "http://localhost:8083/userorder";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri,order, String.class);
    }
}
