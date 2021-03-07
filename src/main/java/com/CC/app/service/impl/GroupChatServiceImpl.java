package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.GroupChatDao;
import com.CC.app.entity.GroupChatEntity;
import com.CC.app.service.GroupChatService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


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