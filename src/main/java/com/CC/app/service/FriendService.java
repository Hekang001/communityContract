package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.CC.app.entity.FriendEntity;

import java.util.Map;

/**
 * 好友
 */
public interface FriendService extends IService<FriendEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean judgeFriendship(Long id, Long myId);

}

