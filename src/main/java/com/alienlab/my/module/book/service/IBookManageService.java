package com.alienlab.my.module.book.service;


import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.BookInfo;
import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.SaveInfo;
import com.alienlab.my.entity.StockInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookManageService {

    // 书籍信息的接口
    public BookInfo insertBookInfo(BookInfo bookInfo, String stockInfo);

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

    public Page<BookInfo> searchBook(String type,String value1,String value2,String value3,String value4,Pageable pageable) throws Exception;
}
