package com.alienlab.my.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "historyinfo")
public class HistoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "ReaderID")
    private String ReaderID;

    @Column(name = "LibraryID")
    private String LibraryID;

    @Column(name = "RRanking")
    private String RRanking;

    @Column(name = "BorrowTime")
    private Date BorrowTime;

    @Column(name = "ReturnTime")
    private Date ReturnTime;

    public String getReaderID() {
        return ReaderID;
    }

    public void setReaderID(String readerID) {
        ReaderID = readerID;
    }

    public String getLibraryID() {
        return LibraryID;
    }

    public void setLibraryID(String libraryID) {
        LibraryID = libraryID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRRanking() {
        return RRanking;
    }

    public void setRRanking(String RRanking) {
        this.RRanking = RRanking;
    }

    public Date getBorrowTime() {
        return BorrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        BorrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(Date returnTime) {
        ReturnTime = returnTime;
    }

    @Override
    public String toString() {
        return "HistoryInfo{" +
                "id=" + id +
                ", ReaderID='" + ReaderID + '\'' +
                ", LibraryID='" + LibraryID + '\'' +
                ", RRanking='" + RRanking + '\'' +
                ", BorrowTime=" + BorrowTime +
                ", ReturnTime=" + ReturnTime +
                '}';
    }
}
