package com.alienlab.my.module.book.service;


import com.alienlab.my.entity.BookInfo;

import java.util.List;

public interface IBookManageService {
    public BookInfo insertBookInfo(BookInfo bookInfo);

    public List<BookInfo> getAllBook();

    public BookInfo updateBookInfo(BookInfo bookInfo);

    public void deleteBookInfo(BookInfo bookInfo);
}
