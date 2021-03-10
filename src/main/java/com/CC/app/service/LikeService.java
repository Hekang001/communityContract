package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.LikeEntity;
import com.CC.app.vo.LikeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface LikeService extends IService<LikeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<LikeVo> getLikeListByPostId(Long id);
}

