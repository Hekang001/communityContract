package com.hawki.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.entity.UserEntity;
import com.hawki.app.vo.UserInfoVo;
import com.hawki.app.vo.UserVo;

import java.util.Map;

/**
 * 用户表
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    UserVo login(Map<String, String> params);

    UserInfoVo getUserInfoById(Long id, Long myId);
}

