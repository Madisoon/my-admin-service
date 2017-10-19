package com.alienlab.my.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "saveinfo")
public class SaveInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


   /* @Column(name = "userInfoId")
    private String userInfoId;*/

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "libraryId")
    private BookInfo saveBookInfo;


    public BookInfo getSaveBookInfo() {
        return saveBookInfo;
    }


    public void setSaveBookInfo(BookInfo saveBookInfo) {
        this.saveBookInfo = saveBookInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

  /*  public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }*/
}
