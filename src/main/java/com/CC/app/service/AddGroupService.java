package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.AddGroupEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:17
 */
public interface AddGroupService extends IService<AddGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

