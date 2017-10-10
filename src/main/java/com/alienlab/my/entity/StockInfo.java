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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LibraryID")
    private String LibraryID;

    @Column(name = "BookId")
    private String BookId;

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
        return LibraryID;
    }

    public void setLibraryID(String libraryID) {
        LibraryID = libraryID;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

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

    @Override
    public String toString() {
        return "StockInfo{" +
                "bookInfo=" + bookInfo +
                ", LibraryID='" + LibraryID + '\'' +
                ", BookId='" + BookId + '\'' +
                ", ISBN13='" + ISBN13 + '\'' +
                ", ISBN10='" + ISBN10 + '\'' +
                ", StockTag=" + StockTag +
                ", RRanking=" + RRanking +
                ", ReaderID='" + ReaderID + '\'' +
                ", LastTime=" + LastTime +
                ", SumBNo=" + SumBNo +
                '}';
    }
}
