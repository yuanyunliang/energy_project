package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.ElectricityJson;
import com.baomidou.springwind.entity.RecordAlternatorProducer;
import com.baomidou.springwind.entity.Result;
import com.baomidou.springwind.mapper.RecordAlternatorProducerMapper;
import com.baomidou.springwind.service.IRecordAlternatorProducerService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import com.baomidou.springwind.utils.EnergyChainUtil;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.util.List;

/**
 * <p>
 * 发电机发电信息记录表 服务实现类
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-15
 */
@Service
public class RecordAlternatorProducerServiceImpl extends BaseServiceImpl<RecordAlternatorProducerMapper, RecordAlternatorProducer> implements IRecordAlternatorProducerService {

    @Autowired
    private RecordAlternatorProducerMapper producerMapper;

    /**
     * 发电信息上链
     * @param recordAlternatorProducer 发电信息
     * @return 返回状态对象
     */
    @Override
    @Log("发电信息上链")
    public Result generateElectricUpper(RecordAlternatorProducer recordAlternatorProducer) {

        // 插入数据库
        Integer insert = producerMapper.insert(recordAlternatorProducer);
        String encdeStr;

        // 是否插入成功
        if (!(insert > 0)) {
            Result failResult = new Result();
            failResult.setCode("500");
            failResult.setMessage("发电信息上链时，插入数据库失败");
            return failResult;
        } else {
            // SHA-256加密过程
            MessageDigest messageDigest;
            encdeStr = recordAlternatorProducer.getAlternatorId() + "!@#$" +
                       recordAlternatorProducer.getElectricity() +
                       recordAlternatorProducer.getStartTime() +
                       recordAlternatorProducer.getEndTime();
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] hash = messageDigest.digest(encdeStr.getBytes("UTF-8"));
                encdeStr = Hex.encodeHexString(hash);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 调用发电信息上链接口
            String url = "producer.upper";
            EnergyChainUtil chainUtil = new EnergyChainUtil();
            ElectricityJson electricityJson = new ElectricityJson();

            electricityJson.setCheckSum(encdeStr);
            electricityJson.setElectricity(String.valueOf(recordAlternatorProducer.getElectricity()));
            electricityJson.setEndTime(String.valueOf(recordAlternatorProducer.getEndTime().getTime()));
            electricityJson.setStartTime(String.valueOf(recordAlternatorProducer.getStartTime().getTime()));
            electricityJson.setMeterId(String.valueOf(recordAlternatorProducer.getAlternatorId()));

            return chainUtil.post(url, electricityJson);
        }
    }

    /**
     * 查询公司所有上链信息
     * @param cid 公司id
     * @return 返回上链信息集合
     */
    @Override
    @Log("查询公司所有上链信息")
    public Page<RecordAlternatorProducer> selectAlternatorsProducers(String cid) {
        List<RecordAlternatorProducer> list = producerMapper.selectAlternatorsProducers(cid);
        Page<RecordAlternatorProducer> page = new Page<RecordAlternatorProducer>();
        page.setRecords(list);
        page.setSize(10);
        page.setTotal(list.size());
        page.setCurrent(0);
        return page;
    }
}
