package com.alienlab.my.repository;

import com.alienlab.my.entity.BookNews;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 所有新闻的JpaRepository
 *
 * @author Msater Zg
 * @create 2017-11-02 19:50
 */
public interface BookNewsRepository extends JpaRepository<BookNews, Long> {
}
