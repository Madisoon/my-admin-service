package com.alienlab.my.repository;

import com.alienlab.my.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    OrderInfo findOrderInfoByReaderIDAndLibraryID(String readerId,String libraryId);
    List<OrderInfo> findOrderByReaderID(String readerId);
}
