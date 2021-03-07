package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.GroupMemberDao;
import com.CC.app.entity.GroupMemberEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.GroupMemberService;


@Service("groupMemberService")
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberDao, GroupMemberEntity> implements GroupMemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupMemberEntity> page = this.page(
                new Query<GroupMemberEntity>().getPage(params),
                new QueryWrapper<GroupMemberEntity>()
        );

        return new PageUtils(page);
    }

}