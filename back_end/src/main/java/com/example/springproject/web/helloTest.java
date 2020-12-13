package com.example.springproject.web;

import com.example.springproject.domain.Users;
import com.example.springproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController("/OOAD")
public class helloTest {
    @Autowired
    private UsersService usersService;
    @RequestMapping("/hello")
    public String hello(){

        String name = "aaa";
        String pwd = "aaa";
        Users users = new Users();
        users.setName(name);
        users.setPassword(pwd);
        if (usersService.checkLogin(users)) {
            System.out.println("login");
        } else {
            System.out.println("name pwd miss match");
        }
        return "Hello world";
    }

    @RequestMapping("/lab")
    public String lab(){
        return "lab6";
    }
}
