package com.baomidou.springwind.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.springwind.entity.AccountJson;
import com.baomidou.springwind.entity.ElectricityJson;
import com.baomidou.springwind.entity.QueryJson;
import com.baomidou.springwind.entity.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author XieYongJie
 * @Date 2018/5/31 16:34
 */
public class EnergyChainUtil {
    private String url = "http://172.13.5.56:80/energyChain/";
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public EnergyChainUtil() {
        // 实例化接口调用工具
        restTemplate = new RestTemplate();
        // 实例化Http头
        headers = new HttpHeaders();
        // 配置Content-Type = application/json
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public Result post(String uri, QueryJson queryJson) {
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(queryJson), headers);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url + uri, entity, JSONObject.class);
        return response.getBody().toJavaObject(Result.class);
    }

    public Result post(String uri, ElectricityJson electricityJson) {
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(electricityJson), headers);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url + uri, entity, JSONObject.class);
        return response.getBody().toJavaObject(Result.class);
    }

    public Result post(String uri, AccountJson accountJson) {
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(accountJson), headers);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url + uri, entity, JSONObject.class);
        return response.getBody().toJavaObject(Result.class);
    }
}
