package com.example.sell.dto;
import com.example.sell.dataobject.OrderDetail;
import com.example.sell.enums.OrderStatusEnum;
import com.example.sell.enums.PayStatusEnum;
import com.example.sell.utils.Date_2_Long;
import com.example.sell.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 订单数据传输对象
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;   //买家微信
    private BigDecimal orderAmount; //订单总价
    /**订单状态,默认0新订单*/
    private Integer orderStatus;
    /**支付状态,默认0未支付*/
    private Integer payStatus;

    @JsonSerialize(using = Date_2_Long.class)
    private Date createTime;
    @JsonSerialize(using = Date_2_Long.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }


}
