package com.qq.wx.service;

import com.alibaba.fastjson.JSON;
import com.qq.wx.config.TokenProperty;
import com.qq.wx.entity.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenProperty tokenProperty;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Scheduled(fixedRate = 1000*10)
    @Async
    public void getToken() {
        StringBuffer url = new StringBuffer(tokenProperty.getTokenUrl());
        url.append("?grant_type=client_credential&appid=")
                .append(tokenProperty.getAppId())
                .append("&secret=")
                .append(tokenProperty.getAppSecret());
        String tokenStr = restTemplate.getForObject(url.toString(), String.class);
        Token token = JSON.parseObject(tokenStr, Token.class);
        log.info("获取access_token>>>>>>>>>>>>>" + token);
        ValueOperations<String, String> opration = stringRedisTemplate.opsForValue();
        opration.set("access_token",token.getAccessToken());
    }
}
