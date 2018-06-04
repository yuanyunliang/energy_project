package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.RecordAlternatorProducer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
  * 发电机发电信息记录表 Mapper接口
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-15
 */
@Component
public interface RecordAlternatorProducerMapper extends BaseMapper<RecordAlternatorProducer> {

    List<RecordAlternatorProducer> selectAlternatorsProducers(@Param("cid")String cid);

    void insertOne(RecordAlternatorProducer recordAlternatorProducer);
}