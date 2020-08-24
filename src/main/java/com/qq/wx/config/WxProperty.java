package com.qq.wx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "wx")
@Component
@Data
public class WxProperty {
    private String appId;
    private String appSecret;
    private String tokenUrl;
    private String mediaUrl;
    private String createUrl;
    private String userUrl;
}
