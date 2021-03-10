package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.UserEntity;
import com.CC.app.vo.UserInfoVo;
import com.CC.app.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 用户表
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    UserVo login(Map<String, String> params);

    UserInfoVo getUserInfoById(Long id, Long myId);
}

