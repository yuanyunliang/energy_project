package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Blse
 * @date 2018/5/2
 * @description
 */
@TableName("user_info")
public class UserInfo {

    /**
     * id
     */
    private String id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String name;

    /**
     * 余额
     */
    private Double money;

    /**
     * 用户所选择的发电公司的id
     */
    private String companyId;

    public UserInfo() {}

    public UserInfo(String id,String phone, String name) {
        this.id = id;
        this.phone = phone;
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
