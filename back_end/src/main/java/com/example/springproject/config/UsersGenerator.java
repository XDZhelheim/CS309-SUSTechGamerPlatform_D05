package com.example.springproject.config;

import com.example.springproject.service.GameService;
import com.example.springproject.service.GameUserService;
import com.example.springproject.service.SDKLogService;
import com.example.springproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/OOAD")
public class UsersGenerator {
    public static UsersGenerator Services;

    public UsersGenerator() {
        Services = this;
    }


    @Autowired
    public UsersService usersService;
    @Autowired
    public GameService gamesService;
    @Autowired
    public GameUserService gameUserService;
    @Autowired
    public SDKLogService sdkLogService;

    @RequestMapping("/users")
    public void hello(){

    }
}
