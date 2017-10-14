package com.alienlab.my.module.book.web;

import com.alienlab.my.entity.OrderInfo;
import com.alienlab.my.module.book.service.OrderInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhuliang on 2017/10/12.
 */
@RestController
@RequestMapping(value="/api")
public class userController {

    @Autowired
    OrderInfoService orderInfoService;
    @GetMapping(value = "/getAllReserveBook")
    @ApiOperation(value = "getAllReserveBook", notes = "获取用户预定书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name="readerId",value="用户id",dataType="string"),
    })
    public ResponseEntity getRecommendBook(@RequestParam String readerId) {
        try{
            List<OrderInfo> recommendList = orderInfoService.getAllreserveBook(readerId);
            return ResponseEntity.ok().body(recommendList);
        }catch(Exception e){
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
