package com.example.sell.repository;

import com.example.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 卖家信息资源仓库接口/DAO(数据库持久访问对象)
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
