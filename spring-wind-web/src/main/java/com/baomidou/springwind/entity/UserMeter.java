package com.baomidou.springwind.entity;

import java.util.List;

/**
 * TODO 2018/5/17 :用户所属电表类
 *
 * @author User : Golden_chow
 */
public class UserMeter {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 所属电表集合
     */
    private List<MeterInfo> metersList;

    public String getUid() {
        return userId;
    }

    public void setUid(String userId) {
        this.userId = userId;
    }

    public List<MeterInfo> getMetersList() {
        return metersList;
    }

    public void setMetersList(List<MeterInfo> metersList) {
        this.metersList = metersList;
    }

}
