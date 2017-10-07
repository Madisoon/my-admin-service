package com.alienlab.my.repository;

import com.alienlab.my.entity.MemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemTypeRepository extends JpaRepository<MemType, Long> {
}
