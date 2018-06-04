package com.baomidou.framework.common;

import java.util.List;

/**
 * @author Blse
 * @date 2018/5/2
 * @description     用于返回数据
 */
public class AjaxResult<T> {

    private Boolean success;

    private String msg;

    private T t;

    private List<T> list;

    public AjaxResult(Boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
