package com.baomidou.springwind.entity;

/**
 * @author Blse
 * @date 2018/5/2
 * @description     公司发电
 */
public class CompanyElectricity {

    /**
     *  电表的id
     */

    private Long id;

    /**
     * 电表对应公司的id
     */
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

