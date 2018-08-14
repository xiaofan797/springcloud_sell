package com.xiaofan.sell.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ServerController {

    @GetMapping("/msg")
    public String getMsg(){
        return "get product msg 2";
    }
}
