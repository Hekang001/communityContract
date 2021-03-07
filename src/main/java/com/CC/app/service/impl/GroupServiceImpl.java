package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.GroupDao;
import com.CC.app.entity.GroupEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.GroupService;


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