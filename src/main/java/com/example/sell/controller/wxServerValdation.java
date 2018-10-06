package com.example.sell.controller;
import com.example.sell.utils.WxVal_Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/wxServerValdation")
//在申请测试号时,验证signature的微信验证(填url和token用到)
public class wxServerValdation {
    @GetMapping("/token")
    public String wxServerToken(@RequestParam("signature") String signature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestParam("echostr") String echostr) {

        if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp)
                || StringUtils.isEmpty(nonce) || StringUtils.isEmpty(echostr)){
            return "";
        }

        String token = "190pjqook";
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        WxVal_Utils.sort(arr);

        //排序后拼接成一个字符串
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        String sha1Signature = null; //自己本机加密的signture
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串进行sha1加密(sha1只能加密byte类型,所以要转换)
            byte[] digestByte = md.digest(content.toString().getBytes());
            //将加密后的byte数组转换为十六进制字符串
            sha1Signature = WxVal_Utils.byteToStr(digestByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        if(sha1Signature != null && sha1Signature.equals(signature.toUpperCase())){
            return echostr;
        }
        return "";
    }
}


