package com.example.sell.exception;
import com.example.sell.enums.ResultEnum;

//卖家验证异常
public class SellerAuthException extends RuntimeException{
    private Integer code;
    private String message;

    //异常类的构造器
    public SellerAuthException(ResultEnum resultEnum) {
       this.message = resultEnum.getMessage();
        this.code = resultEnum.getCode();
    }
}
