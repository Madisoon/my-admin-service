package com.alienlab.my.repository;

import com.alienlab.my.entity.HistoryInfo;
import com.alienlab.my.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryInfoRepository extends JpaRepository<HistoryInfo, Long> {
    List<HistoryInfo> findHistoryByHistoryUser(UserInfo userInfo);
}
