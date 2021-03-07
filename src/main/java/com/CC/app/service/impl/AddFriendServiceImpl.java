package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.dao.AddFriendDao;
import com.CC.app.entity.AddFriendEntity;
import com.CC.app.service.AddFriendService;


@Service("addFriendService")
public class AddFriendServiceImpl extends ServiceImpl<AddFriendDao, AddFriendEntity> implements AddFriendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AddFriendEntity> page = this.page(
                new Query<AddFriendEntity>().getPage(params),
                new QueryWrapper<AddFriendEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Boolean addFriend(Long userId, Long friendId) {
        AddFriendEntity addFriendEntity = baseMapper.selectOne(new QueryWrapper<AddFriendEntity>().eq("userid", userId).eq("frid", friendId));
        if(addFriendEntity != null){
            addFriendEntity.setUpdateTime(new Date());
            return baseMapper.updateById(addFriendEntity) > 0;
        }

        addFriendEntity = new AddFriendEntity();
        addFriendEntity.setUserid(userId);
        addFriendEntity.setFrid(friendId);
        addFriendEntity.setStatus(0);
        addFriendEntity.setContent("");
        addFriendEntity.setCreateTime(new Date());
        addFriendEntity.setUpdateTime(new Date());
        return baseMapper.insert(addFriendEntity) > 0;
    }

}