package com.alienlab.my.repository;

import com.alienlab.my.entity.WebConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhuliang on 2017/12/6.
 */
@Repository
public interface WebConfigurationRepository extends JpaRepository<WebConfiguration,Long> {
}
