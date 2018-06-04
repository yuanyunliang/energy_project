package com.baomidou.springwind.test;

import com.baomidou.springwind.entity.RecordMeterConsume;
import com.baomidou.springwind.entity.Result;
import com.baomidou.springwind.utils.PostUtil;

import java.util.Date;

/**
 * 描述：
 *
 * @author XieYongJie
 * @since 2018/6/2
 */
public class autoBillTest {
    public static void main(String[] args) {
        String url = "http://127.0.0.1:8080/engery-project/dataQuery/AutomaticClearing";

        RecordMeterConsume recordMeterConsume = new RecordMeterConsume();
        recordMeterConsume.setMeterId("083cadf2bac8581e9da584c52c980ea5");
        recordMeterConsume.setCompanyId("08ad206c732f5b7e859c915ffa202345");
        recordMeterConsume.setElectricity(2.44);
        recordMeterConsume.setStartTime(new Date(System.currentTimeMillis() - 10000));
        recordMeterConsume.setEndTime(new Date(System.currentTimeMillis()));

        Result result = new PostUtil().autoBill(url, recordMeterConsume);

        System.out.println(result.getCode());
        System.out.println(result.getData());
        System.out.println(result.getMessage());
    }
}
