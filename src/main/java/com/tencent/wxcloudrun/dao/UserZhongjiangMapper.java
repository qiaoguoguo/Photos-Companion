package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.UserZhongjiang;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserZhongjiangMapper extends BaseMapper<UserZhongjiang> {

    List<UserZhongjiang> findByOpenid(String openid);

}
