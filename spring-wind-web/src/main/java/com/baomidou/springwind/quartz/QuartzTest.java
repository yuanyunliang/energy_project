package com.baomidou.springwind.quartz;

import com.baomidou.framework.annotations.Log;
import com.baomidou.springwind.entity.Alternator;
import com.baomidou.springwind.entity.RecordAlternatorProducer;
import com.baomidou.springwind.entity.Result;
import com.baomidou.springwind.mapper.AlternatorMapper;
import com.baomidou.springwind.mapper.RecordAlternatorProducerMapper;
import com.baomidou.springwind.service.IRecordAlternatorProducerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 曹金洲.
 * 创建的时间：2017/8/9.
 * 作用：定时器
 */
public class QuartzTest {

    @Autowired
    private RecordAlternatorProducerMapper producerMapper;

    @Autowired
    private IRecordAlternatorProducerService producerService;

    @Autowired
    private AlternatorMapper alternatorMapper;

    public void quartz(){
        RecordAlternatorProducer producer = new RecordAlternatorProducer();
        Alternator alternator = randomAlternator();

        producer.setStartTime(new Date());
        producer.setEndTime(endTime());
        producer.setElectricity(randomNumber());
        producer.setCompanyId(alternator.getCompanyId());
        producer.setAlternatorId(alternator.getAlternatorId());

        Result result = new Result();

        // 检查是否插入数据库成功
        try {
            producerMapper.insertOne(producer);
            dataBaseSuccess();
        } catch (Exception e) {
            dataBaseFail();
        }

        // 检查是否上链成功
        try {
            result = producerService.generateElectricUpper(producer);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 加入日志
        if ("0".equals(result.getCode())) {
            success();
        } else {
            fail();
        }
    }

    public void test(){
        System.out.println("yes");
    }

    /**
     * 随机生成发电数量
     * @return 发电量
     */
    private double randomNumber() {
        DecimalFormat df = new DecimalFormat("#.0");
        return Double.parseDouble(df.format(Math.random() * 100 + 1));
    }

    /**
     * 产生当前时间 + 1个小时
     * @return 1小时后时间
     */
    private Date endTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        return calendar.getTime();
    }

    /**
     * 从数据库中随机产生一个Alternator
     * @return
     */
    private Alternator randomAlternator(){
        List<Alternator> alternators = alternatorMapper.selectAllAlternators();
        int i = (int) (Math.random() * alternators.size());
        return alternators.get(i);
    }

    @Log("发电信息上链成功")
    private void success() {
        System.out.println("发电信息上链成功");
    }

    @Log("发电信息上链失败")
    private void fail() {
        System.out.println("发电信息上链失败");
    }

    @Log("发电信息插入数据库成功")
    private void dataBaseSuccess() {
        System.out.println("发电信息插入数据库成功");
    }

    @Log("发电信息插入数据库失败")
    private void dataBaseFail() {
        System.out.println("发电信息插入数据库失败");
    }
}
