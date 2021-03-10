package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.WalletEntity;
import com.CC.app.vo.WalletVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CC.app.service.WalletService;

@RestController
@RequestMapping("app/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:wallet:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = walletService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:wallet:info")
    public R info(@PathVariable("id") Long id){
		WalletEntity wallet = walletService.getById(id);

        return R.ok().put("wallet", wallet);
    }

    @PostMapping("/walletInfo")
    public R walletInfo(Long userId){
        WalletVo walletVo = walletService.getWalletInfoByUserId(userId);
        return R.ok().put("data", walletVo);
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:wallet:save")
    public R save(@RequestBody WalletEntity wallet){
		walletService.save(wallet);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:wallet:update")
    public R update(@RequestBody WalletEntity wallet){
		walletService.updateById(wallet);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:wallet:delete")
    public R delete(@RequestBody Long[] ids){
		walletService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
