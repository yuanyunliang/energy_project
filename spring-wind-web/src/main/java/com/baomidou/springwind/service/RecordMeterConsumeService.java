package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.RecordMeterConsume;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 */
public interface RecordMeterConsumeService {

    /**
     * 保存用户用电信息
     * @param recordMeterConsume
     */
    void saveRecordMeterConsume(RecordMeterConsume recordMeterConsume);
}
