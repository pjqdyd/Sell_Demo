package com.example.sell.dataobject.mapper;
import org.apache.ibatis.annotations.Insert;
import java.util.Map;

//产品类目的映射操作(mybatis)
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type)" +
            " value(#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

}









