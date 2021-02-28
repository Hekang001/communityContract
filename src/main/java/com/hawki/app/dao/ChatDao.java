package com.hawki.app.dao;

import com.hawki.app.entity.ChatEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 私聊
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
@Mapper
public interface ChatDao extends BaseMapper<ChatEntity> {
	
}
