package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.LikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.app.service.LikeService;

@RestController
@RequestMapping("app/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:like:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = likeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:like:info")
    public R info(@PathVariable("id") Long id){
		LikeEntity like = likeService.getById(id);

        return R.ok().put("like", like);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:like:save")
    public R save(@RequestBody LikeEntity like){
		likeService.save(like);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:like:update")
    public R update(@RequestBody LikeEntity like){
		likeService.updateById(like);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:like:delete")
    public R delete(@RequestBody Long[] ids){
		likeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
