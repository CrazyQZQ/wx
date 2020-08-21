package com.qq.wx.entity.message.request;

import lombok.Data;

@Data
public class LocationRequestMessage extends BaseRequestMessage {
    // 地理位置维度
    private String location_X;
    // 地理位置经度
    private String location_Y;
    // 地图缩放大小
    private String scale;
    // 地理位置信息
    private String label;
}
