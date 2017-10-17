package com.alienlab.my.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "historyinfo")
public class HistoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "readerId")
    private String readerId;

    @Column(name = "libraryId")
    private String libraryId;

    @Column(name = "RRanking")
    private String RRanking;

    @Column(name = "orrowTime")
    private Date orrowTime;

    @Column(name = "returnTime")
    private Date returnTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getRRanking() {
        return RRanking;
    }

    public void setRRanking(String RRanking) {
        this.RRanking = RRanking;
    }

    public Date getOrrowTime() {
        return orrowTime;
    }

    public void setOrrowTime(Date orrowTime) {
        this.orrowTime = orrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}
