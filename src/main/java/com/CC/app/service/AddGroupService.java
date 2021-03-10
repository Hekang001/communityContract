package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.AddGroupEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface AddGroupService extends IService<AddGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

