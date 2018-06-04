package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Blse
 * @date 2018/5/2
 * @description
 */
@TableName("user")
public class User {

    private String id;

    private String account;

    private String password;

    private char freeze;

    private char type;

    @Transient
    private UserInfo userInfo;

    @Transient
    private CompanyInfo companyInfo;

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public char getFreeze() {
        return freeze;
    }

    public void setFreeze(char freeze) {
        this.freeze = freeze;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", freeze=" + freeze +
                ", type=" + type +
                ", userInfo=" + userInfo +
                '}';
    }
}



