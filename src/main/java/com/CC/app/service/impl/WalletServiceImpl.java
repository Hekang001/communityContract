package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.WalletDao;
import com.CC.app.entity.UserEntity;
import com.CC.app.entity.WalletEntity;
import com.CC.app.vo.WalletVo;
import com.CC.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.WalletService;


@Service("walletService")
public class WalletServiceImpl extends ServiceImpl<WalletDao, WalletEntity> implements WalletService {

    @Autowired
    UserService userService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WalletEntity> page = this.page(
                new Query<WalletEntity>().getPage(params),
                new QueryWrapper<WalletEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public WalletVo getWalletInfoByUserId(Long userId) {
        WalletVo walletVo = new WalletVo();
        Map<String, String> cardList = new HashMap<>();
        List<WalletEntity> walletEntities = baseMapper.selectList(new QueryWrapper<WalletEntity>().eq("user_id", userId));
        for(WalletEntity walletEntity : walletEntities)
            cardList.put( walletEntity.getCid(), walletEntity.getBank());

        walletVo.setCardList(cardList);
        UserEntity byId = userService.getById(userId);
        walletVo.setBalance(byId.getBalance());
        return walletVo;
    }

}