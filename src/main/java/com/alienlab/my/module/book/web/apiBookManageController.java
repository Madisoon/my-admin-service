package com.alienlab.my.module.book.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhuliang on 2017/10/12.
 */
@RestController
@RequestMapping(value = "/api")
public class apiBookManageController {

    @PostMapping("/collectBook")
    @ApiOperation(value="collectBook",notes="收藏书籍")
    @ApiImplicitParams({
        @ApiImplicitParam(name="",value="",dataType="")
    })
    public ResponseEntity collectBook(){
      try {
        return ResponseEntity.ok().body("");
      }catch (Exception e){
        e.printStackTrace();
        ExecResult er=new ExecResult(false,e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
      }
    }

    @PostMapping("/reserveBook")
    @ApiOperation(value="",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="",value="",dataType="")
    })
    public ResponseEntity funName(){
        try {
            return ResponseEntity.ok().body("");
        }catch (Exception e){
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
