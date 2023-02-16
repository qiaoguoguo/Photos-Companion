package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.UserCaipiao;
import com.tencent.wxcloudrun.model.UserZhongjiang;
import com.tencent.wxcloudrun.service.UserCaipiaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CaipiaoController {

    @Autowired
    private UserCaipiaoService caipiaoService;

    /**
     * 用户添加彩票号码
     * @return API response json
     */
    @PostMapping(value = "/caipiao/addUserNum")
    ApiResponse get(@RequestBody UserCaipiao userCaipiao){
        caipiaoService.sava(userCaipiao);
        return ApiResponse.ok();
    }


    /**
     * 校验中奖信息
     * @return API response json
     */
    @GetMapping(value = "/caipiao/check")
    ApiResponse check(){
        caipiaoService.kaijiang("14");
        return ApiResponse.ok();
    }

    /**
     * 校验中奖信息
     * @return API response json
     */
    @GetMapping(value = "/caipiao/zjList")
    ApiResponse zjList(@RequestParam String openid){
        List<UserZhongjiang> byOpenid = caipiaoService.findByOpenid(openid);
        return ApiResponse.ok(byOpenid);
    }

}
