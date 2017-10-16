package com.alienlab.my.repository;

import com.alienlab.my.entity.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo, Long> {
    public StockInfo findStockByLibraryId(String libraryId);
}
