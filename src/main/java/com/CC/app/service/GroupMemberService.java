package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.GroupMemberEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:16
 */
public interface GroupMemberService extends IService<GroupMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

