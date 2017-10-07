package com.alienlab.my.repository;

import com.alienlab.my.entity.LexileBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LexileBookListRepository extends JpaRepository<LexileBookList, Long> {
}
