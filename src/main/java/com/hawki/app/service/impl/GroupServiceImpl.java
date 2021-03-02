package com.hawki.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.GroupDao;
import com.hawki.app.entity.GroupEntity;
import com.hawki.app.service.GroupService;


@Service("groupService")
public class GroupServiceImpl extends ServiceImpl<GroupDao, GroupEntity> implements GroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupEntity> page = this.page(
                new Query<GroupEntity>().getPage(params),
                new QueryWrapper<GroupEntity>()
        );

        return new PageUtils(page);
    }

}