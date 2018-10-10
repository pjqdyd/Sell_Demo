package com.example.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//websocket配置类
@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();
    }
    //这个bean在运行测试时,会创建失败
}
