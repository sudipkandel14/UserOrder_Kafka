package com.producer.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "test",groupId = "abc")
    public void consumeMessage(String msg){
        System.out.println("INFO-1------ "+ msg);

    }
    @KafkaListener(topics = "test1",groupId = "abc")
    public void consumeMessage2(String msg){
        System.out.println("INFO-2------ "+ msg);
    }

    @KafkaListener(topics = "test2",groupId = "abc")
    public void consumeMessage3(String msg){
        System.out.println("INFO-3------ "+ msg);
    }
}
