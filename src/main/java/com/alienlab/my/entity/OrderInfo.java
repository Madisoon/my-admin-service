package com.alienlab.my.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderinfo")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "orderTime")
    private Date orderTime;

   /* @Column(name = "userInfoId")
    private String userInfoId;*/

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfoOrder;

    @ManyToOne
    @JoinColumn(name = "libraryId")
    private BookInfo orderBookInfo;

    public BookInfo getOrderBookInfo() {
        return orderBookInfo;
    }


    public void setOrderBookInfo(BookInfo orderBookInfo) {
        this.orderBookInfo = orderBookInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

   public UserInfo getUserInfo() {
        return userInfoOrder;
    }

    @JsonIgnore
    public void setUserInfo(UserInfo userInfo) {
        this.userInfoOrder = userInfo;
    }

    /*public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }*/
}
