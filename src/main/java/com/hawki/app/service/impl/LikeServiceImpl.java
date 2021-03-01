package com.hawki.app.service.impl;

import com.hawki.app.vo.LikeVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.LikeDao;
import com.hawki.app.entity.LikeEntity;
import com.hawki.app.service.LikeService;


@Service("likeService")
public class LikeServiceImpl extends ServiceImpl<LikeDao, LikeEntity> implements LikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LikeEntity> page = this.page(
                new Query<LikeEntity>().getPage(params),
                new QueryWrapper<LikeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<LikeVo> getLikeListByPostId(Long id) {

        return baseMapper.getLikeListByPostId(id);
    }

}