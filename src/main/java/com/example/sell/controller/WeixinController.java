package com.example.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法...");
        log.info("code={}",code);


        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wx8c68082145a44a78" +
                "&secret=c82aa5fb61a44343d383eddabe9c99d0" +
                "&code="+code+"&grant_type=authorization_code";
        //创建一个web的请求对象(访问某个链接)
        RestTemplate restTemplate = new RestTemplate();
        //请求url返回的数据对象,并定义为String类型
        String response = restTemplate.getForObject(url,String.class);
        log.info("response={}",response);
    }

}
