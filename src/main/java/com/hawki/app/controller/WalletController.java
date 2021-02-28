package com.hawki.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hawki.app.entity.WalletEntity;
import com.hawki.app.service.WalletService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.R;



/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:16
 */
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
