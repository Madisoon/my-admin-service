package com.alienlab.my.module.book.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.BookInfo;
import com.alienlab.my.entity.StockInfo;
import com.alienlab.my.entity.UserInfo;
import com.alienlab.my.module.book.service.IBookManageService;
import com.alienlab.my.module.book.service.UserManageService;
import com.alienlab.my.module.book.service.imp.BookManageService;
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
import java.util.Map;

/**
 * Created by Msater Zg on 2017/10/2.
 */
@RestController
@RequestMapping(value = "/book")
public class BookManageController {

    @Autowired
    private IBookManageService iBookManageService;

    @Autowired
    UserManageService userManageService;

    @Autowired
    BookManageService bookManageService;

    @PutMapping(value = "/insertBookInfo")
    @ApiOperation(value = "insertBookInfo", notes = "插入书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookInfo", value = "书籍信息", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "stockInfo", value = "库存信息", required = true, dataType = "STRING")
    })
    public ResponseEntity insertBookInfo(@RequestParam("bookInfo") String bookInfoData,
                                         @RequestParam("stockInfo") String stockInfo) {
        BookInfo bookInfo = JSON.parseObject(bookInfoData, BookInfo.class);
        BookInfo returnBookInfo = iBookManageService.insertBookInfo(bookInfo, stockInfo);
        return ResponseEntity.ok().body(returnBookInfo);
    }

    @GetMapping(value = "/getAllBook")
    @ApiOperation(value = "getAllBook", notes = "得到所有的书籍")
    @ApiImplicitParams({
    })
    public ResponseEntity getAllBook() {
        List<BookInfo> list = iBookManageService.getAllBook();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/updateBookInfo")
    @ApiOperation(value = "updateBookInfo", notes = "修改书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookInfo", value = "书籍信息", required = true, dataType = "STRING")
    })
    public ResponseEntity updateBookInfo(@RequestParam("bookInfo") String bookInfoData) {
        BookInfo bookInfo = JSON.parseObject(bookInfoData, BookInfo.class);
        BookInfo bookInfoReturn = iBookManageService.updateBookInfo(bookInfo);
        return ResponseEntity.ok().body(bookInfoReturn);

    }

    @GetMapping(value = "/deleteBookInfo")
    @ApiOperation(value = "deleteBookInfo", notes = "修改书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn13", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteBookInfo(@RequestParam("isbn13") String isbn13) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setiSBN13(isbn13);
        iBookManageService.deleteBookInfo(bookInfo);
        return ResponseEntity.ok().body("1");
    }

    @GetMapping(value = "/getAllBookByIsbn")
    @ApiOperation(value = "getAllBookByIsbn", notes = "获取书籍的打码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn13", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity getAllBookByIsbn(@RequestParam("isbn13") String isbn13) {
        BookInfo bookInfo = iBookManageService.findBookByISBN13(isbn13);
        return ResponseEntity.ok().body(bookInfo);
    }

    @PostMapping(value = "/returnBook")
    @ApiOperation(value = "returnBook", notes = "会员还书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity returnBook(@RequestParam("isbn") String isbn) {
        JSONObject jsonObject = iBookManageService.updateStock(isbn);
        return ResponseEntity.ok().body(jsonObject);
    }


    @GetMapping(value = "/getRecommendBook")
    @ApiOperation(value = "getRecommendBook", notes = "获取本馆推荐书籍")
    public ResponseEntity getRecommendBook() {
        try {
            Page<BookInfo> recommendList = iBookManageService.getRecommednBook(new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "recommendIndex")));
            return ResponseEntity.ok().body(recommendList);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping("/advancedSearch")
    @ApiOperation(value = "advancedSearch", notes = "高级搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "basicSearch", value = "基础搜索信息json", dataType = "string"),
            @ApiImplicitParam(name = "arSearch", value = "AR信息搜索", dataType = "string"),
            @ApiImplicitParam(name = "LLsearch", value = "蓝思信息搜索", dataType = "string"),
            @ApiImplicitParam(name = "index", value = "当前页码", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "每页长度", dataType = "int"),
    })
    public ResponseEntity advancedSearch(@RequestParam String basicSearch, @RequestParam String arSearch, @RequestParam String LLsearch, @RequestParam int index, @RequestParam int length) {
        try {
            JSONObject result = bookManageService.advancedSearch(JSONObject.parseObject(basicSearch), JSONObject.parseObject(arSearch), JSONObject.parseObject(LLsearch), index, length);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }



    @GetMapping("/searchBook")
    @ApiOperation(value = "searchBook", notes = "搜索书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "搜索类型", dataType = "string"),
            @ApiImplicitParam(name = "value", value = "搜索信息", dataType = "string"),
            @ApiImplicitParam(name = "index", value = "当前页码", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "每页长度", dataType = "int"),
    })
    public ResponseEntity searchBook(@RequestParam String type,@RequestParam String value, @RequestParam int index, @RequestParam int length) {

        try {
            Page<BookInfo> page = bookManageService.searchBook(type,value,value,value,value,new PageRequest(index,length));
            return ResponseEntity.ok().body(page);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/userLogin")
    @ApiOperation(value = "userLogin", notes = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userphone", value = "用户手机号码（用户名）", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "用户密码", dataType = "string"),
    })
    public ResponseEntity postUserData(@RequestParam String userphone,@RequestParam String password) {
        try {
            JSONObject result = new JSONObject();
            UserInfo userInfo= userManageService.userLogin(userphone,password);
            JSONObject book = userManageService.getuserWatchBook(userInfo.getId());
            result.put("userInfo",userInfo);
            result.put("bookInfo",book);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


    @PostMapping(value = "/getUserYzNumber")
    @ApiOperation(value = "getUserYzNumber", notes = "获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", value = "用户手机号", dataType = "string"),
            @ApiImplicitParam(name = "userCode", value = "用户验证码", dataType = "string")
    })
    public ResponseEntity getUserYzNumber(@RequestParam String userPhone,
                                          @RequestParam String userCode) {
        String message = userManageService.getUserYzNumber(userPhone, userCode);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping(value = "/regist")
    @ApiOperation(value = "regist", notes = "注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "UserInfo", value = "用户信息", dataType = "UserInfo"),
    })
    public ResponseEntity regist(@RequestBody UserInfo userInfo) {
        try {
            UserInfo userInfo1 =  userManageService.regist(userInfo);
            return ResponseEntity.ok().body(userInfo1);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @GetMapping(value = "/getBookDetailInfo")
    @ApiOperation(value = "getBookDetailInfo", notes = "获取单本书籍的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookid", value = "书籍id", dataType = "Long"),
    })
    public ResponseEntity getBookDetailInfo(@RequestParam Long bookid) {
        try {
            BookInfo bookInfo = bookManageService.findOneBook(bookid);
            return ResponseEntity.ok().body(bookInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }


}




