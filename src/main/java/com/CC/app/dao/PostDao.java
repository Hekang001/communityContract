package com.CC.app.dao;

import com.CC.app.entity.PostEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 发帖
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:17
 */
@Mapper
public interface PostDao extends BaseMapper<PostEntity> {
	
}
