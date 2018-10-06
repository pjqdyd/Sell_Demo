package com.example.sell.service;
import com.example.sell.dataobject.ProductInfo;
import com.example.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 商品业务操作接口
 */
public interface ProductService {
    ProductInfo findOne(String productId);
    ProductInfo save(ProductInfo productInfo);
    List<ProductInfo> findUpAll(); //查询在上架的商品
    Page<ProductInfo> findAll(Pageable pageable); //分页查询
    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String ProductId);
}
