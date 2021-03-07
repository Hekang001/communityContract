package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.ChatEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 私聊
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
public interface ChatService extends IService<ChatEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

