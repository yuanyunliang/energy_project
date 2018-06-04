package com.baomidou.springwind.common;

/**
 * <p>Description:</p>
 *
 * @Author 姚洪斌
 * @Date 2018/5/08 16:25
 */
public class RequestResult {

    /**
     * 应答码：成功：1001，失败：1002
     */
    private Integer code;

    /**
     * 操作结果提示
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
