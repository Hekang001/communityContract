package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.GroupEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface GroupService extends IService<GroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

