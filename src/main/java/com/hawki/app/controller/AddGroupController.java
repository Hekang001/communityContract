package com.hawki.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hawki.app.entity.AddGroupEntity;
import com.hawki.app.service.AddGroupService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.R;



/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:17
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
