package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.SysteminformationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysteminformationService extends IService<SysteminformationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

