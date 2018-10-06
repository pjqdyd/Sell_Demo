package com.example.sell.service.impl;

import com.example.sell.dataobject.SellerInfo;
import com.example.sell.repository.SellerInfoRepository;
import com.example.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//卖家相关业务实现类
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSelllerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
