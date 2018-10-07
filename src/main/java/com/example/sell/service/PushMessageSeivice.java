package com.example.sell.service;

import com.example.sell.dto.OrderDTO;

//推送微信消息的业务操作接口
public interface PushMessageSeivice {

    //订单状态
    void orderStatus(OrderDTO orderDTO);
}
