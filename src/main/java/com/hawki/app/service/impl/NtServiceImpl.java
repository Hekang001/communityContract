package com.hawki.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.NtDao;
import com.hawki.app.entity.NtEntity;
import com.hawki.app.service.NtService;


@Service("ntService")
public class NtServiceImpl extends ServiceImpl<NtDao, NtEntity> implements NtService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NtEntity> page = this.page(
                new Query<NtEntity>().getPage(params),
                new QueryWrapper<NtEntity>()
        );

        return new PageUtils(page);
    }

}