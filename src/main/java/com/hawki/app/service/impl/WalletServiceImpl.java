package com.hawki.app.service.impl;

import com.hawki.app.entity.UserEntity;
import com.hawki.app.service.UserService;
import com.hawki.app.vo.WalletVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.Query;

import com.hawki.app.dao.WalletDao;
import com.hawki.app.entity.WalletEntity;
import com.hawki.app.service.WalletService;


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