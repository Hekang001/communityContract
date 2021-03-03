package com.hawki.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.entity.PostEntity;
import com.hawki.app.vo.PostListVo;

import java.util.List;
import java.util.Map;

/**
 * 发帖
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:17
 */
public interface PostService extends IService<PostEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PostListVo> getPosts();

    PostListVo getDetailById(Long postId);

    void forward(Long postId, Long userId);

    void posting(Map<String, String> params);
}

