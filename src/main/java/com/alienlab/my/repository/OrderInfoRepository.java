package com.alienlab.my.repository;

import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    OrderInfo findOrderInfoByUserInfoOrderIdAndLibraryId(UserInfo userInfo,String libraryId);
    List<OrderInfo> findOrderByUserInfoOrder(UserInfo userInfo);
}
