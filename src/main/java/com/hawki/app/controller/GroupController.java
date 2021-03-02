package com.hawki.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hawki.app.entity.GroupEntity;
import com.hawki.app.service.GroupService;
import com.hawki.app.common.utils.PageUtils;
import com.hawki.app.common.utils.R;



/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:16
 */
@RestController
@RequestMapping("app/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:group:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = groupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:group:info")
    public R info(@PathVariable("id") Long id){
		GroupEntity group = groupService.getById(id);

        return R.ok().put("group", group);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:group:save")
    public R save(@RequestBody GroupEntity group){
		groupService.save(group);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:group:update")
    public R update(@RequestBody GroupEntity group){
		groupService.updateById(group);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:group:delete")
    public R delete(@RequestBody Long[] ids){
		groupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
