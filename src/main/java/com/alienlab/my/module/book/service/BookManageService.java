package com.alienlab.my.module.book.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookManageService {

    // 书籍信息的接口
    public BookInfo insertBookInfo(BookInfo bookInfo, String stockInfo);

    public JSONObject getBookStockInfo(String id);

    public List<BookInfo> getAllBook();

    public BookInfo updateBookInfo(BookInfo bookInfo);

    public void deleteBookInfo(BookInfo bookInfo);

    public JSONObject updateStock(String libraryId);

    public BookInfo findBookByISBN13(String isbn);

    // 库存信息的接口

    public StockInfo insertStockInfo(StockInfo stockInfo);

    public List<StockInfo> getAllStockByIsbn(String isbn);

    public void deleteStockInfo(StockInfo stockInfo);

    public Page<BookInfo> getRecommednBook(Pageable pageable) throws Exception;

    public SaveInfo collectBook(Long readerId, Long bookId) throws Exception;

    public OrderInfo orderBook(Long readerId, Long bookId, int limit) throws Exception;

    public JSONObject advancedSearch(JSONObject basicSearch, JSONObject ARSearch, JSONObject LLSearch, int index, int length) throws Exception;

    public Page<BookInfo> searchBook(String type, String value1, String value2, String value3, String value4, Pageable pageable) throws Exception;

    public BookInfo findOneBook(Long bookid) throws Exception;

    // 网站新闻的接口

    /**
     * 用来插入或者改变新闻的数据
     *
     * @param bookNews
     * @return
     */
    public BookNews insertUpdateBookNews(BookNews bookNews);

    public BookNews deleteBookNews(String id);

    public JSONArray getAllBookNews(String type);
}
