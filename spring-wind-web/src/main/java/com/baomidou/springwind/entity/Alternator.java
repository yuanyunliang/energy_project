package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * 发电机归属表
 * </p>
 *
 * @author Yanghu
 * @since 2018-05-15
 */
public class Alternator extends Model<Alternator> {

    private static final long serialVersionUID = 1L;

    /**
     * 发电机唯一表示 - UUID
     */
	@TableId("alternator_id")
	private String alternatorId;
    /**
     * 发电机所属公司UUID - 外键
     */
	@TableField("company_id")
	private String companyId;


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

	@Override
	protected Serializable pkVal() {
		return this.alternatorId;
	}

}
