package com.qq.wx.service;

import com.qq.wx.config.WxProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WxProperty wxProperty;
    @Override
    public void getUsers() {
        String access_token = stringRedisTemplate.opsForValue().get("access_token");
        StringBuffer url = new StringBuffer(wxProperty.getUserUrl()).append("/get?access_token=").append(access_token);
        String users = restTemplate.getForObject(url.toString(), String.class);
        log.info("users >>>>> "+users);
    }

    @Override
    public void getUserInfo(String openId) {
        String access_token = stringRedisTemplate.opsForValue().get("access_token");
//        info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        StringBuffer url = new StringBuffer(wxProperty.getUserUrl())
                .append("/info?access_token=")
                .append(access_token)
                .append("&openid=")
                .append(openId)
                .append("&lang=zh_CN");
        String userInfo = restTemplate.getForObject(url.toString(), String.class);
        log.info("users >>>>> "+userInfo);
    }
}

