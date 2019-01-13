package com.wwc.ypt.web.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private Long userId;
    private Long phone;
    private String password;
    private String city;
    private String lat;
    private String lng;
    private Byte type;
    private String userAvatar;
    private String userSex;
    private String nickName;
    private String lastLoginIp;
    private Date userBirthday;
}
