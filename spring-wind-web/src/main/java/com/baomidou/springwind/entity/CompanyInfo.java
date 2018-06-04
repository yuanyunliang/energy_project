package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author Blse
 * @date 2018/5/3
 * @description
 */
@TableName(value = "company_info")
public class CompanyInfo {

    private String id;

    private String name;

    private String phone;

    private Double electricity;

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public String getId() {
        return id;
    }

    public CompanyInfo() {}

    public CompanyInfo(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", electricity=" + electricity +
                '}';
    }
}
