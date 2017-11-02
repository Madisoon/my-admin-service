package com.alienlab.my.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 所有新闻的entity
 *
 * @author Msater Zg
 * @create 2017-11-02 19:50
 */

@Entity
@Table(name = "booknews")
public class BookNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "news_title")
    private String newsTitle;

    @Column(name = "news_content")
    private String newsContent;

    @Column(name = "news_type")
    private String newsType;

    @Column(name = "news_time")
    private Date newsTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }
}
