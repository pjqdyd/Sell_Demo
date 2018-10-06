package com.example.sell.form;

import lombok.Data;

import java.math.BigDecimal;

//接收商品信息的类(接收表单传入的信息)
@Data
public class ProductForm {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
}
