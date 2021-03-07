package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.SysteminformationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:17
 */
public interface SysteminformationService extends IService<SysteminformationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

