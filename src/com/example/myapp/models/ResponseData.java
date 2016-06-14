package com.example.myapp.models;

import java.io.Serializable;

/**
 * Created by 李钊颖 on 2016/6/2.
 */
public class ResponseData implements Serializable {
    private String msg;
    private String ctx;
    private Boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
