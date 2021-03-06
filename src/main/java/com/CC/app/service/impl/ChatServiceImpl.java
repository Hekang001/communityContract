package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.ChatDao;
import com.CC.app.entity.ChatEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.ChatService;


@Service("chatService")
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChatEntity> page = this.page(
                new Query<ChatEntity>().getPage(params),
                new QueryWrapper<ChatEntity>()
        );

        return new PageUtils(page);
    }

}