package com.CC.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.R;
import com.CC.app.entity.ChatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.app.service.ChatService;


/**
 * 私聊
 *
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
@RestController
@RequestMapping("app/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:chat:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = chatService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:chat:info")
    public R info(@PathVariable("id") Long id){
		ChatEntity chat = chatService.getById(id);

        return R.ok().put("chat", chat);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:chat:save")
    public R save(@RequestBody ChatEntity chat){
		chatService.save(chat);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:chat:update")
    public R update(@RequestBody ChatEntity chat){
		chatService.updateById(chat);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:chat:delete")
    public R delete(@RequestBody Long[] ids){
		chatService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
