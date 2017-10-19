package com.alienlab.my.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "historyinfo")
public class HistoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "RRanking")
    private String RRanking;

    @Column(name = "borrowTime")
    private Date borrowTime;

    @Column(name = "returnTime")
    private Date returnTime;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "readerId")
    private UserInfo historyUser;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "libraryId")
    private StockInfo historyStockInfo;


    @ManyToOne
    @JoinColumn(name = "bookId")
    private BookInfo historyBookInfo;


    public BookInfo getHistoryBookInfo() {
        return historyBookInfo;
    }
    public void setHistoryBookInfo(BookInfo historyBookInfo) {
        this.historyBookInfo = historyBookInfo;
    }

    public StockInfo getStockInfo() {
        return historyStockInfo;
    }
    public void setStockInfo(StockInfo stockInfo) {
        this.historyStockInfo = stockInfo;
    }

    public UserInfo getUserInfoHistory() {
        return historyUser;
    }

    @JsonIgnore
    public void setUserInfoHistory(UserInfo userInfoHistory) {
        this.historyUser = userInfoHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRRanking() {
        return RRanking;
    }

    public void setRRanking(String RRanking) {
        this.RRanking = RRanking;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

}
