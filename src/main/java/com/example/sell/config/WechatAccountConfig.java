package com.example.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//微信公众号开发账号配置类
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String mpAppid;
    private String mpAppSecret;
    private String mchId;
    private String mchKey;
    private String keyPath;
    private String notiflyUrl;
}
