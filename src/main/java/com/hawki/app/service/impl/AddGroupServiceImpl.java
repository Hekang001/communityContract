package com.hawki.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.AddGroupDao;
import com.hawki.app.entity.AddGroupEntity;
import com.hawki.app.service.AddGroupService;


@Service("addGroupService")
public class AddGroupServiceImpl extends ServiceImpl<AddGroupDao, AddGroupEntity> implements AddGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AddGroupEntity> page = this.page(
                new Query<AddGroupEntity>().getPage(params),
                new QueryWrapper<AddGroupEntity>()
        );

        return new PageUtils(page);
    }

}