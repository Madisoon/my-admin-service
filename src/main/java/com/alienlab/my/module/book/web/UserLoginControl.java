package com.alienlab.my.module.book.web;

import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.entity.UserInfo;
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
 * Created by zhuliang on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/book")
public class UserLoginControl {

    @Autowired
    UserManageService userManageService;

    @GetMapping(value = "/getUserInfoByPhone")
    @ApiOperation(value = "getUserInfoByPhone", notes = "通过手机号获取用户的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "手机号码", dataType = "string"),
    })
    public ResponseEntity getRecommendBook(@RequestParam String phoneno) {
        try {
            UserInfo userinfo = userManageService.getUserByPhone(phoneno);
            return ResponseEntity.ok().body(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/changePassword")
    @ApiOperation(value = "changePassword", notes = "用户修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneno", value = "手机号码", dataType = "string"),
            @ApiImplicitParam(name = "newpassword", value = "新密码", dataType = "string"),
    })
    public ResponseEntity changePassword(@RequestParam String phoneno,@RequestParam String newpassword) {
        try {
            UserInfo userinfo = userManageService.changePassword(phoneno,newpassword);
            return ResponseEntity.ok().body(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

}
