package com.example.sell.utils;

import com.example.sell.enums.CodeEnum;

//通过code获取枚举类对应的枚举
public class EnumUtil {

    public static <T extends CodeEnum>T getByCode(Integer code, Class<T> enumClass){
        for (T each: enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
