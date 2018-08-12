package com.xiaofan.sell.product.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String id;

    private String username;

    private String password;

    private String openid;

    private Boolean role;

    private Date createTime;

    private Date updateTime;

}