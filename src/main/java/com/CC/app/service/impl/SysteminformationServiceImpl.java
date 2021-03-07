package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.SysteminformationDao;
import com.CC.app.entity.SysteminformationEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.SysteminformationService;


@Service("systeminformationService")
public class SysteminformationServiceImpl extends ServiceImpl<SysteminformationDao, SysteminformationEntity> implements SysteminformationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysteminformationEntity> page = this.page(
                new Query<SysteminformationEntity>().getPage(params),
                new QueryWrapper<SysteminformationEntity>()
        );

        return new PageUtils(page);
    }

}