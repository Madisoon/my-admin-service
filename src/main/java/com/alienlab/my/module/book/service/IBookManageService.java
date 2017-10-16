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
    public BookInfo insertBookInfo(BookInfo bookInfo);

    public List<BookInfo> getAllBook();

    public BookInfo updateBookInfo(BookInfo bookInfo);

    public void deleteBookInfo(BookInfo bookInfo);

    // 库存信息的接口

    public StockInfo insertStockInfo(StockInfo stockInfo);

    public List<StockInfo> getAllStockByIsbn(String isbn);

    public void deleteStockInfo(StockInfo stockInfo);

    public Page<BookInfo> getRecommednBook(Pageable pageable) throws Exception;

    public SaveInfo collectBook(String readerId, String bookId) throws Exception;

    public OrderInfo orderBook(String readerId, String bookId ,int limit) throws Exception;

    public JSONObject advancedSearch(JSONObject basicSearch,JSONObject ARSearch,JSONObject LLSearch,int index,int length) throws Exception;

}
