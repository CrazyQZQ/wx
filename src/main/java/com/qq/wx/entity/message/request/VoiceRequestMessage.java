package com.qq.wx.entity.message.request;

import lombok.Data;

@Data
public class VoiceRequestMessage extends BaseRequestMessage {
    // 媒体 ID
    private String mediaId;
    // 语音格式
    private String format;
}
