package com.example.sell.VO;

import lombok.Data;

/**
 * HTTP请求返回的最外层对象
 * @param <T>
 */
@Data
public class ResultVO<T> {
    private Integer code; //错误码
    private String msg; //提示信息
    private T data; //返回的具体的内容
}
