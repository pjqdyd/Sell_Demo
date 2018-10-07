package com.example.sell.service.impl;

import com.example.sell.dto.OrderDTO;
import com.example.sell.service.OrderService;
import com.example.sell.service.PushMessageSeivice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageSerivaceImplTest {

    @Autowired
    private PushMessageSeivice pushMessageSeivice;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() throws Exception{
       OrderDTO orderDTO = orderService.findOne("1538550367662508889");
       pushMessageSeivice.orderStatus(orderDTO);
    }
}