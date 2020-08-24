package com.qq.wx.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qq.wx.config.WxProperty;
import com.qq.wx.entity.menu.ClickButton;
import com.qq.wx.entity.menu.ViewButton;
import jdk.nashorn.internal.runtime.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WxProperty wxProperty;
    @Override
    public String createMenu() {
        String rs = null;
        ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");


        ViewButton vbt=new ViewButton();
        vbt.setUrl("https://www.cnblogs.com/gede");
        vbt.setName("博客");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);

        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
        button.add(cbt);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);

        String access_token = stringRedisTemplate.opsForValue().get("access_token");
        String url= new StringBuffer(wxProperty.getCreateUrl()).append(access_token).toString();
        log.info("请求生成菜单url》》》》》"+url);
        try{
            rs = restTemplate.postForObject(url,menujson,String.class);
            log.info(rs);
        }catch(Exception e){
            log.error("请求错误！");
        }
        return rs;
    }
}
