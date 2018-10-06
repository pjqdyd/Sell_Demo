package com.example.sell.service.impl;

import com.example.sell.dto.OrderDTO;
import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.service.OrderService;
import com.example.sell.service.PayService;
import com.example.sell.utils.MathUitl;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

       PayResponse payResponse = bestPayService.pay(payRequest);
       log.info("[微信支付结果] response={}",payResponse);
       return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名 (sdk已经完成了)
        //2.判断支付状态 (sdk已经完成了)

       PayResponse payResponse = bestPayService.asyncNotify(notifyData);
       log.info("[微信支付]  异步通知, payResponse={}",payResponse);

       //验证订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if(orderDTO == null) {
            log.error("[微信支付]  异步通知,订单不存在,orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //3.判断支付金额 (这里由于数据类型不一致不能直接.equirs)
        if(MathUitl.equals(payResponse.getOrderAmount(),
                orderDTO.getOrderAmount().doubleValue())){
            log.error("[微信支付]  异步通知,订单金额不一致");
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //4.支付人(下单人 == 支付人)

        //修改订单支付状态
        orderService.paid(orderDTO);
       return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款请求] result={}",refundRequest);

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] response={}",refundResponse);

        return refundResponse;
    }
}
