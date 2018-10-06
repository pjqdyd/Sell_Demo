package com.example.sell.form;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    //这里的购物车前端传过来的是json格式的字符串
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
