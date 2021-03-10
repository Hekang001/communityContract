package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.vo.PostListVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.CC.app.entity.PostEntity;

import java.util.List;
import java.util.Map;

/**
 * 发帖
 *
 */
public interface PostService extends IService<PostEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PostListVo> getPosts();

    PostListVo getDetailById(Long postId);

    void forward(Long postId, Long userId);

    void posting(Map<String, String> params);
}

