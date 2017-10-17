package com.alienlab.my.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "stockinfo")
public class StockInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "libraryId")
    private String libraryId;

    @Column(name = "isbn13")
    private String iSBN13;

    @Column(name = "isbn10")
    private String iSBN10;

    @Column(name = "stockTag")
    private int stockTag;

    @Column(name = "rranking")
    private int RRanking;

    @Column(name = "lastTime")
    private Date lastTime;

    @Column(name = "sumNo")
    private BigDecimal sumBNo;

    @Column(name = "userInfoId")
    private String userInfoId;

    /*@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;*/

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "book_info_id")
    private BookInfo bookInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getiSBN13() {
        return iSBN13;
    }

    public void setiSBN13(String iSBN13) {
        this.iSBN13 = iSBN13;
    }

    public String getiSBN10() {
        return iSBN10;
    }

    public void setiSBN10(String iSBN10) {
        this.iSBN10 = iSBN10;
    }

    public int getStockTag() {
        return stockTag;
    }

    public void setStockTag(int stockTag) {
        this.stockTag = stockTag;
    }

    public int getRRanking() {
        return RRanking;
    }

    public void setRRanking(int RRanking) {
        this.RRanking = RRanking;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public BigDecimal getSumBNo() {
        return sumBNo;
    }

    public void setSumBNo(BigDecimal sumBNo) {
        this.sumBNo = sumBNo;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
