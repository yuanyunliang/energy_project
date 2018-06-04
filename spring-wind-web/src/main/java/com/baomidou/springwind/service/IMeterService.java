package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.MeterInfo;
import com.baomidou.springwind.entity.UserMeter;

/**
 * <p>
 * 电表归属表 服务类
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-16
 */
public interface IMeterService {

    /**
     * 查询用户所属电表
     * @param uid :用户id
     * @return: 返回用户所属电表集合
     */
    Page<MeterInfo> selectMeterListByUserId(String uid);
}
