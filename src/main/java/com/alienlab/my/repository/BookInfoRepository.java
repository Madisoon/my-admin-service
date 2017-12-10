package com.alienlab.my.repository;

import com.alienlab.my.entity.BookInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
    BookInfo findBookByISBN13OrISBN10(String isbn, String isbn10);

    Page<BookInfo> findBookByISBN13OrISBN10OrNameContainingOrAuthorContainingAndLexileTag(String value, String value2, String value3, String value4, int tag, Pageable pageable);

    Page<BookInfo> findBookByISBN13OrISBN10OrNameContainingOrAuthorContainingAndArtag(String value, String value2, String value3, String value4, int tag, Pageable pageable);

    Page<BookInfo> findBookByISBN13OrISBN10OrNameContainingOrAuthorContaining(String value, String value2, String value3, String value4, Pageable pageable);

    Page<BookInfo> findBookByLexileTag(int tag, Pageable pageable);

    Page<BookInfo> findBookByArtag(int tag, Pageable pageable);

    Page<BookInfo> findBookByOrderByRecommendIndexDesc(Pageable pageable);

    Page<BookInfo> findBookByRecommendIndexGreaterThan( int recommend,Pageable pageable);


};
