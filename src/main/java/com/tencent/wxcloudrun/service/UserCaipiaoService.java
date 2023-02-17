package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.UserCaipiao;
import com.tencent.wxcloudrun.model.UserZhongjiang;

import java.util.List;

public interface UserCaipiaoService {

    //用户新录入彩票号码
    void sava(UserCaipiao userCaipiao);

    //用户删除彩票号码
    void deleteById(Integer id);

    //用户查询自己全部彩票号码
    List<UserCaipiao> findByUser(String openid, String type);

    //调用API查询开奖信息
    void kaijiang(String type);

    //查询用户中奖记录
    List<UserZhongjiang> findByOpenid(String openid);
}
