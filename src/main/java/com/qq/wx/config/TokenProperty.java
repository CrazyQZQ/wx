package com.qq.wx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "token")
@Component
@Data
public class TokenProperty {
    private String appId;
    private String appSecret;
    private String tokenUrl;
}
