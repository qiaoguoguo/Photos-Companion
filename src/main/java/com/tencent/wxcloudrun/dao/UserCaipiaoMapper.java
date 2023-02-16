package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.UserCaipiao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserCaipiaoMapper extends BaseMapper<UserCaipiao> {

    List<UserCaipiao> findByOpenidAndType(String openid,String type);
    List<UserCaipiao> findAll();

}

