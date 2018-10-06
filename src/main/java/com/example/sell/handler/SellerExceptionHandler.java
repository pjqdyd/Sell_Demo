package com.example.sell.handler;

import com.example.sell.exception.SellerAuthException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//捕获卖家验证异常的类
@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(value = SellerAuthException.class)
    public ModelAndView handlerSellerAuthException(){
        return new ModelAndView("redirect:"+
               "http://www.baidu.com");
    }
}
