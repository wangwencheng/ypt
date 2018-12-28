package com.wwc.ypt.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "banner")
@Entity
public class Banner {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "image_path", nullable = false)
    private String imagePath;
}
