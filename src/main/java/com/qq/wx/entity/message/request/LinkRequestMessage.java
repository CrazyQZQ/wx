package com.qq.wx.entity.message.request;

import lombok.Data;

@Data
public class LinkRequestMessage extends BaseRequestMessage {
    // 消息标题
    private String title;
    // 消息描述
    private String description;
    // 消息链接
    private String url;
}
