package com.wwc.ypt.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name = "banner")
@Entity
public class Banner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "image_path", nullable = false)
    private String imagePath;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
