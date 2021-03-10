package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.WalletEntity;
import com.CC.app.vo.WalletVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface WalletService extends IService<WalletEntity> {

    PageUtils queryPage(Map<String, Object> params);

    WalletVo getWalletInfoByUserId(Long userId);
}

