package com.baomidou.springwind.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RefundData {

    /**
     * 主键
     */
    private Integer refundId;

    /**
     * 申请退款的用户id
     */
    private String userId;

    /**
     * 退款总金额
     */
    private Double refundAmount;

    /**
     * 退款帐号
     */
    private String refundAccount;

    /**
     * 退款帐号类型，0代表支付宝，1代表微信
     */
    private String refundAccountType;

    /**
     * 退款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date refundTime;

    /**
     * 退款状态，0代表未退款，1表示已退款
     */
    private String refundStatus;

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount == null ? null : refundAccount.trim();
    }

    public String getRefundAccountType() {
        return refundAccountType;
    }

    public void setRefundAccountType(String refundAccountType) {
        if ("0".equals(refundAccountType)) {
            this.refundAccountType = "支付宝";
        } else if ("1".equals(refundAccountType)) {
            this.refundAccountType = "微信";
        } else {
            this.refundAccountType = refundAccountType;
        }
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        if ("0".equals(refundStatus)) {
            this.refundStatus = "未退款";
        } else if ("1".equals(refundStatus)){
            this.refundStatus = "已退款";
        } else {
            this.refundStatus = refundStatus;
        }
    }

    @Override
    public String toString() {
        return "RefundData{" +
                "refundId=" + refundId +
                ", userId='" + userId + '\'' +
                ", refundAmount=" + refundAmount +
                ", refundAccount='" + refundAccount + '\'' +
                ", refundAccountType='" + refundAccountType + '\'' +
                ", refundTime=" + refundTime +
                ", refundStatus='" + refundStatus + '\'' +
                '}';
    }
}