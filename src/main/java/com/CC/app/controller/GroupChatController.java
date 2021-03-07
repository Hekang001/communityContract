package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.GroupChatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.app.service.GroupChatService;


/**
 * 
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-03-02 21:30:16
 */
@RestController
@RequestMapping("app/groupchat")
public class GroupChatController {
    @Autowired
    private GroupChatService groupChatService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:groupchat:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = groupChatService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:groupchat:info")
    public R info(@PathVariable("id") Long id){
		GroupChatEntity groupChat = groupChatService.getById(id);

        return R.ok().put("groupChat", groupChat);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:groupchat:save")
    public R save(@RequestBody GroupChatEntity groupChat){
		groupChatService.save(groupChat);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:groupchat:update")
    public R update(@RequestBody GroupChatEntity groupChat){
		groupChatService.updateById(groupChat);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:groupchat:delete")
    public R delete(@RequestBody Long[] ids){
		groupChatService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
