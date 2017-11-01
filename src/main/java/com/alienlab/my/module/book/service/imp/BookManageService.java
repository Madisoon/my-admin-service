package com.alienlab.my.module.book.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.*;
import com.alienlab.my.module.book.service.IBookManageService;
import com.alienlab.my.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookManageService implements IBookManageService {

    @Value("${system.blank-reader-id}")
    private String blankReadid;

    @Autowired
    BookInfoRepository bookInfoRepository;

    @Autowired
    StockInfoRepository stockInfoRepository;

    @Autowired
    SaveInfoRepository saveInfoRepository;
    @Autowired
    OrderInfoRepository orderInfoRepository;
    @Autowired
    HistoryInfoRepository historyInfoRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserInfoRepository userInfoRepository;


    @Override
    public BookInfo insertBookInfo(BookInfo bookInfo, String stockInfoId) {
        String[] stockInfoIds = stockInfoId.split(",");
        Set<StockInfo> set = new HashSet<>();
        BookInfo bookInfoReturn = this.bookInfoRepository.save(bookInfo);
        for (int i = 0, bookInfoLen = stockInfoIds.length; i < bookInfoLen; i++) {
            StockInfo stockInfo = new StockInfo();
            stockInfo = stockInfoRepository.findStockByLibraryId(stockInfoIds[i]);
            // 如果数据库没有这个对象
            if (stockInfo == null) {
                StockInfo stockInfoInsert = new StockInfo();
                stockInfoInsert.setBookInfo(bookInfoReturn);
                stockInfoInsert.setLibraryId(stockInfoIds[i]);
                stockInfoInsert.setLastTime(new Date());
                stockInfoInsert.setRRanking(1);
                stockInfoInsert.setStockTag(1);
                stockInfoRepository.save(stockInfoInsert);
            }
        }
        return bookInfoReturn;
    }

    @Override
    public List<BookInfo> getAllBook() {
        return this.bookInfoRepository.findAll();
    }

    @Override
    public BookInfo updateBookInfo(BookInfo bookInfo) {
        return this.bookInfoRepository.save(bookInfo);
    }

    @Override
    public void deleteBookInfo(BookInfo bookInfo) {
        this.bookInfoRepository.delete(bookInfo);
    }

    @Override
    public StockInfo insertStockInfo(StockInfo stockInfo) {
        return this.stockInfoRepository.save(stockInfo);
    }

    @Override
    public List<StockInfo> getAllStockByIsbn(String isbn) {
        String selectSql = "select * from ?1";
      /*  EntityManager entityManager;
        entityManager.createQuery(selectSql, StockInfo.class).getResultList();*/
        return null;
    }

    @Override
    public void deleteStockInfo(StockInfo stockInfo) {
        this.stockInfoRepository.delete(stockInfo);
    }

    @Override
    public Page<BookInfo> getRecommednBook(Pageable pageable) throws Exception {
        Page<BookInfo> recommendList = bookInfoRepository.findAll(pageable);
        if (recommendList == null) {
            throw new Exception("书籍为空或暂无推荐书籍，请联系管理员!");
        }
        return recommendList;
    }

    @Override
    public SaveInfo collectBook(Long readerId, Long bookId) throws Exception {
        BookInfo bookInfo = bookInfoRepository.findOne(bookId);
        if(bookInfo == null){
            throw new Exception("没有该书库存，请联系管理员！");
        }
        UserInfo userInfo = userInfoRepository.findOne(readerId);
        if(userInfo == null) throw new Exception("非法用户！");
        SaveInfo saveInfo = saveInfoRepository.findSaveInfoByUserInfoAndSaveBookInfo(userInfo, bookInfo);
        if (saveInfo != null) {
            throw new Exception("您已收藏过该书籍，无法继续添加！");
        }
        saveInfo = new SaveInfo();
        saveInfo.setSaveBookInfo(bookInfo);
        saveInfo.setUserInfo(userInfo);
        return saveInfoRepository.save(saveInfo);
    }

    @Override
    public OrderInfo orderBook(Long readerId, Long bookId, int limit) throws Exception {
        UserInfo userInfo = userInfoRepository.findOne(readerId);
        List<OrderInfo> orderInfos = orderInfoRepository.findOrderByUserInfoOrder(userInfo);
        if (orderInfos != null) {
            if (orderInfos.size() > limit) {
                throw new Exception("您已超过可预定的最大本数！");
            }
        }
        BookInfo bookInfo = bookInfoRepository.findOne(bookId);
        if(bookInfo == null){
            throw  new Exception("库存中暂无该书籍信息，无法预定!");
        }
        int flag = 0;
        Set<StockInfo> stockInfos = bookInfo.getStockInfo();
        for(StockInfo stockInfo :stockInfos){
            if(blankReadid.equals(stockInfo.getUserInfoId()))flag++;
        }
        if(flag==stockInfos.size()){
            throw  new Exception("暂无空余库存，无法预订该书籍!");
        }
        OrderInfo orderInfo = orderInfoRepository.findOrderInfoByUserInfoOrderAndOrderBookInfo(userInfo, bookInfo);
        if (orderInfo != null) {
            throw new Exception("您已预订过该书籍，请阅读后再重新预订！");
        }
        orderInfo = new OrderInfo();
        orderInfo.setUserInfo(userInfo);
        orderInfo.setOrderTime(new Date());
        orderInfo.setOrderBookInfo(bookInfo);
        return orderInfoRepository.save(orderInfo);
    }

    @Override
    public JSONObject advancedSearch(JSONObject basicSearch, JSONObject ARSearch, JSONObject LLSearch, int index, int length) throws Exception {
        JSONObject result = new JSONObject();
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) bookCount FROM bookinfo  where 1=1 ");
        setBuffer(sql, basicSearch, ARSearch, LLSearch);
        Map CountResult = jdbcTemplate.queryForMap(sql.toString());
        String total = CountResult.get("bookCount").toString();
        result.put("total", total);
        if ("0".equals(total)) {
            return result;
        }
        sql = new StringBuffer();
        sql.append("select *  FROM bookinfo  where 1=1 ");
        setBuffer(sql, basicSearch, ARSearch, LLSearch);
        int start = index * length;
        sql.append(" LIMIT " + start + " , " + length + " ");
        List searchResult = jdbcTemplate.queryForList(sql.toString());
        result.put("content", searchResult);
        return result;
    }

    @Override
    public Page<BookInfo> searchBook(String type,String value1, String value2, String value3, String value4, Pageable pageable) throws Exception {
        Page<BookInfo> bookInfos;
        if (isNull(value1)) {
            bookInfos = bookInfoRepository.findAll(pageable);
            return bookInfos;
        }else{
            if(type.equals("all")){ bookInfos = bookInfoRepository.findBookByISBN13OrISBN10OrNameOrAuthor(value1,value2,value3,value4,pageable);  return bookInfos;}
            else if(type.equals("ar")){ bookInfos = bookInfoRepository.findBookByISBN13OrISBN10OrNameOrAuthorAndArtag(value1,value2,value3,value4,1,pageable);  return bookInfos;}
            else if(type.equals("lexile")){ bookInfos = bookInfoRepository.findBookByISBN13OrISBN10OrNameOrAuthorAndLexileTag(value1,value2,value3,value4,1,pageable);  return bookInfos;}
        }

        return null;

    }

    @Override
    public BookInfo findOneBook(Long bookid) throws Exception {
        BookInfo bookInfo = bookInfoRepository.findOne(bookid);
        if (bookInfo==null){
            throw  new Exception("没有对应书籍信息,请联系管理员！");
        }
        return bookInfo;
    }

    public StringBuffer setBuffer(StringBuffer sql, JSONObject basicSearch, JSONObject ARSearch, JSONObject LLSearch) {
        if (basicSearch != null) {
            if (JsonIsNull(basicSearch, "title")) {
                sql.append(" AND  name like  '% "+ basicSearch.getString("title") + " %' ");
            }
            if (JsonIsNull(basicSearch, "ISBN")) {
                sql.append(" AND isbn13  like  '%" + basicSearch.getString("ISBN") + " %' or isbn10 like  '% " + basicSearch.getString("ISBN") + "%'    ");
            }
            if (JsonIsNull(basicSearch, "author")) {
                sql.append(" AND  author like  '% " + basicSearch.getString("author") + "%' ");
            }
            if (JsonIsNull(basicSearch, "publisher")) {
                sql.append(" AND  doc_type like '%  " + basicSearch.getString("publisher") + "%'  ");
            }
            if (JsonIsNull(basicSearch, "bookType")) {
                sql.append(" AND  book_type like '%  " + basicSearch.getString("bookType") + "%'  ");
            }
        }

        if (ARSearch != null) {
            if (JsonIsNull(ARSearch, "interestLevel")) {
                sql.append(" AND  il like '% " + ARSearch.getString("interestLevel") + "%'  ");
            }
            if (JsonIsNull(ARSearch, "ABLev")) {
                sql.append(" AND bl >= " + ARSearch.getString("ABLev") + "  ");
            }
            if (JsonIsNull(ARSearch, "ABLevT")) {
                sql.append(" AND  bl <= " + ARSearch.getString("ABLevT") + "  ");
            }
            if (JsonIsNull(ARSearch, "QN")) {
                sql.append(" AND  quiz_no like '%" + ARSearch.getString("QN") + " %' ");
            }
            if (JsonIsNull(ARSearch, "ARP")) {
                sql.append(" AND  arpoints >= " + ARSearch.getString("ARP") + "  ");
            }
            if (JsonIsNull(ARSearch, "ARPT")) {
                sql.append(" AND  arpoints <= " + ARSearch.getString("ARPT") + "  ");
            }
        }

        if (LLSearch != null) {
            if (JsonIsNull(LLSearch, "LLV")) {
                sql.append(" AND  lexile_value >= " + LLSearch.getString("LLV") + "  ");
            }
            if (JsonIsNull(LLSearch, "LLVT")) {
                sql.append(" AND lexile_value <= " + LLSearch.getString("LLVT") + "  ");
            }
            if (JsonIsNull(LLSearch, "sort")) {
                sql.append(" ORDER BY  " + LLSearch.getString("sort") + " DESC   ");
            }
        }
        return sql;

    }

    public Boolean JsonIsNull(JSONObject jo, String item) {
        Boolean flag = true;
        flag = !isNull(jo.getString("" + item + ""));
        return flag;
    }

    public Boolean isNull(String value){
        Boolean flag = false;
        if (value == null || "".equals(value) || "null".equals(value)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public JSONObject updateStock(String libraryId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormData = simpleDateFormat.format(new Date());
        String sqlUpdate = "UPDATE stockinfo a SET a.last_time='" + dateFormData + "' , " +
                "a.user_info_id='88888888' WHERE a.library_id = '" + libraryId + "' ";
        StockInfo stockInfo = stockInfoRepository.findOne(Long.valueOf(libraryId));
        UserInfo userInfo = userInfoRepository.findOne(Long.valueOf(stockInfo.getUserInfoId()));
        HistoryInfo historyInfo = new HistoryInfo();

        historyInfo.setHistoryStockInfo(stockInfo);
        historyInfo.setHistoryUser(userInfo);
        historyInfo.setBorrowTime(stockInfo.getLastTime());
        historyInfo.setReturnTime(new Date());
        historyInfo.setRRanking("1");
        historyInfoRepository.save(historyInfo);
        int result = jdbcTemplate.update(sqlUpdate);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

    @Override
    public BookInfo findBookByISBN13(String isbn) {
        return bookInfoRepository.findBookByISBN13(isbn);
    }
}
