package com.CC.app.dao;

import com.CC.app.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 *
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
