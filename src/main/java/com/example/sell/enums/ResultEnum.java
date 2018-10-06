package com.example.sell.enums;

import lombok.Data;
import lombok.Getter;

/**
 * 给前端提示消息的枚举类
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存错误"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态错误"),
    CART_EMPTY(18,"购物车为空"),
    OPENID_ERROR(19,"错误的openId"),
    PRODUCT_STATUS_ERROR(20,"商品状态不正确"),
    LOGIN_FAIL(21,"卖家登入失败"),
    LOGOUT_SUCCESS(22,"登出成功"),
    VERIFY_FAIL(23,"卖家验证失败");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
