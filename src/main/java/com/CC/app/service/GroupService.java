package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.GroupEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:16
 */
public interface GroupService extends IService<GroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

