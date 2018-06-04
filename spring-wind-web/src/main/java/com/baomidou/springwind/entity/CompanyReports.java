package com.baomidou.springwind.entity;

import java.util.Date;

public class CompanyReports {

    /**
     * 用户电表UUID - 外键
     */
    private String meterId;
    /**
     * 公司UUID - 外键
     */
    private String companyId;
    /**
     * 发电量
     */
    private Double electricity;
    /***
     * 公司储电量
     */
    private Double company_electricity;
    /**
     * 耗电量
     */
    private Double consumption;
    /**
     * 结算总金额
     */
    private Double amount;
    /**
     * 结算时间
     */
    private Date settlementTime;

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public Double getCompany_electricity() {
        return company_electricity;
    }

    public void setCompany_electricity(Double company_electricity) {
        this.company_electricity = company_electricity;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }
}
