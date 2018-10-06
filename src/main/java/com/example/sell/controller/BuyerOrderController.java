package com.example.sell.controller;

import com.example.sell.VO.ResultVO;
import com.example.sell.dto.OrderDTO;
import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.form.OrderForm;
import com.example.sell.service.OrderService;
import com.example.sell.utils.OF_2_ODTO;
import com.example.sell.utils.ResultVOUtil;
import com.example.sell.utils.Verify_OpenId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return 最外层的返回对象(设置了data为一个map(orderid id))
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单] 参数不正确, orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OF_2_ODTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
       OrderDTO createResult = orderService.create(orderDTO);
      Map<String,String> map = new HashMap<>();
      map.put("orderId",createResult.getOrderId());

      return ResultVOUtil.success(map);
    }

    /**
     * 查询订单列表
     * @param openid
     * @param page 默认是0
     * @param size 默认是10
     * @return 最外层的返回对象(data设置为了Page<OrderDTO>类型)
     */
    @GetMapping("/list")
    public ResultVO<Page<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest requestPage = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,requestPage);
        return ResultVOUtil.success(orderDTOPage);
    }

    /**
     * 查询某个订单的订单详情列表
     * @param openid
     * @param orderId
     * @return 最外层的返回对象(data设置了orderDTO(包含了detail_list))
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId" ) String orderId){
        //要验证openid才能查询(直接查询不安全)
       OrderDTO orderDTO = Verify_OpenId.verify(orderService,openid,orderId);
       return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return 最外层返回对象(data为null)
     */
     @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        //TODO 同样需要检验openid
         OrderDTO orderDTO = Verify_OpenId.verify(orderService,openid,orderId);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }
}
