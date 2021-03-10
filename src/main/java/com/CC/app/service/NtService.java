package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.NtEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface NtService extends IService<NtEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

