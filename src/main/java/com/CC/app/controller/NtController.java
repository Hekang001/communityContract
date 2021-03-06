package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.NtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.app.service.NtService;


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
