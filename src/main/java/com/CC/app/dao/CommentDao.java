package com.CC.app.dao;

import com.CC.app.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.CC.app.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论/转发
 *
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

    List<CommentVo> getCommentListByPostId(@Param("postId") Long id);
}
