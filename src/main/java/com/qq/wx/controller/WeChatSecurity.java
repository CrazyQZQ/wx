package com.qq.wx.controller;

import com.qq.wx.dispatcher.EventDispatcher;
import com.qq.wx.dispatcher.MsgDispatcher;
import com.qq.wx.service.TokenService;
import com.qq.wx.utils.MessageUtil;
import com.qq.wx.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping("/weChat")
@Slf4j
public class WeChatSecurity {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping("/security")
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                log.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    @PostMapping("security")
    // post方法用于接收微信服务端消息
    public void DoPost(HttpServletRequest request, HttpServletResponse response) {
        log.info("接收消息》》》》》》》》》》》》》》");
        try {
            Map<String, String> map = MessageUtil.parseXml(request);
            String msgType = map.get("MsgType");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String msgRsp = null;
            if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType))
                //进入事件处理
                msgRsp = EventDispatcher.processEvent(map);
            else
                //进入消息处理
                msgRsp = MsgDispatcher.processMessage(map);
            PrintWriter out = response.getWriter();
            out.print(msgRsp);
            out.close();
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

//    @GetMapping("/getToken")
//    public void getToken() {
//        tokenService.getToken();
//    }

    @GetMapping("/getTokenInRedis")
    public String getTokenInRedis() {
        return stringRedisTemplate.opsForValue().get("access_token");
    }


}
