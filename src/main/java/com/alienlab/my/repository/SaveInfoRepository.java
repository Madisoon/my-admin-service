package com.alienlab.my.repository;

import com.alienlab.my.entity.SaveInfo;
import com.alienlab.my.entity.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SaveInfoRepository extends JpaRepository<StockInfo, Long> {

    SaveInfo findSaveByReaderIDAndLibraryID(String readerId,String libraryId);
}
