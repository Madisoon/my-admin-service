package com.alienlab.my.repository;

import com.alienlab.my.entity.BookInfo;
import com.alienlab.my.entity.SaveInfo;
import com.alienlab.my.entity.StockInfo;
import com.alienlab.my.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SaveInfoRepository extends JpaRepository<SaveInfo, Long> {

    SaveInfo findSaveInfoByUserInfoAndSaveBookInfo(UserInfo userInfo, BookInfo bookInfo);
}
