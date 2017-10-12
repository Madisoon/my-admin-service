package com.alienlab.my.repository;

import com.alienlab.my.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
    BookInfo findBookByISBN13(String isbn);
};
