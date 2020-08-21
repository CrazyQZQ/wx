package com.qq.wx.entity.message.response;

import lombok.Data;

import java.util.List;

/**
 * 多条图文
 */
@Data
public class NewsResponseMessage extends BaseResponseMessage{
    // 图文消息个数，限制为10条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;
}
