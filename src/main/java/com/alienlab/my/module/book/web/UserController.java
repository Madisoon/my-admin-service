package com.alienlab.my.module.book.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.UserInfo;
import com.alienlab.my.module.book.service.OrderInfoService;
import com.alienlab.my.module.book.service.UserManageService;
import com.alienlab.my.repository.UserInfoRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhuliang on 2017/10/12.
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    UserManageService userManageService;

    @GetMapping(value = "/getAllReserveBook")
    @ApiOperation(value = "getAllReserveBook", notes = "获取用户预定书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "readerId", value = "用户id", dataType = "string"),
    })
    public ResponseEntity getRecommendBook(@RequestParam String readerId) {
        try {
            List<OrderInfo> recommendList = orderInfoService.getAllreserveBook(readerId);
            return ResponseEntity.ok().body(recommendList);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getUserInfo")
    @ApiOperation(value = "getUserInfo", notes = "获取用户预定书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "readerId", value = "用户id", dataType = "string"),
    })
    public ResponseEntity getUserInfo(@RequestParam String readerId) {
        JSONObject jsonObject = userManageService.getUserInfo(readerId);
        return ResponseEntity.ok().body(jsonObject);
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

    @PostMapping(value = "/vipBorrowReturn")
    @ApiOperation(value = "vipBorrowReturn", notes = "还书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", value = "用户手机号/会员账号", dataType = "string"),
            @ApiImplicitParam(name = "libraryId", value = "书籍信息", dataType = "string")
    })
    public ResponseEntity vipBorrowReturn(@RequestParam String userPhone,
                                          @RequestParam String libraryId) {
        JSONObject jsonObject = userManageService.vipBorrowReturn(userPhone, libraryId);
        return ResponseEntity.ok().body(jsonObject);
    }

    @PostMapping(value = "/postUserData")
    @ApiOperation(value = "postUserData", notes = "改改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfoData", value = "用户信息", dataType = "string"),
            @ApiImplicitParam(name = "returnBookId", value = "还书的id", dataType = "string"),
            @ApiImplicitParam(name = "borrowBookId", value = "新借书的id", dataType = "string"),
            @ApiImplicitParam(name = "orderBookId", value = "预定书的id", dataType = "string")
    })
    public ResponseEntity postUserData(@RequestParam String userInfoData,
                                       @RequestParam String returnBookId,
                                       @RequestParam String borrowBookId,
                                       @RequestParam String orderBookId) {
        JSONObject jsonObject = userManageService.postUserData(userInfoData, returnBookId, borrowBookId, orderBookId);
        return ResponseEntity.ok().body(jsonObject);
    }


    @GetMapping(value = "/getUserInfoAndBookInfo")
    @ApiOperation(value = "userId", notes = "获取用户相关的图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "string"),
    })
    public ResponseEntity postUserData(@RequestParam Long userId) {
        try {
            UserInfo userInfo= userManageService.getUserInfoAndBook(userId);
            return ResponseEntity.ok().body(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


}
