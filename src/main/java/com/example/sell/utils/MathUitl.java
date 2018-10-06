package com.example.sell.utils;

public class MathUitl {
    private static final Double MONEY_RANGE = 0.01;

    //比较金额是否相等
    public static Boolean equals(Double d1,Double d2){
        Double result = Math.abs(d1-d2);
        if(result < MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }
}
