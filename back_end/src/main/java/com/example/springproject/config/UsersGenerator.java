package com.example.springproject.config;

import com.example.springproject.domain.Users;
import com.example.springproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/OOAD")
public class UsersGenerator {
    public static UsersGenerator userService;

    public UsersGenerator() {
        userService = this;
    }

    @Autowired
    UsersService usersService;
    @RequestMapping("/users")
//    public boolean hello(){
//        Users users = new Users();
//        users.setName("1");
//        users.setPassword("1");
////        System.out.println(usersService.checkLogin(users);
//        return usersService.checkLogin(users);
//    }
    public boolean checkLogin(Users users) {
        return usersService.checkLogin(users);
    }

    public void save(Users users){
        usersService.save(users);
    }


}
