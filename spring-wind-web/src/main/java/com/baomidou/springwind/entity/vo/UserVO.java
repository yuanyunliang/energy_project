package com.baomidou.springwind.entity.vo;

/**
 * @author Blse
 * @date 2018/5/17
 * @description
 */
public class UserVO {

    private String id;

    private String account;

    private char freeze;

    private char type;

    private String phone;

    /**
     * 用户名
     */
    private String name;

    /**
     * 余额
     */
    private Double money;


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

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "UserVO{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", freeze=" + freeze +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
