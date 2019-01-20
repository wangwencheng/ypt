package com.wwc.ypt.web.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private Long userId;
    private Long phone;
    private String password;
    private String city;
    private Byte type;
    private String lat;
    private String lng;
    private String userAvatar;
    private Byte userSex;
    private String nickName;
    private String lastLoginIp;
    private Date userBirthday;

    public void modifyAvatar(UserRequest userRequest) {
    }
}
