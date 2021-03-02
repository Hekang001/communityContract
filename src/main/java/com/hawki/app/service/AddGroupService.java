package com.hawki.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.entity.AddGroupEntity;

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

