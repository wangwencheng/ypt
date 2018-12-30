package com.wwc.ypt.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "feedback")
@Entity
public class FeedBack {
    @Id
    @Column(name = "userId", nullable = false)
    private String userId;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "phone", nullable = false)
    private Long phone;
    @Column(name = "feedbackContent", nullable = false)
    private String feedbackContent;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
