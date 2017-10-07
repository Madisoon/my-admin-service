package com.alienlab.my.repository;

import com.alienlab.my.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoReposItory extends JpaRepository<UserInfo, Long> {
}
