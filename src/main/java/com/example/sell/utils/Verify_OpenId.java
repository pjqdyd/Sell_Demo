package com.example.sell.utils;
import com.example.sell.dto.OrderDTO;
import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
//检验是否是本人的openid(买家操作订单时)
@Slf4j
public class Verify_OpenId {
    public static OrderDTO verify(OrderService orderService, String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[订单操作] 订单的openid不一致, openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.OPENID_ERROR);
        }
        return orderDTO;
    }
}
