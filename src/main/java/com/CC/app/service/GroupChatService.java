package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.GroupChatEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface GroupChatService extends IService<GroupChatEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

