package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.RecordMeterConsume;
import com.baomidou.springwind.mapper.RecordMeterConsumeMapper;
import com.baomidou.springwind.service.RecordMeterConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
@Service
public class RecordMeterConsumeServiceImpl implements RecordMeterConsumeService {

    @Autowired
    private RecordMeterConsumeMapper recordMeterConsumeMapper;

    @Override
    public void saveRecordMeterConsume(RecordMeterConsume recordMeterConsume) {
        recordMeterConsumeMapper.saveRecordMeterConsume(recordMeterConsume);
    }
}
