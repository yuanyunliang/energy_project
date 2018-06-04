package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 电表信息表
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-16
 */
@TableName("meter_info")
public class MeterInfo extends Model<MeterInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 电表UUID - 外键
     */
	@TableId("meter_id")
	private String meterId;
    /**
     * 电表 - 地址
     */
	@TableField("meter_address")
	private String meterAddress;
    /**
     * 电表 - 上线时间
     */
	@TableField("meter_online_time")
	private Date meterOnlineTime;


	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public String getMeterAddress() {
		return meterAddress;
	}

	public void setMeterAddress(String meterAddress) {
		this.meterAddress = meterAddress;
	}

	public Date getMeterOnlineTime() {
		return meterOnlineTime;
	}

	public void setMeterOnlineTime(Date meterOnlineTime) {
		this.meterOnlineTime = meterOnlineTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.meterId;
	}

}
