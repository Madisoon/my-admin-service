package com.alienlab.my.module.book.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.*;
import com.alienlab.my.module.book.service.UserManageService;
import com.alienlab.my.repository.*;
import com.alienlab.my.utils.NumberInfoPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by zhuliang on 2017/10/12.
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    NumberInfoPost numberInfoPost;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    StockInfoRepository stockInfoRepository;

    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    SaveInfoRepository saveInfoRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookInfoRepository bookInfoRepository;

    @Override
    public JSONObject getUserInfo(String vipNumber) {
        //生成EntityMange


        //预定信息

        String sqlStock = "SELECT b.*,c.* FROM userinfo a,orderinfo  b,bookinfo c  " +
                "WHERE  (a.reader_id = '" + vipNumber + "' OR a.phone_no='" + vipNumber + "')   " +
                "AND a.id = b.user_info_id  " +
                "AND  b.library_id = c.id";
        System.out.println(sqlStock);
        List<Map<String, Object>> listStock = jdbcTemplate.queryForList(sqlStock);
        JSONArray jsonArrayStock = (JSONArray) JSON.toJSON(listStock);

        //个人信息
        String personSql = "SELECT * FROM userinfo a WHERE  (a.reader_id = '" + vipNumber + "' OR a.phone_no='" + vipNumber + "')";
        Map<String, Object> listPerson = jdbcTemplate.queryForMap(personSql);
        JSONObject jsonObjectPerson = (JSONObject) JSON.toJSON(listPerson);
        //已订信息
        String sql = "SELECT b.*,c.* FROM userinfo a,stockinfo b,bookinfo c WHERE  (a.reader_id = '" + vipNumber + "' OR a.phone_no='" + vipNumber + "'  " +
                ") AND a.id = b.user_info_id AND  b.book_info_id = c.id";
        List<Map<String, Object>> listOrder = jdbcTemplate.queryForList(sql);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(listOrder);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("person", jsonObjectPerson);
        jsonObject.put("order", jsonArrayStock);
        jsonObject.put("stock", jsonArray);
        return jsonObject;
    }

    @Override
    public String getUserYzNumber(String userPhone, String userCode) {
        String message = numberInfoPost.sendMsgByYunPian(userCode, userPhone);
        return message;
    }

    @Override
    public JSONObject vipBorrowReturn(String userPhone, String libraryId) {
        UserInfo userInfo = userInfoRepository.findUserByReaderIdOrPhoneNo(userPhone, userPhone);
        StockInfo stockInfo = stockInfoRepository.findStockByLibraryIdAndUserInfoId(libraryId, String.valueOf(userInfo.getId()));
        String sql = "SELECT b.* FROM stockinfo a ,bookinfo b  " +
                "WHERE a.book_info_id = b.id AND a.library_id = '" + libraryId + "'";
        Map<String, Object> bookInfo = jdbcTemplate.queryForMap(sql);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(bookInfo);
        BookInfo bookInfo1 = bookInfoRepository.findOne(jsonObject.getLong("id"));
        OrderInfo orderInfo = orderInfoRepository.findOrderInfoByUserInfoOrderIdAndOrderBookInfo(userInfo, bookInfo1);
        JSONObject returnJsonObject = new JSONObject();
        returnJsonObject.put("book", jsonObject);
        if (stockInfo == null) {
            // 没有这本书
            if (orderInfo == null) {
                // 没有预定
                returnJsonObject.put("result", "1");
            } else {
                // 预定了
                returnJsonObject.put("result", "2");
            }
        } else {
            // 有这本书
            returnJsonObject.put("result", "3");
        }
        return returnJsonObject;
    }

    @Override
    public JSONObject postUserData(String userInfoData, String returnBookId, String borrowBookId, String orderBookId) {
        System.out.println(userInfoData);
        System.out.println(returnBookId);
        System.out.println(borrowBookId);
        System.out.println(orderBookId);
        String[] returnBookIds = returnBookId.split(",");
        int returnBookIdsLen = returnBookIds.length;
        String[] borrowBookIds = borrowBookId.split(",");
        int borrowBookIdsLen = borrowBookIds.length;
        String[] orderBookIds = orderBookId.split(",");
        int orderBookIdsLen = orderBookIds.length;
        UserInfo userInfo = JSON.parseObject(userInfoData, UserInfo.class);
        /*userInfoRepository.save(userInfo);*/
        for (int i = 0; i < returnBookIdsLen; i++) {
            // 将书的user_infor_id 变成88888888
            String sql = "UPDATE stockinfo a SET a.user_info_id = '88888888' WHERE a.library_id='" + returnBookIds[i] + "'";
            jdbcTemplate.update(sql);
        }
        for (int i = 0; i < borrowBookIdsLen; i++) {
            // 借书，将书的user_infor_id 变成id
            String sql = "UPDATE stockinfo a SET a.user_info_id = '" + userInfo.getId() + "' WHERE a.library_id='" + borrowBookIds[i] + "'";
            jdbcTemplate.update(sql);
        }
        String delete = "DELETE FROM saveinfo WHERE user_info_id ='" + userInfo.getId() + "'";
        jdbcTemplate.update(delete);
        for (int i = 0; i < orderBookIdsLen; i++) {
            // 先删除预定信息，然后重新插入id
            SaveInfo saveInfo = new SaveInfo();
            saveInfo.setUserInfo(userInfo);
           /* saveInfo.setLibraryId(orderBookIds[i]);*/
            saveInfoRepository.save(saveInfo);
        }
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    @Override
    public UserInfo getUserInfoAndBook(Long userId) throws Exception {
        UserInfo userInfo = userInfoRepository.findOne(userId);
        if (userInfo == null){
            throw new Exception("没有对应用户信息");
        }
        return userInfo;
    }

    @Override
    public UserInfo userLogin(String username, String password) throws Exception {
        UserInfo userInfo = userInfoRepository.findUserByPhoneNoAndPassword(username,password);
        if(userInfo==null){
            throw  new Exception("用户名或密码错误！");
        }
        return userInfo;
    }
}
