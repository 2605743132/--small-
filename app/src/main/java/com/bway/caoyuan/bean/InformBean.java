package com.bway.caoyuan.bean;

public class InformBean {
    private long createTime;
    private String headPic;
    private String nickName;
    private String password;
    private String phone;
    private int sex;
    private int userId;

    public InformBean(long createTime, String headPic, String nickName, String password, String phone, int sex, int userId) {
        this.createTime = createTime;
        this.headPic = headPic;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.userId = userId;
    }
}
