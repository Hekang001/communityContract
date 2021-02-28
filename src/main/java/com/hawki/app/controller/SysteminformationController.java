package com.hawki.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hawki.app.entity.SysteminformationEntity;
import com.hawki.app.service.SysteminformationService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.R;



/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:17
 */
@RestController
@RequestMapping("app/systeminformation")
public class SysteminformationController {
    @Autowired
    private SysteminformationService systeminformationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:systeminformation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = systeminformationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:systeminformation:info")
    public R info(@PathVariable("id") Long id){
		SysteminformationEntity systeminformation = systeminformationService.getById(id);

        return R.ok().put("systeminformation", systeminformation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:systeminformation:save")
    public R save(@RequestBody SysteminformationEntity systeminformation){
		systeminformationService.save(systeminformation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:systeminformation:update")
    public R update(@RequestBody SysteminformationEntity systeminformation){
		systeminformationService.updateById(systeminformation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:systeminformation:delete")
    public R delete(@RequestBody Long[] ids){
		systeminformationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
