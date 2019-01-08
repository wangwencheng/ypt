package com.wwc.ypt.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "banner")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;
    @Column(name = "phone", nullable = false)
    private Long phone;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "lat", nullable = false)
    private String lat;
    @Column(name = "lng", nullable = false)
    private String lng;
    @Column(name = "type", nullable = false)
    private Byte type;
    @Column(name = "user_avatar", nullable = false)
    private String userAvatar;
    @Column(name = "user_sex", nullable = false)
    private String userSex;
    @Column(name = "nick_name", nullable = false)
    private String nickName;
    @Column(name = "last_login_ip", nullable = false)
    private String lastLoginIp;
    @Column(name = "user_birthday", nullable = false)
    private String userBirthday;
}
