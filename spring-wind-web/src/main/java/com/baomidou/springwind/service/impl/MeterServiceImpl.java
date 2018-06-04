package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.MeterInfo;
import com.baomidou.springwind.mapper.MeterMapper;
import com.baomidou.springwind.service.IMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 电表归属表 服务实现类
 * </p>
 *
 * @author 曹金洲
 * @since 2018-05-16
 */
@Service
public class MeterServiceImpl implements IMeterService {

    @Autowired
    private MeterMapper meterMapper;

    @Override
    @Log("查询用户所属电表")
    public Page<MeterInfo> selectMeterListByUserId(String uid) {
        List<MeterInfo> list = meterMapper.selectMetersByUserId(uid).getMetersList();
        Page<MeterInfo> page = new Page<MeterInfo>();
        page.setRecords(list);
        page.setCurrent(0);
        page.setTotal(list.size());
        page.setSize(10);
        return page;
    }
}
