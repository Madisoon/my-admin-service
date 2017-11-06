package com.alienlab.my.repository;

import com.alienlab.my.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findUserByReaderIdOrPhoneNo(String readerId, String phoneNo);
    UserInfo findUserByPhoneNoAndPassword(String phone,String password);
    UserInfo  findUserByPhoneNo(String phone);
}
