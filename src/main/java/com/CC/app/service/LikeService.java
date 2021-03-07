package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.LikeEntity;
import com.CC.app.vo.LikeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:16
 */
public interface LikeService extends IService<LikeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<LikeVo> getLikeListByPostId(Long id);
}

