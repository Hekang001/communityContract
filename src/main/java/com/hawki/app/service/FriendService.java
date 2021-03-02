package com.hawki.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.entity.FriendEntity;

import java.util.Map;

/**
 * 好友
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:16
 */
public interface FriendService extends IService<FriendEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean judgeFriendship(Long id, Long myId);

}

