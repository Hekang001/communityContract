package com.hawki.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.GroupChatDao;
import com.hawki.app.entity.GroupChatEntity;
import com.hawki.app.service.GroupChatService;


@Service("groupChatService")
public class GroupChatServiceImpl extends ServiceImpl<GroupChatDao, GroupChatEntity> implements GroupChatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupChatEntity> page = this.page(
                new Query<GroupChatEntity>().getPage(params),
                new QueryWrapper<GroupChatEntity>()
        );

        return new PageUtils(page);
    }

}