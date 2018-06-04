package com.baomidou.springwind.entity;

import java.util.Date;

/**
 * TODO 2018/6/1 :
 *
 * @author User : Golden_chow
 */
public class AlternatorInfo {

    private String aId;

    private String aAddress;

    private Date aOnlineTime;

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getaAddress() {
        return aAddress;
    }

    public void setaAddress(String aAddress) {
        this.aAddress = aAddress;
    }

    public Date getaOnlineTime() {
        return aOnlineTime;
    }

    public void setaOnlineTime(Date aOnlineTime) {
        this.aOnlineTime = aOnlineTime;
    }
}
