package com.hawki.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.FriendDao;
import com.hawki.app.entity.FriendEntity;
import com.hawki.app.service.FriendService;


@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendDao, FriendEntity> implements FriendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendEntity> page = this.page(
                new Query<FriendEntity>().getPage(params),
                new QueryWrapper<FriendEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Boolean judgeFriendship(Long id, Long myId) {
        Integer count = baseMapper.selectCount(new QueryWrapper<FriendEntity>().eq("userid", myId).eq("frid", id));
        return count > 0;
    }

}