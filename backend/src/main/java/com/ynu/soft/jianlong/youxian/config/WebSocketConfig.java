package com.ynu.soft.jianlong.youxian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/** websocket配置，开启支持
 * @Description
 * @Author Jianlong
 * @Date 2020-06-02 上午 11:37
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
