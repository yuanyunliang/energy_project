package com.baomidou.springwind.entity;

import static com.baomidou.springwind.utils.EncryptUtil.SHA256;

/**
 * @Author XieYongJie
 * @Date 2018/5/27 6:03
 */
public class AccountJson {
    private String companyId;
    private String meterId;
    private String electricity;
    private String account;
    private String tradingTime;
    private String checkSum;

    public AccountJson() {}

    public AccountJson(String companyId, String meterId, String electricity, String account, String tradingTime, String checkSum) {
        this.companyId = companyId;
        this.meterId = meterId;
        this.electricity = electricity;
        this.account = account;
        this.tradingTime = tradingTime;
        this.checkSum = checkSum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(String tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
}
