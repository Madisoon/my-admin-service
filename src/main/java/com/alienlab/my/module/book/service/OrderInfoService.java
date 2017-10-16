package com.alienlab.my.module.book.service;

import com.alienlab.my.entity.OrderInfo;


import java.util.List;

/**
 * Created by zhuliang on 2017/10/12.
 */
public interface OrderInfoService {
    public List<OrderInfo> getAllreserveBook(String readId) throws  Exception;

}
