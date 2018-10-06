package com.example.sell.aspect;

import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellerAuthException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Pointcut("execution(public * com.example.sell.controller.SellerOrderController.list(..))" +
    "&& !execution(public * com.example.sell.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){

       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取cookie,并检验
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        if(cookies == null){
            throw new SellerAuthException(ResultEnum.VERIFY_FAIL);
        }
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("token")){
               tokenCookie = cookie; //记录名为token的cookie
            }
        }
        System.out.println("------------------3");
        if(tokenCookie == null){
            log.warn("[登入验证] 未找到token的cookie");
            throw new SellerAuthException(ResultEnum.VERIFY_FAIL);
        }else {
            //验证redis中的数据
            String redisTokenValue = redisTemplate
                    .opsForValue().get(String.format("token_"+tokenCookie.getValue()));

            if(StringUtils.isEmpty(redisTokenValue)){
                log.warn("[登入验证] 未找到redis中的token值");
                throw new SellerAuthException(ResultEnum.VERIFY_FAIL);
            }
        }
    }
}
