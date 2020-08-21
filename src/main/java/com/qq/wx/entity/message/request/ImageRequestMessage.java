package com.qq.wx.entity.message.request;

import lombok.Data;

/**
 * 图片消息
 */
@Data
public class ImageRequestMessage extends BaseRequestMessage {
    private String picUrl;
}
