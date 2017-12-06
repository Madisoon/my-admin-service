package com.alienlab.my.module.book.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.BookInfo;
import com.alienlab.my.entity.BookNews;
import com.alienlab.my.entity.UserInfo;
import com.alienlab.my.module.book.service.BookManageService;
import com.alienlab.my.module.book.service.UserManageService;
import com.alienlab.my.module.book.service.WebConfigurationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Msater Zg
 */
@RestController
@RequestMapping(value = "/book")
public class BookManageController {

    @Autowired
    private BookManageService iBookManageService;

    @Autowired
    UserManageService userManageService;

    @Autowired
    WebConfigurationService webConfigurationService;

    @Autowired
    com.alienlab.my.module.book.service.imp.BookManageServiceImpl bookManageService;

    @PutMapping(value = "/insertBookInfo")
    @ApiOperation(value = "insertBookInfo", notes = "插入书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookInfo", value = "书籍信息", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "stockInfo", value = "库存信息", required = true, dataType = "STRING")
    })
    public ResponseEntity insertBookInfo(@RequestParam("bookInfo") String bookInfoData,
                                         @RequestParam("stockInfo") String stockInfo) {

        try {
            BookInfo bookInfo = JSON.parseObject(bookInfoData, BookInfo.class);
            BookInfo returnBookInfo = iBookManageService.insertBookInfo(bookInfo, stockInfo);
            return ResponseEntity.ok().body(returnBookInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getAllBook")
    @ApiOperation(value = "getAllBook", notes = "得到所有的书籍")
    @ApiImplicitParams({
    })
    public ResponseEntity getAllBook() {
        try {
            List<BookInfo> list = iBookManageService.getAllBook();
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @GetMapping(value = "/updateBookInfo")
    @ApiOperation(value = "updateBookInfo", notes = "修改书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookInfo", value = "书籍信息", required = true, dataType = "STRING")
    })
    public ResponseEntity updateBookInfo(@RequestParam("bookInfo") String bookInfoData) {
        try {
            BookInfo bookInfo = JSON.parseObject(bookInfoData, BookInfo.class);
            BookInfo bookInfoReturn = iBookManageService.updateBookInfo(bookInfo);
            return ResponseEntity.ok().body(bookInfoReturn);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @GetMapping(value = "/deleteBookInfo")
    @ApiOperation(value = "deleteBookInfo", notes = "修改书籍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn13", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteBookInfo(@RequestParam("isbn13") String isbn13) {
        try {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setiSBN13(isbn13);
            iBookManageService.deleteBookInfo(bookInfo);
            return ResponseEntity.ok().body("1");
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @GetMapping(value = "/getAllBookByIsbn")
    @ApiOperation(value = "getAllBookByIsbn", notes = "获取书籍的打码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn13", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity getAllBookByIsbn(@RequestParam("isbn13") String isbn13) {
        try {
            BookInfo bookInfo = iBookManageService.findBookByISBN13(isbn13);
            return ResponseEntity.ok().body(bookInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/returnBook")
    @ApiOperation(value = "returnBook", notes = "会员还书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity returnBook(@RequestParam("isbn") String isbn) {
        try {
            JSONObject jsonObject = iBookManageService.updateStock(isbn);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @GetMapping(value = "/getRecommendBook")
    @ApiOperation(value = "getRecommendBook", notes = "获取本馆推荐书籍")
    public ResponseEntity getRecommendBook() {
        try {
            JSONObject result = new JSONObject();
            Page<BookInfo> recommendList = iBookManageService.getRecommednBook(new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "recommendIndex")));
            List list = bookManageService.getBorrowRanking();
            result.put("recommendList",recommendList);
            result.put("borrowList",list);
            return ResponseEntity.ok().body(result);
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
    public ResponseEntity searchBook(@RequestParam String type, @RequestParam String value, @RequestParam int index, @RequestParam int length) {

        try {
            Page<BookInfo> page = bookManageService.searchBook(type, value, value, value, value, new PageRequest(index, length));
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
    public ResponseEntity postUserData(@RequestParam String userphone, @RequestParam String password) {
        try {
            JSONObject result = new JSONObject();
            UserInfo userInfo = userManageService.userLogin(userphone, password);
            JSONObject book = userManageService.getuserWatchBook(userInfo.getId());
            result.put("userInfo", userInfo);
            result.put("bookInfo", book);
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
            UserInfo userInfo1 = userManageService.regist(userInfo);
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

    @GetMapping(value = "/getBookStockInfo")
    @ApiOperation(value = "getBookStockInfo", notes = "会员还书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "书籍的id", required = true, dataType = "STRING")
    })
    public ResponseEntity getBookStockInfo(@RequestParam("id") String id) {
        JSONObject jsonObject = iBookManageService.getBookStockInfo(id);
        return ResponseEntity.ok().body(jsonObject);
    }

    @PostMapping(value = "/getBookCase")
    @ApiOperation(value = "getBookCase", notes = "得到书籍的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "libraryId", value = "书籍编码", required = true, dataType = "STRING")
    })
    public ResponseEntity getBookCase(@RequestParam("libraryId") String libraryId) {
        JSONObject jsonObject = iBookManageService.getBookCase(libraryId);
        return ResponseEntity.ok().body(jsonObject);
    }

    @PostMapping(value = "/updateBookCase")
    @ApiOperation(value = "updateBookCase", notes = "得到书籍的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "libraryId", value = "书籍编码", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "bookCase", value = "书架信息", required = true, dataType = "STRING")
    })
    public ResponseEntity updateBookCase(@RequestParam("libraryId") String libraryId,
                                         @RequestParam("bookCase") String bookCase) {
        JSONObject jsonObject = iBookManageService.updateBookCase(libraryId, bookCase);
        return ResponseEntity.ok().body(jsonObject);
    }

    @PostMapping(value = "/insertUpdateBookNews")
    @ApiOperation(value = "insertUpdateBookNews", notes = "获取单本书籍的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookNewsData", value = "新闻信息", dataType = "String"),
    })
    public ResponseEntity insertUpdateBookNews(@RequestParam String bookNewsData) {
        try {
            BookNews bookNews = JSON.parseObject(bookNewsData, BookNews.class);
            bookNews.setNewsTime(new Date());
            BookNews bookNewsReturn = bookManageService.insertUpdateBookNews(bookNews);
            return ResponseEntity.ok().body(bookNewsReturn);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getAllBookNews")
    @ApiOperation(value = "getAllBookNews", notes = "根据新闻的类型获取新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsType", value = "新闻类型", dataType = "String"),
    })
    public ResponseEntity getAllBookNews(@RequestParam String newsType) {
        try {
            JSONArray jsonArray = bookManageService.getAllBookNews(newsType);
            System.out.println(jsonArray);
            return ResponseEntity.ok().body(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/deleteBookNews")
    @ApiOperation(value = "deleteBookNews", notes = "删除新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId", value = "新闻id", dataType = "String"),
    })
    public ResponseEntity deleteBookNews(@RequestParam String bookId) {
        try {
            return ResponseEntity.ok().body(bookManageService.deleteBookNews(bookId));
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getWebConfigInfo")
    @ApiOperation(value = "getWebConfigInfo", notes = "获取当前web配置信息")
    @ApiImplicitParams({
    })
    public ResponseEntity getWebConfigInfo() {
        try {
            return ResponseEntity.ok().body(webConfigurationService.getWebConfig());
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }


}




