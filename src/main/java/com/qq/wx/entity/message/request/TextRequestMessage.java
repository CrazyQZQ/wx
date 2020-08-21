package com.qq.wx.entity.message.request;

import lombok.Data;

@Data
public class TextRequestMessage extends BaseRequestMessage {
    // 消息内容
    private String content;
}
