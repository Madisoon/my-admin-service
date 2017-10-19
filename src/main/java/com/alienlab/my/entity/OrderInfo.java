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

    @Column(name = "libraryId")
    private String libraryId;

    @Column(name = "orderTime")
    private Date orderTime;

   /* @Column(name = "userInfoId")
    private String userInfoId;*/

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfoOrder;


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
        libraryId = libraryId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        orderTime = orderTime;
    }

   public UserInfo getUserInfo() {
        return userInfoOrder;
    }

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
