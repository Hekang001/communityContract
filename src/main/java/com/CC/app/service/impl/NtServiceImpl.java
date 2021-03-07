package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.NtDao;
import com.CC.app.entity.NtEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.NtService;


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