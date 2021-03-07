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
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

    List<CommentVo> getCommentListByPostId(@Param("postId") Long id);
}
