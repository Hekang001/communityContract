package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.CommentEntity;
import com.CC.app.vo.CommentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 评论/转发
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CommentVo> getCommentListByPostId(Long id);
}

