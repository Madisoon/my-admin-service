package com.alienlab.my.entity;

import javax.persistence.*;

/**
 * Created by Msater Zg on 2017/10/5.
 */
@Entity
@Table(name = "MemType")
public class MemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemID")
    private int MemID;

    @Column(name = "MemName")
    private String MemName;

    @Column(name = "MaxNo")
    private int MaxNo;

    @Column(name = "BMoney")
    private int BMoney;

    @Column(name = "Deposit")
    private int Deposit;

    @Column(name = "DuringTime")
    private int DuringTime;

    public int getMemID() {
        return MemID;
    }

    public void setMemID(int memID) {
        MemID = memID;
    }

    public String getMemName() {
        return MemName;
    }

    public void setMemName(String memName) {
        MemName = memName;
    }

    public int getMaxNo() {
        return MaxNo;
    }

    public void setMaxNo(int maxNo) {
        MaxNo = maxNo;
    }

    public int getBMoney() {
        return BMoney;
    }

    public void setBMoney(int BMoney) {
        this.BMoney = BMoney;
    }

    public int getDeposit() {
        return Deposit;
    }

    public void setDeposit(int deposit) {
        Deposit = deposit;
    }

    public int getDuringTime() {
        return DuringTime;
    }

    public void setDuringTime(int duringTime) {
        DuringTime = duringTime;
    }
}
