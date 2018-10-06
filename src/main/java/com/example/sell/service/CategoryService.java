package com.example.sell.service;
import com.example.sell.dataobject.ProductCategory;
import java.util.List;



/**
 * 类目表相关业务接口
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
