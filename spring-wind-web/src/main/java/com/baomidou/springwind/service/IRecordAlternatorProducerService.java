package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.RecordAlternatorProducer;
import com.baomidou.springwind.entity.Result;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 发电机发电信息记录表 服务类
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-15
 */
public interface IRecordAlternatorProducerService extends IService<RecordAlternatorProducer> {

    /**
     * 发电信息上链
     * @param recordAlternatorProducer :发电信息
     * @return String:返回状态信息
     */
    Result generateElectricUpper(RecordAlternatorProducer recordAlternatorProducer) throws NoSuchAlgorithmException;

    /**
     * 查询公司所有上链信息
     * @param cid:公司id
     * @return 返回上链信息集合
     */
    Page<RecordAlternatorProducer> selectAlternatorsProducers(String cid);
}
