package com.example.sell.exception;
import com.example.sell.enums.ResultEnum;

//自定义异常类
public class SellException extends RuntimeException {
    private Integer code;

    //异常类的构造器
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    //异常类的构造器
    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
