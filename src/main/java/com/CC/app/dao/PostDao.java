package com.CC.app.dao;

import com.CC.app.entity.PostEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 发帖
 *
 */
@Mapper
public interface PostDao extends BaseMapper<PostEntity> {
	
}
