package com.alienlab.my.module.book.service;

import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.UserInfo;

/**
 * Created by zhuliang on 2017/10/12.
 */
public interface UserManageService {
    public JSONObject getUserInfo(String vipNumber);

    public String getUserYzNumber(String userPhone, String userCode);

    public JSONObject vipBorrowReturn(String userPhone, String libraryId);

    public JSONObject postUserData(String userInfoData,
                                   String returnBookId,
                                   String borrowBookId,
                                   String orderBookId);

    public UserInfo getUserInfoAndBook(Long userId) throws Exception;

    public UserInfo userLogin(String username,String password) throws Exception;

    public JSONObject getuserWatchBook(Long userId) throws Exception;

    public void deleteSaveBook(Long userId,Long bookId) throws Exception;

    public void deleteOrderBook(Long userId,Long bookId) throws Exception;

    public UserInfo regist(UserInfo userInfo)throws Exception;
}
