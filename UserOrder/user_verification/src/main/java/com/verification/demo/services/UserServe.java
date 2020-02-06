package com.verification.demo.services;

import com.verification.demo.dao.OrderRepo;
import com.verification.demo.dao.UserRepo;
import com.verification.demo.dao.entity.OrderEntity;
import com.verification.demo.dao.entity.UserEntity;
import com.verification.demo.producer.Producer;
import com.verification.demo.vo.Orders;
import com.verification.demo.vo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServe {

    @Autowired
    private Producer producer;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;

    public void addUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        producer.produceMessage("User Created Successfully");
        userRepo.save(userEntity);
    }

    public boolean addOrder(Orders orders, Integer uid) {
        boolean status=false;
        String message ="Order not Placed";
        Optional<UserEntity> userEntity = userRepo.findById(uid);

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orders, orderEntity);

        if (userEntity.isPresent()) {
            orderEntity.setUserEntity(userEntity.get());
            orderRepo.save(orderEntity);
            status = true;
            message="Order Placed Successfully";
        }
        producer.produceMessage(message);
        return status;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Iterable<UserEntity> list = userRepo.findAll();
        for (UserEntity user : list) {
            User tempusers = new User();
            BeanUtils.copyProperties(user, tempusers);
            users.add(tempusers);
        }

        producer.produceMessage("Printing List of Users");
        return users;
    }

    public boolean validUser(User user) {
        boolean same = false;
        String message="Invalid User/ User Does not exist";
        UserEntity userEntity = new UserEntity();
        User tempUser = new User();
        BeanUtils.copyProperties(user, userEntity);
        Optional<UserEntity> userEntity1 = userRepo.findById(userEntity.getId());
        if (userEntity1.isPresent()) {
            BeanUtils.copyProperties(userEntity1.get(), tempUser);
            if (tempUser.equals(user)) {
                same = true;
                message="User Validated";
            }
        }
        producer.produceMessage(message);
        return same ? true : false;
    }

}
