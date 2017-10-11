package com.alienlab.my.module.book.web;

import com.alibaba.fastjson.JSON;
import com.alienlab.my.entity.BookInfo;
import com.alienlab.my.entity.StockInfo;
import com.alienlab.my.module.book.service.IBookManageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Msater Zg on 2017/10/2.
 */
@RestController
@RequestMapping(value = "/book")
public class BookManageController {

    @Autowired
    private IBookManageService iBookManageService;

    @PutMapping(value = "/insertBookInfo")
    @ApiOperation(value = "insertBookInfo", notes = "插入书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookInfo", value = "按钮数据", required = true, dataType = "STRING")
    })
    public ResponseEntity insertBookInfo(@RequestParam("bookInfo") String bookInfoData) {
        BookInfo bookInfo = JSON.parseObject(bookInfoData, BookInfo.class);
        BookInfo returnBookInfo = iBookManageService.insertBookInfo(bookInfo);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnBookInfo);
    }

    @GetMapping(value = "/getAllBook")
    @ApiOperation(value = "getAllBook", notes = "得到所有的书籍")
    @ApiImplicitParams({
    })
    public ResponseEntity getAllBook() {
        List<BookInfo> list = iBookManageService.getAllBook();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(list);

    }

    @GetMapping(value = "/updateBookInfo")
    @ApiOperation(value = "updateBookInfo", notes = "修改书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookInfo", value = "书籍信息", required = true, dataType = "STRING")
    })
    public ResponseEntity updateBookInfo(@RequestParam("bookInfo") String bookInfoData) {
        BookInfo bookInfo = JSON.parseObject(bookInfoData, BookInfo.class);
        BookInfo bookInfoReturn = iBookManageService.updateBookInfo(bookInfo);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bookInfoReturn);

    }

    @GetMapping(value = "/deleteBookInfo")
    @ApiOperation(value = "deleteBookInfo", notes = "修改书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn13", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteBookInfo(@RequestParam("isbn13") String isbn13) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setISBN13(isbn13);
        iBookManageService.deleteBookInfo(bookInfo);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("1");
    }

    @GetMapping(value = "/getAllStockByIsbn")
    @ApiOperation(value = "getAllStockByIsbn", notes = "获取书籍的打码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn13", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity getAllStockByIsbn(@RequestParam("isbn13") String isbn13) {
        List<StockInfo> list = iBookManageService.getAllStockByIsbn(isbn13);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(list);
    }


    @GetMapping(value = "/getRecommendBook")
    @ApiOperation(value = "getRecommendBook", notes = "获取本馆推荐书籍")
    public ResponseEntity getRecommendBook() {


        try{
            Page<BookInfo> recommendList = iBookManageService.getRecommednBook(new PageRequest(0,10,new Sort(Sort.Direction.DESC,"recommendIndex")));
            return ResponseEntity.ok().body(recommendList);
        }catch(Exception e){
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
