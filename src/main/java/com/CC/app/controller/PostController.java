package com.CC.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.vo.PostListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CC.app.entity.PostEntity;
import com.CC.app.service.PostService;


/**
 * 发帖
 */
@RestController
@RequestMapping("app/post")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:post:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = postService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 展示帖子
     */
    @PostMapping("/explore")
    public R explore(){
        List<PostListVo> postListVo = postService.getPosts();
        return R.ok().put("data", postListVo);
    }

    /**
     * 用户信息详情
     * @param postId
     * @return
     */
    @PostMapping("/explore/detail")
    public R detail(Long postId){
        PostListVo postListVo = postService.getDetailById(postId);
        return R.ok().setData(postListVo);
    }

    /**
     * 转发
     * @param postId
     * @param userId
     * @return
     */
    @PostMapping("/explore/forward")
    public R forward(Long postId, Long userId){
        postService.forward(postId, userId);
        return R.ok("转发成功");
    }

    /**
     * 发帖
     */
    @PostMapping("/explore/posting")
    public R forward(@RequestParam Map<String, String> params){
        postService.posting(params);
        return R.ok("发帖成功");
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:post:info")
    public R info(@PathVariable("id") Long id){
		PostEntity post = postService.getById(id);

        return R.ok().put("post", post);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:post:save")
    public R save(@RequestBody PostEntity post){
		postService.save(post);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:post:update")
    public R update(@RequestBody PostEntity post){
		postService.updateById(post);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:post:delete")
    public R delete(@RequestBody Long[] ids){
		postService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
