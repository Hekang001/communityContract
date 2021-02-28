package com.hawki.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hawki.app.entity.NtEntity;
import com.hawki.app.service.NtService;
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
@RequestMapping("app/nt")
public class NtController {
    @Autowired
    private NtService ntService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:nt:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ntService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:nt:info")
    public R info(@PathVariable("id") Long id){
		NtEntity nt = ntService.getById(id);

        return R.ok().put("nt", nt);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:nt:save")
    public R save(@RequestBody NtEntity nt){
		ntService.save(nt);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:nt:update")
    public R update(@RequestBody NtEntity nt){
		ntService.updateById(nt);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:nt:delete")
    public R delete(@RequestBody Long[] ids){
		ntService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
