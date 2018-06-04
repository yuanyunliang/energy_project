package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.Meter;
import com.baomidou.springwind.entity.UserMeter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 电表归属表 Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-16
 */
@Repository
public interface MeterMapper extends BaseMapper<Meter> {

    UserMeter selectMetersByUserId(@Param("uid") String uid);

    String getUserIdByMeterId(@Param("id") String id);
}