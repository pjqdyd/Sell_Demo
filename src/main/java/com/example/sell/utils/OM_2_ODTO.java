package com.example.sell.utils;
import com.example.sell.dataobject.OrderMaster;
import com.example.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 订单主表对象转订单DTO对象
 */
public class OM_2_ODTO {
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
