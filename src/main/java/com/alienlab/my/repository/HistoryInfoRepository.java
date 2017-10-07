package com.alienlab.my.repository;

import com.alienlab.my.entity.HistoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryInfoRepository extends JpaRepository<HistoryInfo, Long> {
}
