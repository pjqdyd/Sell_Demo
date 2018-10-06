package com.example.sell.dto;
import lombok.Data;
/**
 * 购物车中的单样商品,根据前端文档定义,存放单样商品
 */
@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
