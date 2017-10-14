package com.alienlab.my.module.book.service.imp;

import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.StockInfo;
import com.alienlab.my.module.book.service.OrderInfoService;
import com.alienlab.my.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhuliang on 2017/10/12.
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Override
    public List<OrderInfo> getAllreserveBook(String readId) throws Exception {
        List<OrderInfo> orderInfos = orderInfoRepository.findOrderByReaderID(readId);
        if(orderInfos==null){
            throw  new Exception("您还没有预定书籍哦!");
        }
        return orderInfoRepository.save(orderInfos);
    }
}
