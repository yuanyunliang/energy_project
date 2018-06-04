package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 自动结算临时表
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-17
 */
public class Automaticsettlement  {

    /**
     * 表主键
     */
	private Integer id;
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
     * 耗电量
     */
	private Double electricity;
    /**
     * 耗电时间区间的开始时间戳
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * 耗电时间区间的结束时间戳
     */
	@TableField("end_time")
	private Date endTime;
    /**
     * 添加时间
     */
	@TableField("set_time")
	private Date setTime;
    /**
     * 结算金额
     */
	private Double amount;
    /**
     * 电价
     */
	@TableField("electricity_price")
	private Double electricityPrice;


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

	public Date getSetTime() {
		return setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getElectricityPrice() {
		return electricityPrice;
	}

	public void setElectricityPrice(Double electricityPrice) {
		this.electricityPrice = electricityPrice;
	}


}
