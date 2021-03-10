package com.CC.app.dao;

import com.CC.app.entity.ChatEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 私聊
 */
@Mapper
public interface ChatDao extends BaseMapper<ChatEntity> {
	
}
