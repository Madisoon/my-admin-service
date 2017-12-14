package com.alienlab.my.repository;

import com.alienlab.my.entity.SysImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 主页图片的repository
 *
 * @author Msater Zg
 * @create 2017-12-13 21:18
 */
public interface SysImageRepository extends JpaRepository<SysImage, Long> {
}
