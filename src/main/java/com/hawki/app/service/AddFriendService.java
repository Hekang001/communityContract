package com.hawki.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.entity.AddFriendEntity;

import java.util.Map;

/**
 * 加好友
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:17
 */
public interface AddFriendService extends IService<AddFriendEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean addFriend(Long userId, Long friendId);
}

