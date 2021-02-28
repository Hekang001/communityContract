package com.hawki.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hawki.app.entity.LikeEntity;
import com.hawki.app.service.LikeService;
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
