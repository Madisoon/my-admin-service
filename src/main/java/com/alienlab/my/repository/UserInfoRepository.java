package com.alienlab.my.repository;

import com.alienlab.my.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findUserByReaderIdOrPhoneNo(String readerId, String phoneNo);
}
