package com.qq.wx.dispatcher;

import com.qq.wx.entity.message.response.Article;
import com.qq.wx.entity.message.response.NewsResponseMessage;
import com.qq.wx.entity.message.response.TextResponseMessage;
import com.qq.wx.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息分发
 */
@Slf4j
public class MsgDispatcher {
    public static String processMessage(Map<String, String> map) {
        String openid=map.get("FromUserName"); //用户openid
        String mpid=map.get("ToUserName");   //公众号原始ID
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            //普通文本消息
            TextResponseMessage txtmsg=new TextResponseMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            txtmsg.setContent("你好，欢迎您的关注！");
            return MessageUtil.textMessageToXml(txtmsg);
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            log.info("==============这是图片消息！");
            NewsResponseMessage newsMsg=new NewsResponseMessage();
            newsMsg.setToUserName(openid);
            newsMsg.setFromUserName(mpid);
            newsMsg.setCreateTime(new Date().getTime());
            newsMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            Article article=new Article();
            article.setDescription("图文消息测试"); //图文消息的描述
            article.setPicUrl("https://i.loli.net/2020/08/21/MqolfKyIj7pzibX.jpg"); //图文消息图片地址
            article.setTitle("图文消息测试");  //图文消息标题
            article.setUrl("http://47.99.165.120");  //图文url链接
            List<Article> list=new ArrayList<>();
            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
            newsMsg.setArticleCount(list.size());
            newsMsg.setArticles(list);
            return MessageUtil.newsMessageToXml(newsMsg);
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            log.info("==============这是链接消息！");
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            log.info("==============这是位置消息！");
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            log.info("==============这是视频消息！");
        }
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            log.info("==============这是语音消息！");
        }

        return null;
    }
}
