package com.wwc.ypt.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "feedback")
@Entity
public class FeedBack {
    @Id
    @Column(name = "userId", nullable = false)
    private Long userId;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "feedbackContent", nullable = false)
    private String feedbackContent;
}
