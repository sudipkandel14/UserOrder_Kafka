package com.order.status.producer;

import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String , String> kafkaTemplate ;

    public void produceMessage(String msg){
        LOGGER.info("sending payload='{}'", msg);
        kafkaTemplate.send("test1",msg);
    }

}
