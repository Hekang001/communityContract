package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.GroupMemberEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface GroupMemberService extends IService<GroupMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

