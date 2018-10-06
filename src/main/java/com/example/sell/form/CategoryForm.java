package com.example.sell.form;

import lombok.Data;

//接收类目表单传入的数据(表单验证)
@Data
public class CategoryForm {

    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
}
