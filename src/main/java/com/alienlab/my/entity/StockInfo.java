package com.alienlab.my.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "stockinfo")
public class StockInfo {

    @ManyToOne
    private BookInfo bookInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LibraryId")
    private String LibraryId;

    @Column(name = "ISBN13")
    private String ISBN13;

    @Column(name = "ISBN10")
    private String ISBN10;

    @Column(name = "StockTag")
    private int StockTag;

    @Column(name = "RRanking")
    private int RRanking;

    @Column(name = "ReaderID")
    private String ReaderID;

    @Column(name = "LastTime")
    private Date LastTime;

    @Column(name = "SumBNo")
    private BigDecimal SumBNo;

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getLibraryID() {
        return LibraryId;
    }

    public void setLibraryID(String libraryId) {
        LibraryId = libraryId;
    }

    /*public String getBookId() {
        return BookInfoId;
    }

    public void setBookId(String bookId) {
        BookInfoId = BookInfoId;
    }*/

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) {
        this.ISBN10 = ISBN10;
    }

    public int getStockTag() {
        return StockTag;
    }

    public void setStockTag(int stockTag) {
        StockTag = stockTag;
    }

    public String getReaderID() {
        return ReaderID;
    }

    public void setReaderID(String readerID) {
        ReaderID = readerID;
    }

    public Date getLastTime() {
        return LastTime;
    }

    public void setLastTime(Date lastTime) {
        LastTime = lastTime;
    }

    public BigDecimal getSumBNo() {
        return SumBNo;
    }

    public void setSumBNo(BigDecimal sumBNo) {
        SumBNo = sumBNo;
    }

    public int getRRanking() {
        return RRanking;
    }

    public void setRRanking(int RRanking) {
        this.RRanking = RRanking;
    }
}
