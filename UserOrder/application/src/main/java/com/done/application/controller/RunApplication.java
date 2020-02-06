package com.done.application.controller;

import com.done.application.services.FullService;
import com.done.application.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunApplication {

    @Autowired
    private FullService fullService;

    @PostMapping("/validate")
    public void validateUser(@RequestBody User user){
        fullService.validUser(user);
    }
}
