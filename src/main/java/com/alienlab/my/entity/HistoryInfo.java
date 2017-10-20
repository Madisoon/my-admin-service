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

    @Column(name = "bookId")
    private Long bookId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "readerId")
    private UserInfo historyUser;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "libraryId")
    private StockInfo historyStockInfo;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private BookInfo historyBookInfo;*/

    /*public BookInfo getHistoryBookInfo() {
        return historyBookInfo;
    }*/
    /*public void setHistoryBookInfo(BookInfo historyBookInfo) {
        this.historyBookInfo = historyBookInfo;
    }*/

    public StockInfo getHistoryStockInfo() {
        return historyStockInfo;
    }
    @JsonIgnore
    public void setHistoryStockInfo(StockInfo historyStockInfo) {
        this.historyStockInfo = historyStockInfo;
    }

    public UserInfo getHistoryUser() {
        return historyUser;
    }

    @JsonIgnore
    public void setHistoryUser(UserInfo historyUser) {
        this.historyUser = historyUser;
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
