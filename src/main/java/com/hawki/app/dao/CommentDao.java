package com.hawki.app.dao;

import com.hawki.app.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论/转发
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {
	
}
