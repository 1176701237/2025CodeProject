package com.example.demo;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Discription: \
 * @User: LiChenHao
 * @Date: 2025/3/3 16:03
 */
@RestController
public class tes {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping("/test")
    public String test(){
        System.out.println("te");
        stringRedisTemplate.opsForValue().set("hello", "world");
        String hello = stringRedisTemplate.opsForValue().get("hello");
        System.out.println(hello);
        return "TEST";
    }

}
