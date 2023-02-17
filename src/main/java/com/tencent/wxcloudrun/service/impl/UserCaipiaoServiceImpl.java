package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserCaipiaoMapper;
import com.tencent.wxcloudrun.dao.UserZhongjiangMapper;
import com.tencent.wxcloudrun.dto.PushDTO;
import com.tencent.wxcloudrun.model.UserCaipiao;
import com.tencent.wxcloudrun.model.UserZhongjiang;
import com.tencent.wxcloudrun.service.UserCaipiaoService;
import com.tencent.wxcloudrun.utils.Query;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;

@Service
public class UserCaipiaoServiceImpl implements UserCaipiaoService {

    @Autowired
    private UserCaipiaoMapper userCaipiaoMapper;
    @Autowired
    private UserZhongjiangMapper userZhongjiangMapper;
    @Autowired
    private PushBizService pushBizService;

    @Override
    public void sava(UserCaipiao userCaipiao) {
        userCaipiaoMapper.insert(userCaipiao);
    }


    @Override
    public void deleteById(Integer id) {
        userCaipiaoMapper.deleteById(id);
    }

    @Override
    public List<UserCaipiao> findByUser(String openid, String type) {
        List<UserCaipiao> list = userCaipiaoMapper.findByOpenidAndType(openid,type);
        return list;
    }

    //兑奖方法
    @Override
    public void kaijiang(String type) {
        JSONObject kaijiang = Query.Get(14, "");
        String number = kaijiang.getString("number");
        String refernumber = kaijiang.getString("refernumber");
        String issueno = kaijiang.getString("issueno");
        //查询用户彩票数据进行当期对比
        List<String> kNum = new ArrayList<>(Arrays.asList(number.split(" ")));
        List<String> kRnum = new ArrayList<>(Arrays.asList(refernumber.split(" ")));
        List<UserCaipiao> userNum = userCaipiaoMapper.findAll();
        for (UserCaipiao caipiao : userNum){
            List<String> uNum = new ArrayList<>(Arrays.asList(caipiao.getNumber().split(" ")));
            List<String> uRnum = new ArrayList<>(Arrays.asList(caipiao.getRefernumber().split(" ")));
            uNum.retainAll(kNum);
            uRnum.retainAll(kRnum);
            String zuhe = uNum.size() + "-" + uRnum.size();
            Integer result = check(zuhe);
            UserZhongjiang zhongJiang = new UserZhongjiang();
            zhongJiang.setIssueno(issueno);
            zhongJiang.setType(type);
            zhongJiang.setLevel(result);
            zhongJiang.setNumber(number);
            zhongJiang.setRefernumber(refernumber);
            zhongJiang.setZuhe(zuhe);
            zhongJiang.setOpenid(caipiao.getOpenid());
            zhongJiang.setUnumber(caipiao.getNumber());
            zhongJiang.setUrefernumber(caipiao.getRefernumber());
            //存入中奖信息表
            if (result > 0){
                userZhongjiangMapper.insert(zhongJiang);
                //推送中奖消息给用户
                PushDTO pushDTO = new PushDTO(caipiao.getOpenid(),result+"等奖，中奖组合："+zuhe);
                pushBizService.pushOneUser(pushDTO);
            }
        }
    }

    @Override
    public List<UserZhongjiang> findByOpenid(String openid) {
        List<UserZhongjiang> list = userZhongjiangMapper.findByOpenid(openid);
        return list;
    }

    //大乐透判断中几等奖方法
    Integer check(String user){
        List<String> jiu = Arrays.asList("3-0","1-2","2-1","0-2");
        List<String> ba = Arrays.asList("3-1","2-2");
        List<String> qi = Arrays.asList("4-0");
        List<String> liu = Arrays.asList("3-2");
        List<String> wu = Arrays.asList("4-1");
        List<String> si = Arrays.asList("4-2");
        List<String> san = Arrays.asList("5-0");
        List<String> er = Arrays.asList("5-1");
        List<String> yi = Arrays.asList("5-2");
        if (jiu.contains(user)){
            return 9;
        }else if (ba.contains(user)){
            return 8;
        }else if (qi.contains(user)){
            return 7;
        }else if (liu.contains(user)){
            return 6;
        }else if (wu.contains(user)){
            return 5;
        }else if (si.contains(user)){
            return 4;
        }else if (san.contains(user)){
            return 3;
        }else if (er.contains(user)){
            return 2;
        }else if (yi.contains(user)){
            return 1;
        }else {
            return 0;
        }
    }


}
