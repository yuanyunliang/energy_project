package com.baomidou.springwind.entity;

public class PersonalReports {


    private  Automaticsettlement automaticsettlement;


    /***
     * 用户余额
     */

    private Double money;

    public Automaticsettlement getAutomaticsettlement() {
        return automaticsettlement;
    }

    public void setAutomaticsettlement(Automaticsettlement automaticsettlement) {
        this.automaticsettlement = automaticsettlement;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
