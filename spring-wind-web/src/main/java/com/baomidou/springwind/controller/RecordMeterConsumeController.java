package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.common.RequestResult;
import com.baomidou.springwind.entity.ElectricityJson;
import com.baomidou.springwind.entity.RecordMeterConsume;
import com.baomidou.springwind.entity.Result;
import com.baomidou.springwind.service.RecordMeterConsumeService;
import com.baomidou.springwind.utils.EnergyChainUtil;
import com.baomidou.springwind.utils.PostUtil;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
@RequestMapping(value = "userConsume")
@Controller
public class RecordMeterConsumeController {

    @Autowired
    private RecordMeterConsumeService recordMeterConsumeService;

    /**
     * 前往用户用电信息模拟上链页面
     * @return
     */
    @RequestMapping(value = "recordMeterConsumeUI")
    public String recordMeterConsumeUI() {
        return "consume/recordMeterConsume";
    }

    /**
     * 保存用户用电信息并结算上链
     * @param recordMeterConsume:前台需要传入用户id,用户电表id,电表度数，开始和结束时间
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public RequestResult saveRecordMeterConsume(RecordMeterConsume recordMeterConsume, String startTimed, String endTimed) {
        System.out.println(startTimed);
        System.out.println(endTimed);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime = simpleDateFormat.parse(startTimed);
            recordMeterConsume.setStartTime(startTime);
            Date endTime = simpleDateFormat.parse(endTimed);
            recordMeterConsume.setEndTime(endTime);
            // 保存用户用电信息到数据库
            recordMeterConsumeService.saveRecordMeterConsume(recordMeterConsume);

            // 调用结算接口
            String url = "http://127.0.0.1:8080/engery-project/dataQuery/AutomaticClearing";
            Result result = new PostUtil().autoBill(url, recordMeterConsume);
            System.out.println(result.getCode());
            System.out.println(result.getData());
            System.out.println(result.getMessage());

            // SHA-256加密过程
            MessageDigest messageDigest;
            String encdeStr = recordMeterConsume.getMeterId() + "!@#$" +
                    recordMeterConsume.getElectricity() +
                    recordMeterConsume.getStartTime() +
                    recordMeterConsume.getEndTime();
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] hash = messageDigest.digest(encdeStr.getBytes("UTF-8"));
                encdeStr = Hex.encodeHexString(hash);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 用电信息上链
            url = "electricityConsumption.upper";
            EnergyChainUtil chainUtil = new EnergyChainUtil();
            ElectricityJson electricityJson = new ElectricityJson();
            electricityJson.setCheckSum(encdeStr);
            electricityJson.setElectricity(String.valueOf(recordMeterConsume.getElectricity()));
            electricityJson.setEndTime(String.valueOf(recordMeterConsume.getEndTime().getTime()));
            electricityJson.setStartTime(String.valueOf(recordMeterConsume.getStartTime().getTime()));
            electricityJson.setMeterId(String.valueOf(recordMeterConsume.getMeterId()));
            chainUtil.post(url, electricityJson);
            return new RequestResult(1001,"上链成功");
        } catch (Exception e) {
            return new RequestResult(1002,"未知错误,上链失败");
        }
    }

}
