package com.alienlab.my.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "readerId")
    private String readerId;

    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name = "password")
    private String password;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "rName")
    private String rName;

    @Column(name = "rSex")
    private String rSex;

    @Column(name = "rBirthday")
    private Date rBirthday;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "memId")
    private int memId;

    @Column(name = "paidTag")
    private String paidTag;

    @Column(name = "registerDate")
    private Date registerDate;

    @Column(name = "paidDate")
    private Date paidDate;

    @Column(name = "paidInfo")
    private String paidInfo;

    @Column(name = "memStart")
    private Date memStart;

    @Column(name = "memEnd")
    private Date memEnd;

    @Column(name = "ARSuggestion")
    private String ARSuggestion;

    @Column(name = "lexileSuggestion")
    private String lexileSuggestion;

    @Column(name = "rCount")
    private int rCount;

    @Column(name = "rwCount")
    private int rwCount;

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrSex() {
        return rSex;
    }

    public void setrSex(String rSex) {
        this.rSex = rSex;
    }

    public Date getrBirthday() {
        return rBirthday;
    }

    public void setrBirthday(Date rBirthday) {
        this.rBirthday = rBirthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMemId() {
        return memId;
    }

    public void setMemId(int memId) {
        this.memId = memId;
    }

    public String getPaidTag() {
        return paidTag;
    }

    public void setPaidTag(String paidTag) {
        this.paidTag = paidTag;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public String getPaidInfo() {
        return paidInfo;
    }

    public void setPaidInfo(String paidInfo) {
        this.paidInfo = paidInfo;
    }

    public Date getMemStart() {
        return memStart;
    }

    public void setMemStart(Date memStart) {
        this.memStart = memStart;
    }

    public Date getMemEnd() {
        return memEnd;
    }

    public void setMemEnd(Date memEnd) {
        this.memEnd = memEnd;
    }

    public String getARSuggestion() {
        return ARSuggestion;
    }

    public void setARSuggestion(String ARSuggestion) {
        this.ARSuggestion = ARSuggestion;
    }

    public String getLexileSuggestion() {
        return lexileSuggestion;
    }

    public void setLexileSuggestion(String lexileSuggestion) {
        this.lexileSuggestion = lexileSuggestion;
    }

    public int getrCount() {
        return rCount;
    }

    public void setrCount(int rCount) {
        this.rCount = rCount;
    }

    public int getRwCount() {
        return rwCount;
    }

    public void setRwCount(int rwCount) {
        this.rwCount = rwCount;
    }
}
