package com.example.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 类目在前端的视图对象(包含商品)
 *
 */
@Data
public class CategoryVO {

    @JsonProperty("name") //这样在返回给前端的字段名就是name
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
