package com.example.demo.controller;

import com.example.demo.stringRedisTemplate.service.myRedisTemplateService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Discription:
 * @User: LiChenHao
 * @Date: 2025/3/3 下午5:20
 */
@RestController
public class redisController {
    @Resource
    private myRedisTemplateService redisTemplateService;


    @RequestMapping("/redis/redisSet")
    private Map<String,Object> redisSetcontroller(HttpRequest httpRequest, HttpResponse httpResponse){
        redisTemplateService.set("redisSet", "redisSet");
        String redisSet = redisTemplateService.get("redisSet");
        redisTemplateService.set("redisSetTimeout", "redisSetTimeout", 10, TimeUnit.SECONDS);
        String redisSetTimeout = redisTemplateService.get("redisSetTimeout");
        Map<String, Object> map = new HashMap<>();
        map.put("redisSet", redisSet);
        map.put("redisSetTimeout", redisSetTimeout);


        return map;

    }
}
