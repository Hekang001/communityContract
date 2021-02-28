package com.hawki.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hawki.app.entity.GroupMemberEntity;
import com.hawki.app.service.GroupMemberService;
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
@RequestMapping("app/groupmember")
public class GroupMemberController {
    @Autowired
    private GroupMemberService groupMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("app:groupmember:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = groupMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("app:groupmember:info")
    public R info(@PathVariable("id") Long id){
		GroupMemberEntity groupMember = groupMemberService.getById(id);

        return R.ok().put("groupMember", groupMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("app:groupmember:save")
    public R save(@RequestBody GroupMemberEntity groupMember){
		groupMemberService.save(groupMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("app:groupmember:update")
    public R update(@RequestBody GroupMemberEntity groupMember){
		groupMemberService.updateById(groupMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("app:groupmember:delete")
    public R delete(@RequestBody Long[] ids){
		groupMemberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
