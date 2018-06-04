package com.baomidou.springwind.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.springwind.entity.RecordMeterConsume;
import com.baomidou.springwind.entity.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：
 *
 * @author XieYongJie
 * @since 2018/6/2
 */
public class PostUtil {
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public PostUtil() {
        // 实例化接口调用工具
        restTemplate = new RestTemplate();
        // 实例化Http头
        headers = new HttpHeaders();
        // 配置Content-Type = application/json
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public Result autoBill(String url, RecordMeterConsume recordMeterConsume) {
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(recordMeterConsume), headers);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, entity, JSONObject.class);
        return response.getBody().toJavaObject(Result.class);
    }
}
