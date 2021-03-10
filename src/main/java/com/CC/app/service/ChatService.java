package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.ChatEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 私聊
 */
public interface ChatService extends IService<ChatEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

