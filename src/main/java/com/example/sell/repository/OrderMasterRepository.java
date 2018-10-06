package com.example.sell.repository;
import com.example.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 订单主表的资源仓库接口/DAO(数据库持久访问对象)
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /**按买家的微信id分页查找的方法*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
