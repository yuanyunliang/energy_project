package com.baomidou.springwind.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RecordUserPay {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 充值的用户id
     */
    private String payUserId;

    /**
     * 充值总金额
     */
    private Double payAmount;

    /**
     * 充值订单号
     */
    private String payOrderNumber;

    /**
     * 充值订单类型，0代表支付宝，1代表微信
     */
    private String payOrderType;

    /**
     * 充值时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId == null ? null : payUserId.trim();
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayOrderNumber() {
        return payOrderNumber;
    }

    public void setPayOrderNumber(String payOrderNumber) {
        this.payOrderNumber = payOrderNumber == null ? null : payOrderNumber.trim();
    }

    public String getPayOrderType() {
        return payOrderType;
    }

    public void setPayOrderType(String payOrderType) {
        if ("0".equals(payOrderType)) {
            this.payOrderType = "支付宝支付";
        } else if ("1".equals(payOrderType)){
            this.payOrderType = "微信支付";
        } else {
            this.payOrderType = payOrderType;
        }
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "RecordUserPay{" +
                "id=" + id +
                ", payUserId='" + payUserId + '\'' +
                ", payAmount=" + payAmount +
                ", payOrderNumber='" + payOrderNumber + '\'' +
                ", payOrderType='" + payOrderType + '\'' +
                ", payTime=" + payTime +
                '}';
    }
}