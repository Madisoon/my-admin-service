package com.alienlab.my.module.book.service;

import com.alibaba.fastjson.JSONObject;

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
}
