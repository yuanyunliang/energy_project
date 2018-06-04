package com.baomidou.springwind.entity;

/**
 * @author Blse
 * @date 2018/5/2
 * @description 用户发电
 */
public class UserElectricity {

    /**
     *  电表的id
     */

    private Long id;

    /**
     * 电表对应用户的id
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
