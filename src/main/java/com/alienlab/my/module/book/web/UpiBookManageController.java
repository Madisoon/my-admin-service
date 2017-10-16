package com.alienlab.my.module.book.web;

import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.SaveInfo;
import com.alienlab.my.module.book.service.imp.BookManageService;
import com.sun.tools.javah.LLNI;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuliang on 2017/10/12.
 */
@RestController
@RequestMapping(value = "/api")
public class UpiBookManageController {

    @Autowired
    BookManageService bookManageService;

    @PostMapping("/collectBook")
    @ApiOperation(value = "collectBook", notes = "收藏书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "readerId", value = "用户Id", dataType = "string"),
            @ApiImplicitParam(name = "bookId", value = "用户Id", dataType = "string")
    })
    public ResponseEntity collectBook(@RequestParam String readerId, @RequestParam String bookId) {
        try {
            SaveInfo saveInfo = bookManageService.collectBook(readerId, bookId);
            return ResponseEntity.ok().body(saveInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping("/reserveBook")
    @ApiOperation(value = "reserveBook", notes = "预定书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "readerId", value = "用户id", dataType = "string"),
            @ApiImplicitParam(name = "bookId", value = "用户id", dataType = "string"),
            @ApiImplicitParam(name = "limit", value = "用户借书限制数", dataType = "int")
    })
    public ResponseEntity reserveBook(@RequestParam String readerId, @RequestParam String bookId, @RequestParam int limit) {
        try {
            OrderInfo orderInfo = bookManageService.orderBook(readerId, bookId, limit);
            return ResponseEntity.ok().body(orderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


}