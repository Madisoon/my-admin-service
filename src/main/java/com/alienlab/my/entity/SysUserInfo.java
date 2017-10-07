package com.alienlab.my.entity;


import javax.persistence.*;

@Entity
@Table(name = "sysuserinfo")
public class SysUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserLoginName")
    private String UserLoginName;

    @Column(name = "UserPassWord")
    private String UserPassWord;

    public String getUserLoginName() {
        return UserLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        UserLoginName = userLoginName;
    }

    public String getUserPassWord() {
        return UserPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        UserPassWord = userPassWord;
    }
}
