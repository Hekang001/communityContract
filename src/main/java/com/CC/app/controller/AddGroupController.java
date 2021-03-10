package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.AddGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.app.service.AddGroupService;


/**
 *
 */
@RestController
@RequestMapping("app/addgroup")
public class AddGroupController {
    @Autowired
    private AddGroupService addGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:addgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:addgroup:info")
    public R info(@PathVariable("id") Long id){
		AddGroupEntity addGroup = addGroupService.getById(id);

        return R.ok().put("addGroup", addGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:addgroup:save")
    public R save(@RequestBody AddGroupEntity addGroup){
		addGroupService.save(addGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:addgroup:update")
    public R update(@RequestBody AddGroupEntity addGroup){
		addGroupService.updateById(addGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:addgroup:delete")
    public R delete(@RequestBody Long[] ids){
		addGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
