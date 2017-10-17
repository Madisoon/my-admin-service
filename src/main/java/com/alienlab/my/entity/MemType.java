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
    private Long id;

    @Column(name = "memName")
    private String memName;

    @Column(name = "maxNo")
    private int maxNo;

    @Column(name = "bMoney")
    private int bMoney;

    @Column(name = "deposit")
    private int deposit;

    @Column(name = "duringTime")
    private int duringTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public int getMaxNo() {
        return maxNo;
    }

    public void setMaxNo(int maxNo) {
        this.maxNo = maxNo;
    }

    public int getbMoney() {
        return bMoney;
    }

    public void setbMoney(int bMoney) {
        this.bMoney = bMoney;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getDuringTime() {
        return duringTime;
    }

    public void setDuringTime(int duringTime) {
        this.duringTime = duringTime;
    }
}
