package com.example.sell.service;

import com.example.sell.dataobject.SellerInfo;
/**
 * 卖家业务操作接口
 */
public interface SellerService {
    SellerInfo findSelllerInfoByOpenid(String openid);
}
