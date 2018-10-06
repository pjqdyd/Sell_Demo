package com.example.sell.repository;

import com.example.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("000002");
        orderDetail.setOrderId("0000");
        orderDetail.setProductIcon("http://xxxxxx.jpg");
        orderDetail.setProductId("0122");
        orderDetail.setProductName("西红柿面");
        orderDetail.setProductPrice(new BigDecimal(3.3));
        orderDetail.setProductQuantity(2);

       OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception{

        List<OrderDetail> orderDetailList = repository.findByOrderId("000002");
        Assert.assertNotEquals(0,orderDetailList.size());

    }
}