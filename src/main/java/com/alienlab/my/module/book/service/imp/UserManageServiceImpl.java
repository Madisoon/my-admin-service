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

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    HistoryInfoRepository historyInfoRepository;

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
        OrderInfo orderInfo = orderInfoRepository.findOrderInfoByUserInfoOrderAndOrderBookInfo(userInfo, bookInfo1);
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
        String[] returnBookIds = returnBookId.split(",");
        int returnBookIdsLen = returnBookIds.length;
        String[] borrowBookIds = borrowBookId.split(",");
        int borrowBookIdsLen = borrowBookIds.length;
        String[] orderBookIds = orderBookId.split(",");
        int orderBookIdsLen = orderBookIds.length;
        UserInfo userInfo = JSON.parseObject(userInfoData, UserInfo.class);
        UserInfo userInfoDataPwd = userInfoRepository.findOne(userInfo.getId());
        userInfo.setPassword(userInfoDataPwd.getPassword());
        userInfoRepository.save(userInfo);
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
        String delete = "DELETE FROM orderinfo WHERE user_info_id ='" + userInfo.getId() + "'";
        jdbcTemplate.update(delete);
        for (int i = 0; i < orderBookIdsLen; i++) {
            OrderInfo orderInfo = new OrderInfo();
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(Long.valueOf(orderBookIds[i]));
            orderInfo.setOrderBookInfo(bookInfo);
            orderInfo.setUserInfo(userInfo);
            orderInfo.setOrderTime(new Date());
            orderInfoRepository.save(orderInfo);
        }
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    @Override
    public UserInfo getUserInfoAndBook(Long userId) throws Exception {
        UserInfo userInfo = userInfoRepository.findOne(userId);
        if (userInfo == null) {
            throw new Exception("没有对应用户信息");
        }
        return userInfo;
    }

    @Override
    public UserInfo userLogin(String username, String password) throws Exception {
        UserInfo userInfo = userInfoRepository.findUserByPhoneNoAndPassword(username, password);
        if (userInfo == null) {
            throw new Exception("用户名或密码错误！");
        }
        return userInfo;
    }

    @Override
    public JSONObject getuserWatchBook(Long userId) throws Exception {
        JSONObject result = new JSONObject();
        UserInfo userInfo = userInfoRepository.findOne(userId);
        List<BookInfo> bookInfos = new ArrayList<>();
        if (userInfo == null) {
            throw new Exception("错误的用户信息！");
        }
        //先获得在读书籍
        List<StockInfo> stockInfos = stockInfoRepository.findStockByUserInfoId(String.valueOf(userId));
        if (stockInfos != null) {
            for (int i = 0; i < stockInfos.size(); i++) {
                BookInfo bookInfo = stockInfos.get(i).getBookInfo();
                bookInfos.add(bookInfo);
            }
            result.put("readingBook", bookInfos);
        }
        List<HistoryInfo> historyInfos = new ArrayList<>();
        historyInfos = historyInfoRepository.findHistoryByHistoryUser(userInfo);
        if (historyInfos != null) {
            bookInfos = new ArrayList<>();
            for (int i = 0; i < historyInfos.size(); i++) {
                BookInfo bookInfo = bookInfoRepository.findOne(historyInfos.get(i).getBookId());
                if (bookInfo != null) {
                    bookInfos.add(bookInfo);
                }
            }
            result.put("historyBook", bookInfos);
        }

        //再获得阅读历史
        return result;
    }

    @Override
    public void deleteSaveBook(Long userId, Long bookId) throws Exception {
        UserInfo userInfo = userInfoRepository.findOne(userId);
        if (userInfo == null) {
            throw new Exception("非法用户!");
        }
        BookInfo bookInfo = bookInfoRepository.findOne(bookId);
        if (bookInfo == null) {
            throw new Exception("没有对应书籍的库存，请联系管理员!");
        }
        SaveInfo saveInfo = saveInfoRepository.findSaveInfoByUserInfoAndSaveBookInfo(userInfo, bookInfo);
        if (saveInfo == null) {
            throw new Exception("没有对于该书的收藏记录!");
        }
        saveInfoRepository.delete(saveInfo);
    }

    @Override
    public void deleteOrderBook(Long userId, Long bookId) throws Exception {
        UserInfo userInfo = userInfoRepository.findOne(userId);
        if (userInfo == null) {
            throw new Exception("非法用户!");
        }
        BookInfo bookInfo = bookInfoRepository.findOne(bookId);
        if (bookInfo == null) {
            throw new Exception("没有对应书籍的库存，请联系管理员!");
        }
        OrderInfo orderInfo = orderInfoRepository.findOrderInfoByUserInfoOrderAndOrderBookInfo(userInfo, bookInfo);
        if (orderInfo == null) {
            throw new Exception("没有对于该书的预定信息!");
        }
        orderInfoRepository.delete(orderInfo);
    }

    @Override
    public UserInfo regist(UserInfo userInfo) throws Exception {
        List<UserInfo> userInfos = userInfoRepository.findUserByPhoneNo(userInfo.getPhoneNo());
        if (userInfos != null) {
            throw new Exception("该手机号已经注册过，无法重复注册！");
        }
        return userInfoRepository.save(userInfo);

    }
}
