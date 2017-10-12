package com.alienlab.my.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "stockinfo")
public class StockInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn13")
    private String ISBN13;

    @Column(name = "isbn10")
    private String ISBN10;

    @Column(name = "stockTag")
    private int StockTag;

    @Column(name = "rranking")
    private int RRanking;

    @Column(name = "readerId")
    private String ReaderID;

    @Column(name = "lastTime")
    private Date LastTime;

    @Column(name = "sumNo")
    private BigDecimal SumBNo;

    @ManyToOne
    @JoinColumn(name = "book_info_id")
    private BookInfo bookInfo;

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
