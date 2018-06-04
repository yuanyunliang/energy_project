package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * 电表归属表
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-16
 */
public class Meter extends Model<Meter> {

    private static final long serialVersionUID = 1L;

    /**
     * 电表唯一标识 - UUID
     */
	@TableId("meter_id")
	private String meterId;
    /**
     * 电表所属用户UUID - 外键
     */
	@TableField("user_id")
	private String userId;

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.meterId;
	}

}
