package com.code.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author： LiChenHao
 * @date： 2023/6/13 23:25
 * @description：
 * @modifiedBy：
 * @version: 1.0.1
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        System.out.println("te");
        return "TEST";
    }

}
