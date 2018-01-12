package com.alienlab.my.module.book.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.*;
import com.alienlab.my.module.book.service.BookManageService;
import com.alienlab.my.repository.*;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookManageServiceImpl implements BookManageService {

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

    @Autowired
    BookNewsRepository bookNewsRepository;

    @Autowired
    SysImageRepository sysImageRepository;


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
        Page<BookInfo> recommendList = bookInfoRepository.findBookByRecommendIndexGreaterThan(0, pageable);
        if (recommendList == null) {
            throw new Exception("书籍为空或暂无推荐书籍，请联系管理员!");
        }
        return recommendList;
    }

    @Override
    public SaveInfo collectBook(Long readerId, Long bookId) throws Exception {
        BookInfo bookInfo = bookInfoRepository.findOne(bookId);
        if (bookInfo == null) {
            throw new Exception("没有该书库存，请联系管理员！");
        }
        UserInfo userInfo = userInfoRepository.findOne(readerId);
        if (userInfo == null) {
            throw new Exception("非法用户！");
        }
        SaveInfo saveInfo = saveInfoRepository.findSaveInfoByUserInfoAndSaveBookInfo(userInfo, bookInfo);
        if (saveInfo != null) {
            throw new Exception("您已收藏过该书籍，无法继续添加！");
        }
        List<SaveInfo> saveInfos = saveInfoRepository.findSaveByUserInfo(userInfo);
        if (saveInfo != null) {
            if (saveInfos.size() > 40) {
                throw new Exception("您收藏的书籍已经超过40本的收藏上限啦！");
            }
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
        if (bookInfo == null) {
            throw new Exception("库存中暂无该书籍信息，无法预定!");
        }
        int flag = 0;
        Set<StockInfo> stockInfos = bookInfo.getStockInfo();
        for (StockInfo stockInfo : stockInfos) {
            if (blankReadid.equals(stockInfo.getUserInfoId())) {
                flag++;
            }
        }
        if (flag == stockInfos.size()) {
            throw new Exception("暂无空余库存，无法预订该书籍!");
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
        sql.append("select id, isbn13 iSBN13,isbn10 iSBN10,name,author,pages,weight,series,doc_type docType,book_type bookType,pub_lisher pubLisher,count,stock,bl,arpoints,arrating,quiz_no quizNo,lexile_tag lexileTag,lexile_value lexileValue,audio,artag,lexile_combined lexileCombined,il FROM bookinfo  where 1=1 ");
        setBuffer(sql, basicSearch, ARSearch, LLSearch);
        int start = index * length;
        sql.append(" LIMIT " + start + " , " + length + " ");
        List searchResult = jdbcTemplate.queryForList(sql.toString());
        result.put("content", searchResult);
        return result;
    }

    @Override
    public Page<BookInfo> searchBook(String type, String value1, String value2, String value3, String value4, Pageable pageable) throws Exception {
        Page<BookInfo> bookInfos;
        if (isNull(value1) && type.equals("all")) {
            bookInfos = bookInfoRepository.findAll(pageable);
            return bookInfos;
        } else {
            if (type.equals("all")) {
                bookInfos = bookInfoRepository.findBookByISBN13OrISBN10OrNameContainingOrAuthorContaining(value1, value2, value3, value4, pageable);
                return bookInfos;
            } else if (type.equals("ar")) {
                if (isNull(value1)) bookInfos = bookInfoRepository.findBookByArtag(1, pageable);
                else
                    bookInfos = bookInfoRepository.findBookByISBN13OrISBN10OrNameContainingOrAuthorContainingAndArtag(value1, value2, value3, value4, 1, pageable);
                return bookInfos;
            } else if (type.equals("lexile")) {
                if (isNull(value1)) bookInfos = bookInfoRepository.findBookByLexileTag(1, pageable);
                else
                    bookInfos = bookInfoRepository.findBookByISBN13OrISBN10OrNameContainingOrAuthorContainingAndLexileTag(value1, value2, value3, value4, 1, pageable);
                return bookInfos;
            }
        }

        return null;

    }

    @Override
    public BookInfo findOneBook(Long bookid) throws Exception {
        BookInfo bookInfo = bookInfoRepository.findOne(bookid);
        if (bookInfo == null) {
            throw new Exception("没有对应书籍信息,请联系管理员！");
        }
        return bookInfo;
    }

    public StringBuffer setBuffer(StringBuffer sql, JSONObject basicSearch, JSONObject ARSearch, JSONObject LLSearch) {
        if (basicSearch != null) {
            if (JsonIsNull(basicSearch, "title")) {
                sql.append(" AND  name like  '%" + basicSearch.getString("title") + "%' ");
            }
            if (JsonIsNull(basicSearch, "ISBN")) {
                sql.append(" AND isbn13  like  '%" + basicSearch.getString("ISBN") + "%' or isbn10 like  '%"+basicSearch.getString("ISBN") + "%'    ");
            }
            if (JsonIsNull(basicSearch, "author")) {
                sql.append(" AND  author like  '%" + basicSearch.getString("author") + "%' ");
            }
            if (JsonIsNull(basicSearch, "publisher")) {
                sql.append(" AND  series =  '" + basicSearch.getString("publisher") + "'  ");
            }

            if (JsonIsNull(basicSearch, "docType")) {
                if(basicSearch.getString("docType").equals("Fiction")){
                    sql.append(" AND  doc_type =  '" + basicSearch.getString("docType") + "'   ");
                }else if(basicSearch.getString("docType").equals("Nonfiction")){
                    sql.append(" AND  doc_type =  '" + basicSearch.getString("docType") + "'   ");
                }

            }

            if (JsonIsNull(basicSearch, "bookType")) {
                sql.append(" AND  book_type like '%" + basicSearch.getString("bookType") + "%'  ");
            }


            if (JsonIsNull(basicSearch, "hasstock") && basicSearch.getBoolean("hasstock")) {
                sql.append(" AND  stock > 0  ");
            }
            if (JsonIsNull(basicSearch, "musflag") && basicSearch.getBoolean("musflag")) {
                sql.append(" AND  audio = 1  ");
            }
            if (JsonIsNull(basicSearch, "arflag") && basicSearch.getBoolean("arflag")) {
                sql.append(" AND  artag =1 ");
            }
            if (JsonIsNull(basicSearch, "lsflag") && basicSearch.getBoolean("lsflag")) {
                sql.append(" AND  lexile_tag = 1   ");
            }

            if (JsonIsNull(basicSearch, "start")) {
                sql.append(" AND age_start >= " + basicSearch.getFloat("start") + " ");
            }
            if (JsonIsNull(basicSearch, "ageEnd")) {
                sql.append(" AND  age_stop <= " + basicSearch.getFloat("ageEnd") + "  ");
            }
            if (JsonIsNull(basicSearch, "gradeStart")) {
                sql.append(" AND grade_start >= " + basicSearch.getFloat("gradeStart") + "  ");
            }
            if (JsonIsNull(basicSearch, "gradeEnd")) {
                sql.append(" AND  grade_stop <= " + basicSearch.getFloat("gradeEnd") + "  ");
            }
        }

        if (ARSearch != null) {
            if (JsonIsNull(ARSearch, "interestLevel")) {
                sql.append(" AND  il like '%" + ARSearch.getString("interestLevel") + "%'  ");
            }
            if (JsonIsNull(ARSearch, "ABLev")) {
                sql.append(" AND bl >= " + ARSearch.getFloat("ABLev") + "  ");
            }
            if (JsonIsNull(ARSearch, "ABLevT")) {
                sql.append(" AND  bl <= " + ARSearch.getFloat("ABLevT") + "  ");
            }
            if (JsonIsNull(ARSearch, "QN")) {
                sql.append(" AND  quiz_no = " + ARSearch.getFloat("QN") + "  ");
            }
            if (JsonIsNull(ARSearch, "ARP")) {
                sql.append(" AND  arpoints >= " + ARSearch.getFloat("ARP") + "  ");
            }


            if (JsonIsNull(ARSearch, "quizType")) {

                if (JsonIsNull(ARSearch, "quizType")) {
                    String skills = ARSearch.getString("quizType");
                    if(skills.equals("Recorded Voice")){
                        sql.append(" AND  rvquiz  = 1  ");
                    }

                    if(skills.equals("Vocabulary Practice")){
                        sql.append(" AND  vpquiz  = 1  ");
                    }

                    if(skills.equals("Literacy Skills")){
                        sql.append(" AND  lsquiz  = 1  ");
                    }

                }
            }

        }

        if (LLSearch != null) {
            if (JsonIsNull(LLSearch, "LLV")) {
                sql.append(" AND  lexile_value >= " + LLSearch.getFloat("LLV") + "  ");
            }
            if (JsonIsNull(LLSearch, "LLVT")) {
                sql.append(" AND lexile_value <= " + LLSearch.getFloat("LLVT") + "  ");
            }
            if (JsonIsNull(LLSearch, "sort")) {
                sql.append(" ORDER BY  " + LLSearch.getString("sort") + "    ");
            }
        }
        return sql;

    }

    public Boolean JsonIsNull(JSONObject jo, String item) {
        Boolean flag = true;
        flag = !isNull(jo.getString("" + item + ""));
        return flag;
    }

    public Boolean isNull(String value) {
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
                "a.user_info_id='88888888' , a.stock_tag = '1'  WHERE a.library_id = '" + libraryId + "' ";
        StockInfo stockInfo = stockInfoRepository.findStockByLibraryId(libraryId);
        UserInfo userInfo = userInfoRepository.findOne(Long.valueOf(stockInfo.getUserInfoId()));
        HistoryInfo historyInfo = new HistoryInfo();
        historyInfo.setHistoryStockInfo(stockInfo);
        historyInfo.setHistoryUser(userInfo);
        historyInfo.setBookId(stockInfo.getBookInfo().getId());
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
        BookInfo bookInfo = bookInfoRepository.findBookByISBN13OrISBN10(isbn, isbn);
        if (bookInfo == null) {
            bookInfo = new BookInfo();
            bookInfo.setId(Long.parseLong("0"));
        }
        return bookInfo;
    }

    @Override
    public JSONObject getBookStockInfo(String id) {
        String sql = "SELECT a.*,b.book_case,b.library_id FROM bookinfo a , stockinfo b " +
                "WHERE a.id = b.book_info_id AND a.id = '" + id + "'" +
                "AND b.user_info_id = '88888888' LIMIT 0,1 ";
        Map<String, Object> map = new HashMap<>();
        try {
            map = jdbcTemplate.queryForMap(sql);
        } catch (Exception e) {
            map.put("result", 0);
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
        return jsonObject;
    }

    @Override
    public JSONObject getBookCase(String libraryId) {
        String sql = " SELECT b.isbn10,b.isbn13,a.library_id,b.name,c.phone_no,a.user_info_id  " +
                " FROM stockinfo a ,bookinfo b, userinfo c   " +
                " WHERE a.book_info_id= b.id AND a.library_id = '" + libraryId + "'  " +
                " AND a.user_info_id = c.id";
        JSONObject jsonObject = new JSONObject();
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sql);
            jsonObject = (JSONObject) JSON.toJSON(map);
        } catch (Exception e) {
            // 输入有误
            jsonObject.put("result", 2);
        }
        return jsonObject;
    }

    @Override
    public JSONObject updateBookCase(String libraryId, String bookCase) {
        String sql = "UPDATE stockinfo a SET a.`book_case` = '" + bookCase + "' WHERE a.`library_id` = '" + libraryId + "'";
        int result = jdbcTemplate.update(sql);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

    @Override
    public BookNews insertUpdateBookNews(BookNews bookNews) {
        BookNews bookNewsReturn = bookNewsRepository.save(bookNews);
        return bookNewsReturn;
    }

    @Override
    public JSONObject deleteBookNews(String id) {
        String[] ids = id.split(",");
        for (int i = 0; i < ids.length; i++) {
            bookNewsRepository.delete(Long.parseLong(ids[i]));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", 1);
        return jsonObject;
    }

    @Override
    public JSONArray getAllBookNews(String type) {
        String sql = "SELECT * FROM booknews WHERE news_type = '" + type + "'";
        List list = jdbcTemplate.queryForList(sql);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        return jsonArray;
    }

    @Override
    public List getBorrowRanking(int index, int length) throws Exception {
        int start = index * length;
        int end = (index + 1) * length;
        String sql = "  select c.* ,count(*) cou from historyinfo a ,bookinfo c where a.book_id = c.id group by c.id order by cou DESC LIMIT " + start + "," + end + "  ";
        List<Map<String, Object>> list = new ArrayList();
        try {
            list = jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            throw new Exception("暂时没有收藏记录哦！");
        }
        for (int i = 0; i < list.size(); i++) {

            list.get(i).put("iSBN13", list.get(i).get("isbn13"));
            list.get(i).put("iSBN10", list.get(i).get("isbn10"));
        }


        return list;
    }

    @Override
    public Map findBorrowCount() throws Exception {
        String sqlToalBorrowCount = "select count(0) totalborrow from( select c.id from historyinfo a ,bookinfo c where a.book_id = c.id group by c.id) c";
        Map result = new HashMap();
        try {
            result = jdbcTemplate.queryForMap(sqlToalBorrowCount);
        } catch (Exception e) {
            throw new Exception("获取借阅历史书籍总数失败！");
        }
        return result;
    }

    @Override
    public List findBookSeries() throws Exception {
        String sql = "select bookinfo.series,count(*) sercount FROM bookinfo where series is NOT NULL AND  series !=' ' group by series  order by sercount desc";
        List result = jdbcTemplate.queryForList(sql);
        return result;
    }

    @Override
    public List findARBookSeries() throws Exception {
        String sql = "select arbooklist.serise series,count(*) sercount FROM arbooklist where serise is NOT NULL AND  serise !=' ' group by serise  order by sercount desc";
        List result = jdbcTemplate.queryForList(sql);
        return result;
    }

    @Override
    public SysImage saveImageUrl(SysImage sysImage) {
        sysImage.setImageTime(new Date());
        SysImage sysImageReturn = sysImageRepository.save(sysImage);
        return sysImageReturn;
    }

    @Override
    public List<SysImage> listImageInformation() {
        return sysImageRepository.findAll();
    }

    @Override
    public JSONObject removeImageInformation(String id) {

        JSONObject jsonObject = new JSONObject();
        try {
            sysImageRepository.delete(Long.parseLong(id));
            jsonObject.put("result", 1);
        } catch (Exception e) {
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }

    @Override
    public JSONObject listArBookSearch(String searchData) {
        JSONObject jsonObject = JSON.parseObject(searchData);
        StringBuffer sql = new StringBuffer();

        sql.append("  select id,quiz_no quizNo,name,author,doc_type docType ,serise,ar_points arpoints,pub_lisher pubLisher,bl,il FROM arbooklist  where 1=1");
        setBuffer(sql,jsonObject);
        List searchResult = jdbcTemplate.queryForList(sql.toString());
        JSONObject result = new JSONObject();
        result.put("content", searchResult);
        return result;
    }


    public StringBuffer setBuffer(StringBuffer sql, JSONObject ARSearch) {

        if (ARSearch != null) {
            if (JsonIsNull(ARSearch, "title")) {
                sql.append(" AND  name like  '%" + ARSearch.getString("title") + "%' ");
            }

            if (JsonIsNull(ARSearch, "publisher")) {
                sql.append(" AND  pub_lisher like  '%" + ARSearch.getString("publisher") + "%' ");
            }

            if (JsonIsNull(ARSearch, "author")) {
                sql.append(" AND  author like  '%" + ARSearch.getString("author") + "%' ");
            }
            if (JsonIsNull(ARSearch, "interestLevel")) {
                sql.append(" AND  il like '%" + ARSearch.getString("interestLevel") + "%'  ");
            }
            if (JsonIsNull(ARSearch, "award")) {
                sql.append(" AND  awards like '%" + ARSearch.getString("award") + "%'  ");
            }
            if (JsonIsNull(ARSearch, "ABLev")) {
                sql.append(" AND bl >= " + ARSearch.getFloat("ABLev") + "  ");
            }


            if (JsonIsNull(ARSearch, "series")) {
                sql.append(" AND  serise =  '" + ARSearch.getString("series") + "'  ");
            }
            if (JsonIsNull(ARSearch, "doctype")) {
                if(ARSearch.getString("doctype").equals("Fiction")){
                    sql.append(" AND  doc_type =  'F'  ");
                }else if(ARSearch.getString("doctype").equals("Nonfiction")){
                    sql.append(" AND  doc_type =  'N'  ");
                }

            }
            if (JsonIsNull(ARSearch, "ABLevT")) {
                sql.append(" AND  bl <= " + ARSearch.getFloat("ABLevT") + "  ");
            }
            if (JsonIsNull(ARSearch, "QN")) {
                sql.append(" AND  quiz_no = " + ARSearch.getFloat("QN") + "  ");
            }
            if (JsonIsNull(ARSearch, "skills")) {
                String skills = ARSearch.getString("skills");
                if(skills.equals("音频")){
                    sql.append(" AND  rv_quiz  = 1  ");
                }

                if(skills.equals("词汇练习")){
                    sql.append(" AND  vp_quiz  = 1  ");
                }

                if(skills.equals("文学题")){
                    sql.append(" AND  ls_quiz  = 1  ");
                }

            }

            sql.append(" LIMIT 0, 20 ");
        }

        return sql;

    }

    @Override
    public JSONObject listLexBookSearch(String searchData) {
        JSONObject jsonObject = JSON.parseObject(searchData);

        StringBuffer sql = new StringBuffer();

        sql.append("select id, name,author,doc_type docType,lexile_combined lexileCombined,series,pages FROM lexilebooklist  where 1=1 ");
        setLexBuffer(sql,jsonObject);
        List searchResult = jdbcTemplate.queryForList(sql.toString());
        JSONObject result = new JSONObject();
        result.put("content", searchResult);
        return result;
    }


    public StringBuffer setLexBuffer(StringBuffer sql, JSONObject LLSearch) {
        if (LLSearch != null) {
            if (JsonIsNull(LLSearch, "title")) {
                sql.append(" AND  name like  '%" + LLSearch.getString("title") + "%' ");
            }
            if (JsonIsNull(LLSearch, "ISBN")) {
                sql.append(" AND isbn13  like  '%" + LLSearch.getString("ISBN") + "%' or isbn10 like  '%" + LLSearch.getString("ISBN") + "%'    ");
            }
            if (JsonIsNull(LLSearch, "author")) {
                sql.append(" AND  author like  '%" + LLSearch.getString("author") + "%' ");
            }
            if (JsonIsNull(LLSearch, "publisher")) {
                sql.append(" AND  series =  '" + LLSearch.getString("publisher") + "'  ");
            }


            if (JsonIsNull(LLSearch, "docType")) {
                if(LLSearch.getString("docType").equals("Fiction")){
                    sql.append(" AND  doc_type =  'Fiction'  ");
                }else if(LLSearch.getString("docType").equals("Nonfiction")){
                    sql.append(" AND  doc_type =  'Nonfiction'  ");
                }

            }
            if (JsonIsNull(LLSearch, "award")) {
                sql.append(" AND  awards like '%" + LLSearch.getString("award") + "%'  ");
            }
            if (JsonIsNull(LLSearch, "LLV")) {
                sql.append(" AND  lexile_value >= " + LLSearch.getFloat("LLV") + "  ");
            }
            if (JsonIsNull(LLSearch, "LLVT")) {
                sql.append(" AND lexile_value <= " + LLSearch.getFloat("LLVT") + "  ");
            }
        }

        return sql;

    }
}
