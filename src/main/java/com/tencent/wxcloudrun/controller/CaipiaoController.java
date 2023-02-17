package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.UserCaipiao;
import com.tencent.wxcloudrun.model.UserZhongjiang;
import com.tencent.wxcloudrun.service.UserCaipiaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 彩票类功能接口
 */
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
    ApiResponse get(@RequestBody UserCaipiao userCaipiao, HttpServletRequest request){
        String openid = request.getHeader("x-wx-openid");
        userCaipiao.setOpenid(openid);
        caipiaoService.sava(userCaipiao);
        return ApiResponse.ok();
    }

    /**
     * 用户删除彩票号码
     * @return API response json
     */
    @PostMapping(value = "/caipiao/delNum")
    ApiResponse delNum(@RequestParam Integer id, HttpServletRequest request){
        String openid = request.getHeader("x-wx-openid");
        caipiaoService.deleteById(id);
        return ApiResponse.ok();
    }

    /**
     * 查询当前用户所有彩票组合
     * @return API response json
     */
    @GetMapping(value = "/caipiao/listNum")
    ApiResponse listNum(HttpServletRequest request){
        String openid = request.getHeader("x-wx-openid");
        List<UserCaipiao> caipiaos = caipiaoService.findByUser(openid,"14");
        return ApiResponse.ok(caipiaos);
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
     * 查询全部中奖信息
     * @return API response json
     */
    @GetMapping(value = "/caipiao/zjList")
    ApiResponse zjList(HttpServletRequest request){
        String openid = request.getHeader("x-wx-openid");
        List<UserZhongjiang> byOpenid = caipiaoService.findByOpenid(openid);
        return ApiResponse.ok(byOpenid);
    }

}
