package com.example.sell.handler;

import com.example.sell.exception.SellerAuthException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//捕获异常的类
@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(value = SellerAuthException.class)
    public ModelAndView handlerSellerAuthException(){
        return new ModelAndView("redirect:"+
               "http://www.baidu.com");
        //验证不通过就跳转到微信扫码登入的地址
    }



}
