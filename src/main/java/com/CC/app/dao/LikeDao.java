package com.CC.app.dao;

import com.CC.app.entity.LikeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.CC.app.vo.LikeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:16
 */
@Mapper
public interface LikeDao extends BaseMapper<LikeEntity> {

    List<LikeVo> getLikeListByPostId(@Param("postId") Long id);
}
