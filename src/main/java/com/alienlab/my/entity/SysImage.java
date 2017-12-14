package com.alienlab.my.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 主页图片
 *
 * @author Msater Zg
 * @create 2017-12-13 21:12
 */

@Entity
@Table(name = "sys_image")
public class SysImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sys_image_url")
    private String sysImageUrl;

    @Column(name = "news_url")
    private String newsUrl;

    @Column(name = "image_status")
    private int imageStatus;

    @Column(name = "image_time")
    private Date imageTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysImageUrl() {
        return sysImageUrl;
    }

    public void setSysImageUrl(String sysImageUrl) {
        this.sysImageUrl = sysImageUrl;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public int getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(int imageStatus) {
        this.imageStatus = imageStatus;
    }

    public Date getImageTime() {
        return imageTime;
    }

    public void setImageTime(Date imageTime) {
        this.imageTime = imageTime;
    }
}
