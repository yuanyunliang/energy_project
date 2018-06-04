package com.baomidou.springwind.entity;

import java.util.List;

/**
 * <p>Description:layui分页对象</p>
 *
 * @Author 姚洪斌
 */
public class LayuiPage<T> {

    /**
     * 状态码，0表示成功
     */
    private Integer code;

    /**
     * 请求信息，可以不填
     */
    private String msg;

    /**
     * 数据总数
     */
    private Integer count;

    /**
     * 分页数据
     */
    private List<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
