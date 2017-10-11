package com.alienlab.my.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userinfo")
public class UserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "ReaderID")
    private String ReaderID;

    @Column(name = "PhoneNo")
    private String PhoneNo;

    @Column(name = "Password")
    private String Password;

    @Column(name = "NickName")
    private String NickName;

    @Column(name = "RName")
    private String RName;

    @Column(name = "RSex")
    private String RSex;

    @Column(name = "RBirthday")
    private Date RBirthday;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Address")
    private String Address;

    @Column(name = "Email")
    private String Email;

    @Column(name = "MemID")
    private int MemID;

    @Column(name = "PaidTag")
    private String PaidTag;

    @Column(name = "RegisterDate")
    private Date RegisterDate;

    @Column(name = "PaidDate")
    private Date PaidDate;

    @Column(name = "PaidInfo")
    private String PaidInfo;

    @Column(name = "MemStart")
    private Date MemStart;

    @Column(name = "MemEnd")
    private Date MemEnd;

    @Column(name = "ARSuggestion")
    private String ARSuggestion;

    @Column(name = "LexileSuggestion")
    private String LexileSuggestion;

    @Column(name = "RCount")
    private int RCount;

    @Column(name = "RWCount")
    private int RWCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReaderID() {
        return ReaderID;
    }

    public void setReaderID(String readerID) {
        ReaderID = readerID;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public String getRSex() {
        return RSex;
    }

    public void setRSex(String RSex) {
        this.RSex = RSex;
    }

    public Date getRBirthday() {
        return RBirthday;
    }

    public void setRBirthday(Date RBirthday) {
        this.RBirthday = RBirthday;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMemID() {
        return MemID;
    }

    public void setMemID(int memID) {
        MemID = memID;
    }

    public String getPaidTag() {
        return PaidTag;
    }

    public void setPaidTag(String paidTag) {
        PaidTag = paidTag;
    }

    public Date getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(Date registerDate) {
        RegisterDate = registerDate;
    }

    public Date getPaidDate() {
        return PaidDate;
    }

    public void setPaidDate(Date paidDate) {
        PaidDate = paidDate;
    }

    public String getPaidInfo() {
        return PaidInfo;
    }

    public void setPaidInfo(String paidInfo) {
        PaidInfo = paidInfo;
    }

    public Date getMemStart() {
        return MemStart;
    }

    public void setMemStart(Date memStart) {
        MemStart = memStart;
    }

    public Date getMemEnd() {
        return MemEnd;
    }

    public void setMemEnd(Date memEnd) {
        MemEnd = memEnd;
    }

    public String getARSuggestion() {
        return ARSuggestion;
    }

    public void setARSuggestion(String ARSuggestion) {
        this.ARSuggestion = ARSuggestion;
    }

    public String getLexileSuggestion() {
        return LexileSuggestion;
    }

    public void setLexileSuggestion(String lexileSuggestion) {
        LexileSuggestion = lexileSuggestion;
    }

    public int getRCount() {
        return RCount;
    }

    public void setRCount(int RCount) {
        this.RCount = RCount;
    }

    public int getRWCount() {
        return RWCount;
    }

    public void setRWCount(int RWCount) {
        this.RWCount = RWCount;
    }
}
