package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.app.entity.FriendEntity;
import com.CC.app.service.FriendService;


/**
 * 好友
 * @date 2021-03-02 21:30:16
 */
@RestController
@RequestMapping("app/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:friend:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:friend:info")
    public R info(@PathVariable("id") Long id){
		FriendEntity friend = friendService.getById(id);

        return R.ok().put("friend", friend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:friend:save")
    public R save(@RequestBody FriendEntity friend){
		friendService.save(friend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:friend:update")
    public R update(@RequestBody FriendEntity friend){
		friendService.updateById(friend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:friend:delete")
    public R delete(@RequestBody Long[] ids){
		friendService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
