package com.hawki.app.dao;

import com.hawki.app.entity.FriendEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 好友
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:16
 */
@Mapper
public interface FriendDao extends BaseMapper<FriendEntity> {
	
}
