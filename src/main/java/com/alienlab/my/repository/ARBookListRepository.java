
package com.alienlab.my.repository;

import com.alienlab.my.entity.ARBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ARBookListRepository extends JpaRepository<ARBookList, Long> {
}

