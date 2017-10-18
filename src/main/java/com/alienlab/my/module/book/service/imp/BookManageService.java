package com.alienlab.my.module.book.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.BookInfo;
import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.SaveInfo;
import com.alienlab.my.entity.StockInfo;
import com.alienlab.my.module.book.service.IBookManageService;
import com.alienlab.my.repository.BookInfoRepository;
import com.alienlab.my.repository.OrderInfoRepository;
import com.alienlab.my.repository.SaveInfoRepository;
import com.alienlab.my.repository.StockInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookManageService implements IBookManageService {

    @Autowired
    BookInfoRepository bookInfoRepository;

    @Autowired
    StockInfoRepository stockInfoRepository;

    @Autowired
    SaveInfoRepository saveInfoRepository;
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


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
    public SaveInfo collectBook(String readerId, String bookId) throws Exception {
        SaveInfo saveInfo = saveInfoRepository.findSaveInfoByUserInfoIdAndLibraryId(readerId, bookId);
        if (saveInfo != null) {
            throw new Exception("您已收藏过该书籍，无法继续添加！");
        }
        saveInfo = new SaveInfo();
        saveInfo.setLibraryId(bookId);
        /*saveInfo.setReaderID(readerId);*/
        return saveInfoRepository.save(saveInfo);
    }

    @Override
    public OrderInfo orderBook(String readerId, String bookId, int limit) throws Exception {
        List<OrderInfo> orderInfos = orderInfoRepository.findOrderByUserInfoId(readerId);
        if (orderInfos != null) {
            if (orderInfos.size() > limit) {
                throw new Exception("您已超过可预定的最大本数！");
            }
        }
        OrderInfo orderInfo = orderInfoRepository.findOrderInfoByUserInfoIdAndLibraryId(readerId, bookId);
        if (orderInfo != null) {
            throw new Exception("您已预订过该书籍，请阅读后再重新预订！");
        }
        orderInfo = new OrderInfo();
        /*orderInfo.set(readerId);*/
        orderInfo.setLibraryId(bookId);
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

    public StringBuffer setBuffer(StringBuffer sql, JSONObject basicSearch, JSONObject ARSearch, JSONObject LLSearch) {
        if (basicSearch != null) {
            if (JsonIsNull(basicSearch, "title")) {
                sql.append(" AND  name = " + basicSearch.getString("title") + "  ");
            }
            if (JsonIsNull(basicSearch, "ISBN")) {
                sql.append(" AND isbn13= " + basicSearch.getString("ISBN") + " or isbn10 = " + basicSearch.getString("ISBN") + "    ");
            }
            if (JsonIsNull(basicSearch, "author")) {
                sql.append(" AND  author = " + basicSearch.getString("author") + "  ");
            }
            if (JsonIsNull(basicSearch, "publisher")) {
                sql.append(" AND  doc_type = " + basicSearch.getString("publisher") + "  ");
            }
            if (JsonIsNull(basicSearch, "bookType")) {
                sql.append(" AND  book_type = " + basicSearch.getString("bookType") + "  ");
            }
        }

        if (ARSearch != null) {
            if (JsonIsNull(ARSearch, "interestLevel")) {
                sql.append(" AND  il = " + ARSearch.getString("interestLevel") + "  ");
            }
            if (JsonIsNull(ARSearch, "ABLev")) {
                sql.append(" AND bl >= " + ARSearch.getString("ABLev") + "  ");
            }
            if (JsonIsNull(ARSearch, "ABLevT")) {
                sql.append(" AND  bl <= " + ARSearch.getString("ABLevT") + "  ");
            }
            if (JsonIsNull(ARSearch, "QN")) {
                sql.append(" AND  quiz_no = " + ARSearch.getString("QN") + "  ");
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
}
