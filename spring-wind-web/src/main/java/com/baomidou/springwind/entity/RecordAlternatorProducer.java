package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 发电机发电信息记录表
 * </p>
 *
 * @author
 * @since 2018-05-15
 */
@TableName("record_alternator_producer")
public class RecordAlternatorProducer extends Model<RecordAlternatorProducer> {

    private static final long serialVersionUID = 1L;

    /**
     * 表主键
     */
	private Integer id;
    /**
     * 发电机UUID - 外键
     */
	private String alternatorId;
	/**
	 * 发电机地址
	 */
	private String alternatorAddress;
    /**
     * 公司UUID - 外键
     */
	private String companyId;
    /**
     * 发电量
     */
	private Double electricity;
    /**
     * 发电时间区间的开始时间戳
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * 发电时间区间的结束时间戳
     */
	@TableField("end_time")
	private Date endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlternatorId() {
		return alternatorId;
	}

	public void setAlternatorId(String alternatorId) {
		this.alternatorId = alternatorId;
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

	public String getAlternatorAddress() {
		return alternatorAddress;
	}

	public void setAlternatorAddress(String alternatorAddress) {
		this.alternatorAddress = alternatorAddress;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
