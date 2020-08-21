package com.qq.wx.entity.message.request;

import lombok.Data;

@Data
public class VideoRequestMessage extends BaseRequestMessage {
    // 视频消息媒体 id，可以调用多媒体文件下载接口拉取数据
    private String mediaId;
    // 视频消息缩略图的媒体 id，可以调用多媒体文件下载接口拉取数据
    private String thumbMediaId;
}
