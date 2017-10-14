package com.alienlab.my.module.book.service.imp;

import com.alienlab.my.entity.BookInfo;
import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.SaveInfo;
import com.alienlab.my.entity.StockInfo;
import com.alienlab.my.module.book.service.IBookManageService;
import com.alienlab.my.repository.BookInfoRepository;
import com.alienlab.my.repository.OrderInfoRepository;
import com.alienlab.my.repository.SaveInfoRepository;
import com.alienlab.my.repository.StockInfoRepository;
import org.hibernate.engine.spi.EntityEntryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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


    @Override
    public BookInfo insertBookInfo(BookInfo bookInfo, String stockInfoId) {
        String[] stockInfoIds = stockInfoId.split(",");
        System.out.println(stockInfoId);
        Set<StockInfo> set = new HashSet<>();
        BookInfo bookInfoReturn = this.bookInfoRepository.save(bookInfo);
        for (int i = 0, bookInfoLen = stockInfoIds.length; i < bookInfoLen; i++) {
            StockInfo stockInfo = new StockInfo();
            stockInfo.setId(Long.parseLong(stockInfoIds[i]));
            stockInfo.setBookInfo(bookInfoReturn);
            /*stockInfoRepository.save(stockInfo);*/
            set.add(stockInfo);
        }
        bookInfo.setStockInfo(set);
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
    public BookInfo getAllBookByIsbn(String isbn) {
        BookInfo bookInfo = bookInfoRepository.findBookByISBN13(isbn);
        return bookInfo;
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
        SaveInfo saveInfo = saveInfoRepository.findSaveInfoByReaderIDAndLibraryID(readerId,bookId);
        if(saveInfo!=null){
            throw  new Exception("您已收藏过该书籍，无法继续添加！");
        }
        saveInfo = new SaveInfo();
        saveInfo.setLibraryID(bookId);
        saveInfo.setReaderID(readerId);
        return saveInfoRepository.save(saveInfo);
    }

    @Override
    public OrderInfo orderBook(String readerId, String bookId,int limit) throws Exception {
        List<OrderInfo> orderInfos = orderInfoRepository.findOrderByReaderID(readerId);
        if(orderInfos!=null){
            if(orderInfos.size()>limit){
                throw new Exception("您已超过可预定的最大本数！");
            }
        }
        OrderInfo orderInfo = orderInfoRepository.findOrderInfoByReaderIDAndLibraryID(readerId,bookId);
        if(orderInfo!=null){
            throw new Exception("您已预订过该书籍，请阅读后再重新预订！");
        }
        orderInfo = new OrderInfo();
        orderInfo.setReaderID(readerId);
        orderInfo.setLibraryID(bookId);
        return orderInfoRepository.save(orderInfo);
    }
}
