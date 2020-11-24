package com.example.springproject.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/OOAD")
public class helloTest {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello world";
    }

    @RequestMapping("/lab")
    public String lab(){
        return "lab6";
    }
}
