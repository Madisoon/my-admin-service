package com.alienlab.my.module.book.service.imp;

import com.alienlab.my.entity.UserInfo;
import com.alienlab.my.module.book.service.UserManageService;
import com.alienlab.my.repository.UserInfoRepository;
import com.alienlab.my.utils.NumberInfoPost;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 * Created by zhuliang on 2017/10/12.
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    NumberInfoPost numberInfoPost;

    @Override
    public UserInfo getUserInfo(String vipNumber) {
        //生成EntityManger

        return this.userInfoRepository.findUserByReaderId(vipNumber);
    }

    @Override
    public String getUserYzNumber(String userPhone, String userCode) {
        String message = numberInfoPost.sendMsgByYunPian(userCode, userPhone);
        return message;
    }
}
