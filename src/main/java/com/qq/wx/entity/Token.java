package com.qq.wx.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Token {
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;
}
