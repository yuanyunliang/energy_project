package com.baomidou.springwind.entity;

import java.util.Date;

public class RecordMeterConsume {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户电表id
     */
    private String meterId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 耗电量
     */
    private Double electricity;

    /**
     * 耗电时间区间的开始时间戳
     */
    private Date startTime;

    /**
     * 耗电时间区间的结束时间戳
     */
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId == null ? null : meterId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RecordMeterConsume{" +
                "id=" + id +
                ", meterId='" + meterId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", electricity=" + electricity +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

}