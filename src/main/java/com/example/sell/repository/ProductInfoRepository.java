package com.example.sell.repository;
import com.example.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 商品的资源仓库接口/DAO(数据库持久访问对象)
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    //通过商品的状态来查询已上架的商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
