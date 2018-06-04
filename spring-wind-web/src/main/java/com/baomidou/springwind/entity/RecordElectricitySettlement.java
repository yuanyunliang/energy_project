package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 用电结算信息记录表
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-15
 */
@TableName("record_electricity_settlement")
public class RecordElectricitySettlement {


    /**
     * 表主键
     */
	private Integer id;
    /**
     * 用户UUID - 外键
     */
	@TableField("user_id")
	private String userId;
    /**
     * 用户电表UUID - 外键
     */
	@TableField("meter_id")
	private String meterId;
    /**
     * 公司UUID - 外键
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 结算时的电价
     */
	@TableField("electricity_price")
	private Double electricityPrice;
    /**
     * 结算总金额
     */
	private Double amount;
    /**
     * 结算时间
     */
	@TableField("settlement_time")
	private Date settlementTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public Double getElectricityPrice() {
		return electricityPrice;
	}

	public void setElectricityPrice(Double electricityPrice) {
		this.electricityPrice = electricityPrice;
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
