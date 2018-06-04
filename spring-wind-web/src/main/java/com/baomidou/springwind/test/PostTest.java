package com.baomidou.springwind.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.springwind.entity.QueryJson;
import com.baomidou.springwind.entity.Result;
import com.baomidou.springwind.utils.EnergyChainUtil;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author XieYongJie
 * @date 2018/5/31 11:10
 */
public class PostTest {
    public static void main(String[] args) {

        QueryJson queryJson = new QueryJson();
        queryJson.setMeterId("a09233f5-961e-4ad7-9d63-19d58349bda0");
        queryJson.setStartTime("1527000000");
        queryJson.setEndTime("1527999999");

        Result result = new EnergyChainUtil().post("consumer.query", queryJson);
        System.out.println(result.getCode());
        System.out.println(result.getMessage());
        System.out.println(result.getData());
    }

}
