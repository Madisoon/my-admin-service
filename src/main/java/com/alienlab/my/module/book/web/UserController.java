package com.alienlab.my.module.book.web;

import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.UserInfo;
import com.alienlab.my.module.book.service.OrderInfoService;
import com.alienlab.my.module.book.service.UserManageService;
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
        UserInfo userInfo = userManageService.getUserInfo(readerId);
        return ResponseEntity.ok().body(userInfo);
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
}
