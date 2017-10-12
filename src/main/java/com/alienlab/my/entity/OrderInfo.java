package com.alienlab.my.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderinfo")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ReaderID")
    private String readerID;

    @Column(name = "LibraryID")
    private String libraryID;

    @Column(name = "OrderTime")
    private Date orderTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        readerID = readerID;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(String libraryID) {
        libraryID = libraryID;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        orderTime = orderTime;
    }
}
