package com.bway.caoyuan.bean;

import java.util.List;

public class MyFootBean {
    private String message;
    private String status;
    private List<FootBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FootBean> getResult() {
        return result;
    }

    public void setResult(List<FootBean> result) {
        this.result = result;
    }
}
