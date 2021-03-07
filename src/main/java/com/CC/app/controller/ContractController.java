package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.ContractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.app.service.ContractService;


/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
@RestController
@RequestMapping("app/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:contract:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = contractService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:contract:info")
    public R info(@PathVariable("id") Long id){
		ContractEntity contract = contractService.getById(id);

        return R.ok().put("contract", contract);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:contract:save")
    public R save(@RequestBody ContractEntity contract){
		contractService.save(contract);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:contract:update")
    public R update(@RequestBody ContractEntity contract){
		contractService.updateById(contract);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:contract:delete")
    public R delete(@RequestBody Long[] ids){
		contractService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
