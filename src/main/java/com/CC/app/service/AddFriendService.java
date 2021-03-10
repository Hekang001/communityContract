package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.AddFriendEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 加好友
 */
public interface AddFriendService extends IService<AddFriendEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean addFriend(Long userId, Long friendId);
}

